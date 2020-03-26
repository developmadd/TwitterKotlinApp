package com.madd.madd.twitterkapp.utils

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.madd.madd.twitterkapp.R

class Utilities {

    companion object {
        fun setImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }


        /*fun openFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(R.id.CTNR_Main, fragment)
                .addToBackStack(null)
                .commit()
        }*/
    }


}