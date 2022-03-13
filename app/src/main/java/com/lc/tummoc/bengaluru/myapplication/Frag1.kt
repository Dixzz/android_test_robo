package com.lc.tummoc.bengaluru.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lc.tummoc.bengaluru.myapplication.databinding.ActivityMainBinding
import com.lc.tummoc.bengaluru.myapplication.databinding.ActivityNavBinding
import java.lang.ref.WeakReference

class Frag1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var binding: ActivityMainBinding
    lateinit var referenceActivity: Nav

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.wew2).setOnClickListener {
            //view.findNavController().navigate(R.id.action_fragment1_to_fragment2)
            addOneToNumber()
        }
    }

    fun addOneToNumber() {
        referenceActivity.abc.abc.let {
            it.value = it.value!! + 1
        }
    }

    companion object {
        fun newInstance(act: Nav): Frag1 {
            val fragment = Frag1()
            fragment.referenceActivity = act
            return fragment
        }
    }
}