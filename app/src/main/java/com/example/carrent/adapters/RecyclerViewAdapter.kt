package com.example.carrent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrent.databinding.ListItemBinding
import com.example.carrent.models.Car

class RecyclerViewAdapter(private val data: List<Car>,private val onItemClick: (Car) -> Unit ) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val car = data[position]
        holder.binding.apply {
            carBrand.text = car.brand
            carModel.text = car.model
            carYear.text = car.year.toString()
            carPrice.text = car.price
            ratingValue.text = car.rating.toString()
            Glide.with(carImage.context)
                .load(car.imageUrl)
                .into(carImage)
        }

        holder.binding.root.setOnClickListener {
            onItemClick(car)
        }
    }
}