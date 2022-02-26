package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
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
               .asBitmap()
               .load(hero.images.md)
               .centerCrop()
               .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
               .listener(object: RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if(resource != null){
                        val p: Palette = Palette.from(resource).generate()
                        val swatch = p.lightVibrantSwatch
                        cvHero.setCardBackgroundColor(swatch?.rgb ?: R.color.white)
                    }
                    return false
                }
            })
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