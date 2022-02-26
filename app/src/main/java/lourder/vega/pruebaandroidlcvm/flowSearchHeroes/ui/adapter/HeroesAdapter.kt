package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lourder.vega.pruebaandroidlcvm.R
import lourder.vega.pruebaandroidlcvm.databinding.ItemHeroBinding
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroList

class HeroesAdapter(private val onClick: (Int) -> Unit): RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {
    private var listHeroes = mutableListOf<HeroList>()

    class ViewHolder(vBind: View) : RecyclerView.ViewHolder(vBind) {
        val vBind = ItemHeroBinding.bind(vBind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = listHeroes[position]
        holder.vBind.apply {
            root.setOnClickListener {
                onClick(hero.id)
            }
           Glide.with(ivHero)
               .load(hero.images.md)
               .centerCrop()
               .into(ivHero)
            tvNameHero.text = hero.name
        }

    }

    override fun getItemCount() = listHeroes.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(heroes: List<HeroList>) {
        listHeroes.clear()
        listHeroes.addAll(heroes)
        notifyDataSetChanged()
    }

}