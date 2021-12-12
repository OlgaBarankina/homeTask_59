package com.example.hometask_59

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // add into the build.gradle
        //implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9'
        //implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'

        tvEnterText.visibility = View.INVISIBLE
        edSeconds.visibility = View.INVISIBLE
        ivTryAgain.visibility = View.GONE
        ivWin.visibility = View.GONE


        val rand = (5..20).random()
        ///Log.d("MyLog59", rand.toString())

        var result = (rand * 1000).toLong()



        btStart.setOnClickListener {


            timer = object : CountDownTimer(result, 1000  /*step in 1 sec*/) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    //tvRandom.setText("Random: " + millisUntilFinished / 1000 + " seconds")
                }


                override fun onFinish() {
                    tvEnterText.visibility = View.VISIBLE
                    edSeconds.visibility = View.VISIBLE
                }
            }

            timer.start()

        }



        CoroutineScope(Dispatchers.Main).launch {
            btPost.setOnClickListener {

                var edSeconds = Integer.parseInt(edSeconds.getText().toString())
                var userResult = (edSeconds * 1000).toLong()

                if (userResult.equals(result)) {
                    ivWin.visibility = View.VISIBLE
                    btStart.visibility = View.INVISIBLE
                    btPost.visibility = View.INVISIBLE
                    ivClock.visibility = View.INVISIBLE
                } else {
                    ivTryAgain.visibility = View.VISIBLE
                    btStart.visibility = View.INVISIBLE
                    btPost.visibility = View.INVISIBLE
                    ivClock.visibility = View.INVISIBLE

                }


            }
        }

    }
}