package com.lc.tummoc.bengaluru.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.lc.tummoc.bengaluru.myapplication.databinding.ActivityNavBinding

class Nav : AppCompatActivity() {
    lateinit var binding: ActivityNavBinding
    lateinit var navController: NavController

    val abc by viewModels<Abc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        this.setupActionBarWithNavController(navController)
    }

    fun goToFragOne() {
        navController.navigate(R.id.action_fragment1_to_fragment2)
    }
}