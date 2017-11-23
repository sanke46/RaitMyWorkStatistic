package com.sanke.ilafedoseev.raitmyworkstatistic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sanke.ilafedoseev.raitmyworkstatistic.FireBase.FireBaseRealTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fireBase = FireBaseRealTime()
        var text : TextView = findViewById(R.id.averageInterest) as TextView
        fireBase.allAverageLike(text)
    }

}
