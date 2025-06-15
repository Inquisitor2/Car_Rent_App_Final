package com.example.carrent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrent.R
import com.example.carrent.databinding.BankDebitCardBinding
import com.example.carrent.models.bankCard


class debitCardRVA(
    private var data: List<bankCard>,
    private val onCardClick: (bankCard) -> Unit
) : RecyclerView.Adapter<debitCardRVA.MyViewHolder>() {

    inner class MyViewHolder(val binding: BankDebitCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onCardClick(data[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = BankDebitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card = data[position]
        holder.binding.apply {
            cardNumber.text = card.cardNumber
            ownerName.text = card.cardOwner
            expiryDate.text = card.expirationDate
            val iconRes = when (card.cardType) {
                "Visa" -> R.drawable.visa
                "MasterCard" -> R.drawable.mastercard
                else -> R.drawable.visa
            }
            cardTypeIcon.setImageResource(iconRes)
        }
    }

    fun updateData(newData: List<bankCard>) {
        data = newData
        notifyDataSetChanged()
    }
}
