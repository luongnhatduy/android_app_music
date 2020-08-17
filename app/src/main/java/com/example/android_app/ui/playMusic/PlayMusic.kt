package com.example.android_app.ui.playMusic
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.example.android_app.R


class PlayMusic : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.play_music, container, false)
        val bundle = this.arguments
        if (bundle != null) {
           val myInt = bundle.getString("nameSong");
           val text  = root.findViewById(R.id.name_song) as TextView
           text.setText(myInt)
        }
        return root
    }
}

