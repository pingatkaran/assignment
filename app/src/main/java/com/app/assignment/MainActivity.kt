package com.app.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.assignment.adapter.AQIAdapter
import com.app.assignment.databinding.ActivityMainBinding
import com.app.assignment.model.APIResponse
import com.app.assignment.utils.ApiState
import com.app.assignment.viewmodel.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var aqiAdapter: AQIAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        userViewModel.isSocketConnected()

        userViewModel.cartContent.observe(this) { content ->

            if(content.equals("failure")){
                userViewModel.isSocketConnected()
            }else{
                val gson = GsonBuilder().create()
                val theList = gson.fromJson<ArrayList<APIResponse>>(content, object :
                    TypeToken<ArrayList<APIResponse>>(){}.type)

                aqiAdapter.submitList(theList)
            }
        }
    }

    private fun initRecyclerview() {
        binding.apply {
            list.apply {   setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = aqiAdapter
            }

        }
    }
}