package com.scolotin.logintestapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scolotin.logintestapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                                  .replace(R.id.container, LoginFragment.newInstance())
                                  .commit()
        }
    }
}