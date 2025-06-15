package com.example.carrent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrent.databinding.VprvaListItemBinding
import com.example.carrent.models.Car

//class resRVA(private val data: List<Car>) : RecyclerView.Adapter<resRVA.MyViewHolder>() {
//
//    inner class MyViewHolder(val binding: VprvaListItemBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = VprvaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = data.size
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val car = data[position]
//        holder.binding.apply {
//            carBrand.text = car.brand
//            carModel.text = car.model
//            carYear.text = car.year.toString()
//            carPrice.text = car.price
//            itemRating.text = car.rating.toString()
//            mileage.text = car.mileage.toString()
//            location.text = car.location
//            Glide.with(itemImage.context)
//                .load(car.imageUrl)
//                .fitCenter()
//                .into(itemImage)
//        }
//    }
//}

class resRVA(
    private val data: List<Car>,
    private val onItemClicked: (Car) -> Unit
) : RecyclerView.Adapter<resRVA.MyViewHolder>() {

    inner class MyViewHolder(val binding: VprvaListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = VprvaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            itemRating.text = car.rating.toString()
            mileage.text = car.mileage.toString()
            location.text = car.location
            Glide.with(itemImage.context)
                .load(car.imageUrl)
                .fitCenter()
                .into(itemImage)

            root.setOnClickListener {
                onItemClicked(car)
            }
        }
    }
}
