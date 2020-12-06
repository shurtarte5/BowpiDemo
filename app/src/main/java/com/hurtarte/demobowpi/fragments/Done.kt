package com.hurtarte.demobowpi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hurtarte.demobowpi.R


class Done : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootview = inflater.inflate(R.layout.fragment_done,container,false)





        return rootview
    }
}