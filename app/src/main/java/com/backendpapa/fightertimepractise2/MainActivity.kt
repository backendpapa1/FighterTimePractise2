package com.backendpapa.fightertimepractise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var game_score_view:TextView
    private lateinit var game_time_view:TextView
    private lateinit var game_button:Button

    private lateinit var countdownTimer:CountDownTimer

    private var gameStarted=false
    private var initialCountdown:Long=60000
    private var countdownInterval:Long=1000
    private var score=0
    private var timeleft=60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game_score_view=findViewById(R.id.game_score_id)
        game_time_view=findViewById(R.id.game_time_id)
        game_button=findViewById(R.id.button)
        resetGame()
        game_button.setOnClickListener{
            if(!gameStarted){
                startGame()
            }
            increment()
        }

    }

    private fun startGame(){
    countdownTimer.start()
        gameStarted=true
    }

    private fun resetGame(){
        score=0
        game_score_view.text=getString(R.string.game_score,score)
        timeleft=60
        game_time_view.text=getString(R.string.game_time,timeleft)
        gameStarted=false
        countdownTimer=object :CountDownTimer(initialCountdown,countdownInterval){
            override fun onTick(millisUntilFinished: Long) {
                timeleft=millisUntilFinished.toInt()/1000
                game_time_view.text=getString(R.string.game_time,timeleft)
            }

            override fun onFinish() {
                endGame()
            }

        }

    }
    private fun endGame(){
        Toast.makeText(this,getString(R.string.final_score,score),Toast.LENGTH_LONG).show()
        resetGame()

    }
    private fun increment(){
        score++
        game_score_view.text=getString(R.string.game_score,score)
    }
}