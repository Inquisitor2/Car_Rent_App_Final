package com.example.carrent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrent.adapters.RecyclerViewAdapter.MyViewHolder
import com.example.carrent.databinding.ListItemBinding
import com.example.carrent.databinding.NewModelListItemBinding
import com.example.carrent.models.Car

class NewModelRVA(private val data: List<Car>,private val onItemClick: (Car) -> Unit) : RecyclerView.Adapter<NewModelRVA.MyViewHolder>() {

    inner class MyViewHolder(val binding: NewModelListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = NewModelListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val car = data[position]
        holder.binding.apply {
            carName.text = car.brand
            carModel.text = car.model
            carPrice.text = car.price
            carYear.text = car.year.toString()
            ratingText.text = car.rating.toString() + " /5"
            Glide.with(carImage.context)
                .load(car.imageUrl)
                .into(carImage)
        }

        holder.binding.root.setOnClickListener {
            onItemClick(car)
        }
    }
}
