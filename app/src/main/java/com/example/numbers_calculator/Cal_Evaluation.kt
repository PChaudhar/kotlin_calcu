package com.example.numbers_calculator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_cal__evaluation.*
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


private val sharedPrefFile = "kotlinsharedpreference"

class Cal_Evaluation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal__evaluation)


//        val intent = Intent(
//            this, MainActivity.class);
//                    startActivity (intent);
//        finish();

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        tvEquals.setOnClickListener(View.OnClickListener {
            val id: Int = Integer.parseInt(tvExpression.text.toString())
            val name: Int = tvResult.text.toString().toInt()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.apply()
            editor.commit()
        });

        tvExpression.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)")
            if (sharedIdValue.equals(0)) {
                tvExpression.setText("default id: ${sharedIdValue.toString()}")
                tvResult.setText("default id: ${sharedIdValue.toString()}")
            } else {
                tvExpression.setText(sharedIdValue).toString()
                tvResult.setText(sharedIdValue.toString())
            }


            //Numbers
            tvOne.setOnClickListener { appendOnExpresstion("1", true) }
            tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
            tvThree.setOnClickListener { appendOnExpresstion("3", true) }
            tvFour.setOnClickListener { appendOnExpresstion("4", true) }
            tvFive.setOnClickListener { appendOnExpresstion("5", true) }
            tvSix.setOnClickListener { appendOnExpresstion("6", true) }
            tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
            tvEight.setOnClickListener { appendOnExpresstion("8", true) }
            tvNine.setOnClickListener { appendOnExpresstion("9", true) }
            tvZero.setOnClickListener { appendOnExpresstion("0", true) }
            tvDot.setOnClickListener { appendOnExpresstion(".", true) }

            //Operators
            tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
            tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
            tvMul.setOnClickListener { appendOnExpresstion("*", false) }
            tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
            history.setOnClickListener { appendOnExpresstion("(", false) }

            tvClear.setOnClickListener {
                tvExpression.text = ""
                tvResult.text = ""
            }

            tvBack.setOnClickListener {
                val string = tvExpression.text.toString()
                if (string.isNotEmpty()) {
                    tvExpression.text = string.substring(0, string.length - 1)
                }
                tvResult.text = ""
            }

            tvEquals.setOnClickListener {
                try {

                    val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble())
                        tvResult.text = longResult.toString()
                    else
                        tvResult.text = result.toString()

                } catch (e: Exception) {
                    Log.d("Exception", " message : " + e.message)
                }
            }

        }

        fun appendOnExpresstion(string: String, canClear: Boolean) {

            if (tvResult.text.isNotEmpty()) {
                tvExpression.text = ""
            }

            if (canClear) {
                tvResult.text = ""
                tvExpression.append(string)
            } else {
                tvExpression.append(tvResult.text)
                tvExpression.append(string)
                tvResult.text = ""
            }


        }
    }
}
