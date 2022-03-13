package com.lc.tummoc.bengaluru.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class Frag2:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.dadasd).setOnClickListener {
            view.findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }
    }

    fun addOneToNumber(abc: Abc){
        abc.abc.postValue(abc.abc.value?.plus(1))
    }
}