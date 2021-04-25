package com.example.catapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.adapter.CatAdapter
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.util.APiStates
import com.example.catapp.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var adapter: CatAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel:CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        initRecyclerView()

        viewModel=ViewModelProvider(this).get(CatViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.catStateFlow.collect {
                when(it){
                    is APiStates.Failure -> {
                        binding.recycler.isVisible = false
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "${it.msg}", Toast.LENGTH_SHORT).show()
                        Log.i("main", "Error is : ${it.msg}")
                    }

                    is APiStates.Success -> {
                        binding.recycler.isVisible = true
                        binding.progressBar.isVisible = false
                        Log.i("main", "Data is : ${it.data}")
                        adapter.submitList(it.data)
                    }

                    is APiStates.Loading->{
                        binding.recycler.isVisible=false
                        binding.progressBar.isVisible=true
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter= CatAdapter()
        binding.recycler.adapter=adapter
    }
}