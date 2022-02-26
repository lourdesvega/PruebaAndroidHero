package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lourder.vega.pruebaandroidlcvm.R
import lourder.vega.pruebaandroidlcvm.databinding.ItemDataBinding
import lourder.vega.pruebaandroidlcvm.databinding.ItemHeroBinding
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroList

class DataAdapter: RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private var listData = mutableListOf<String>()

    class ViewHolder(vBind: View) : RecyclerView.ViewHolder(vBind) {
        val vBind = ItemDataBinding.bind(vBind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = listData[position]
        holder.vBind.apply {
            tvDataName.text = dataItem
        }

    }

    override fun getItemCount() = listData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(data: List<String>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

}