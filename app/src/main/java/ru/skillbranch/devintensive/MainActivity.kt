package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extension.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var benderImage : ImageView
    lateinit var textText:TextView
    lateinit var etmessage:EditText
    lateinit var sendBtn:ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","onCreate")

        //findViewById<>()
        benderImage = iv_bender
        textText = textTxt
        etmessage = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        val message = savedInstanceState?.getString("MESSAGE")?: ""

        etmessage.setText(message)

        Log.d("M_MainActivity","Question is = $question")
        benderObj = Bender(Bender.Status.valueOf(status),Bender.Question.valueOf(question))

        val(r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)

        textText.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
        Log.d("M_MainActivity","OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","################OnDestroy##################")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","--------------onRestart--------------------")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send){
           val(phrase,color) = benderObj.listenAnswer(etmessage.text.toString().toLowerCase())
            etmessage.setText("")
            val(r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
            textText.text = phrase
            //hideKeyboard()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("M_MainActivity","SavE INSTANCE")
        outState?.putString("MESSAGE",etmessage.text.toString())
        outState?.putString("STATUS",benderObj.status.name)
        outState?.putString("QUESTION",benderObj.question.name)
    }
}
