package com.vogella.android.intentactivity

import android.app.Activity
import android.app.UiAutomation
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

const val FONT_CHOOSER_REQUEST_CODE = 1
lateinit var textStyle: String
lateinit var textBold : String
lateinit var textSetSize : String
lateinit var textColor : String


var typeFace = Typeface.DEFAULT
var boldItalic = 0
var setSize = 14F


class ClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentButton.setOnClickListener(View.OnClickListener {
            val intent = Intent("msud.cs3013.ACTION_RETRIEVE_FONT")
            val message : String = textView.text.toString()
            intent.putExtra("value", message)

            startActivityForResult(intent, FONT_CHOOSER_REQUEST_CODE)
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FONT_CHOOSER_REQUEST_CODE){

            if (resultCode == Activity.RESULT_OK){
                textStyle = data!!.getStringExtra("TextStyle")
                textBold = data!!.getStringExtra("BoldItalic")
                textSetSize = data!!.getStringExtra("SetSize")
                textColor = data!!.getStringExtra("SetColor")
            }
            else {
                super.onActivityResult(requestCode, resultCode, data)
            }
            applyTextStyle()
            applyBoldItalic()
            applyColor()
            applySize()
            textView.setTypeface(typeFace, boldItalic)
        }

    }

    fun applyTextStyle() {
        if (textStyle == "DEFAULT") {
            typeFace = Typeface.DEFAULT
        }
        else if (textStyle == "DEFAULT_BOLD") {
            typeFace = Typeface.DEFAULT_BOLD
        }
        else if (textStyle == "MONOSPACE") {
            typeFace = Typeface.MONOSPACE
        }
        else if (textStyle == "SANS_SERIF") {
            typeFace = Typeface.SANS_SERIF
        }
        else if (textStyle == "SERIF") {
            typeFace = Typeface.SERIF
        }

    }

    fun applyBoldItalic() {
        if (textBold == "0") {
            boldItalic = 0
        }
        else if (textBold == "1") {
            boldItalic = 1
        }
        else if (textBold == "2") {
            boldItalic = 2
        }
        else if (textBold == "3") {
            boldItalic = 3
        }
    }

    fun applySize() {
        textView.textSize = textSetSize.toFloat()

    }
    fun applyColor() {
        if (textColor == "Red") {
            textView.setTextColor(Color.rgb(255, 0, 0))
        }
        else if (textColor == "Blue") {
            textView.setTextColor(Color.rgb(0, 0, 255))
        }
        else if (textColor == "Green") {
            textView.setTextColor(Color.rgb(0, 255, 0))
        }
        else if (textColor == "Black") {
            textView.setTextColor(Color.rgb(0, 0, 0))
        }

    }

}
