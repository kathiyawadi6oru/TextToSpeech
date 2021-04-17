package com.example.texttospeech
import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.util.*


class MainActivity : Activity(), OnInitListener, OnItemSelectedListener {
    /** Called when the activity is first created.  */
    private var tts: TextToSpeech? = null
    private var buttonSpeak: Button? = null
    private var editText: EditText? = null
    private var speedSpinner: Spinner? = null
    private var lang: Spinner? = null

    private var speed = "Normal"
   // private var language = "US"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts = TextToSpeech(this, this)
        buttonSpeak = findViewById<View>(R.id.button1) as Button
        editText = findViewById<View>(R.id.editText1) as EditText
        speedSpinner = findViewById<View>(R.id.spinner1) as Spinner
        lang = findViewById(R.id.spinner2) as Spinner
//load data in spinner
        loadSpinnerData()
        //loadLangData()
        speedSpinner!!.onItemSelectedListener = this
        lang!!.onItemSelectedListener = this

//button click event
        buttonSpeak!!.setOnClickListener {
            setSpeed()
            //setlang()
            speakOut()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            //val result = setResult()
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported")
            } else {
                buttonSpeak!!.isEnabled = true
                speakOut()
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    public override fun onDestroy() {
// Don't forget to shutdown tts!
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    private fun setSpeed() {
        if (speed == "Very Slow") {
            tts!!.setSpeechRate(0.1f)
        }
        if (speed == "Slow") {
            tts!!.setSpeechRate(0.5f)
        }
        if (speed == "Normal") {
            tts!!.setSpeechRate(1.0f) //default 1.0
        }
        if (speed == "Fast") {
            tts!!.setSpeechRate(1.5f)
        }
        if (speed == "Very Fast") {
            tts!!.setSpeechRate(2.0f)
        }
        //for setting pitch you may call
        //tts.setPitch(1.0f);//default 1.0
    }
    private fun setlang() {
        if (speed == "Very Slow") {
            tts!!.setSpeechRate(0.1f)
        }
        if (speed == "Slow") {
            tts!!.setSpeechRate(0.5f)
        }
        if (speed == "Normal") {
            tts!!.setSpeechRate(1.0f) //default 1.0
        }
        if (speed == "Fast") {
            tts!!.setSpeechRate(1.5f)
        }
        if (speed == "Very Fast") {
            tts!!.setSpeechRate(2.0f)
        }
        //for setting pitch you may call
        //tts.setPitch(1.0f);//default 1.0
    }

    private fun speakOut() {
        val text = editText!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun loadSpinnerData() {
        //Data for speed Spinner
        val lables: MutableList<String> = ArrayList()
        lables.add("Very Slow")
        lables.add("Slow")
        lables.add("Normal")
        lables.add("Fast")
        lables.add("Very Fast")

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lables)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        speedSpinner!!.adapter = dataAdapter
    }
    private fun loadLangData() {
        //Data for speed Spinner
        val lables: MutableList<String> = ArrayList()
        lables.add("Very Slow")
        lables.add("Slow")
        lables.add("Normal")
        lables.add("Fast")
        lables.add("Very Fast")

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lables)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        speedSpinner!!.adapter = dataAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int,
                                id: Long) {
        // On selecting a spinner item
        speed = parent.getItemAtPosition(position).toString()
        Toast.makeText(parent.context, "You selected: " + speed,
                Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) {}
   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }*/

    companion object {
        private var speed = "Normal"
    }
}