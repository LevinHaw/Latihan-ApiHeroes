package com.dicoding.latihanapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.latihanapi.databinding.ItemHeroBinding
import com.dicoding.latihanapi.repository.data.remote.response.Heroes

class HeroesAdapter(private val heroes : ArrayList<Heroes>) :
    RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>(){

    class HeroesViewHolder(val binding : ItemHeroBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hero: Heroes) {
            binding.tvName.text = hero.name
            binding.tvBio.text = hero.bio
            binding.tvRealName.text = "Realname : " + hero.realname
            Glide.with(binding.root)
                .load(hero.imageurl)
                .into(binding.ivHero)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroesViewHolder(binding)
    }

    override fun getItemCount() = heroes.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(heroes[position])
    }
}