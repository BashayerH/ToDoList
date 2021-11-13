package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.StartFragment.StartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (currentFragment == null) {

            val fragment = StartFragment()
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slid_out_left,
                    R.anim.slide_out_right,
                    R.anim.slied_in_left
                )
                .add(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }
}