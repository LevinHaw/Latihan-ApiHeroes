package com.dicoding.latihanapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.latihanapi.adapter.HeroesAdapter
import com.dicoding.latihanapi.databinding.ActivityMainBinding
import com.dicoding.latihanapi.repository.data.remote.response.Heroes
import com.dicoding.latihanapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHeroes.addItemDecoration(itemDecoration)

        mainViewModel.getIsLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.getListHero.observe(this) { hero ->
            setListHero(hero)
        }

    }

    private fun setListHero(hero: ArrayList<Heroes>){
        binding.rvHeroes.adapter = HeroesAdapter(hero)

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}