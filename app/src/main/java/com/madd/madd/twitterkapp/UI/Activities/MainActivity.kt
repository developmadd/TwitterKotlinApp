package com.madd.madd.twitterkapp.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.UI.Fragments.UserProfile.UserProfileFragment

class MainActivity : AppCompatActivity() {

    private var userProfileFragment = UserProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction().add(R.id.CTNR_Main, userProfileFragment)
            .commit()
    }
}
