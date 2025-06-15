package com.example.carrent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.carrent.databinding.FragmentCarDetailBinding
import com.example.carrent.databinding.FragmentHomeBinding
import com.example.carrent.models.Car
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CarDetailFragment : Fragment() {

    private var _binding: FragmentCarDetailBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarDetailBinding.inflate(layoutInflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit =with(binding){
        super.onViewCreated(view, savedInstanceState)

        val brand = arguments?.getString("brand") ?: ""
        val model = arguments?.getString("model") ?: ""
        val year = arguments?.getInt("year") ?: 0
        val price = arguments?.getString("price") ?: ""
        val imageUrl = arguments?.getString("imageUrl") ?: ""
        val rating = arguments?.getFloat("rating") ?: 0f
        val mileage = arguments?.getInt("mileage") ?: 0
        val horsepower = arguments?.getInt("horsepower") ?: 0
        val location = arguments?.getString("location") ?: ""
        val doors = arguments?.getInt("doors") ?: 0
        val seats = arguments?.getInt("seats") ?: 0
        val fuelType = arguments?.getString("fuelType") ?: ""
        val topSpeed = arguments?.getInt("topSpeed") ?: 0
        val bodyType = arguments?.getString("bodyType") ?: ""
        val ownerName = arguments?.getString("ownerName") ?: ""
        val ownerPhone = arguments?.getString("ownerPhone") ?: ""
        val ownerEmail = arguments?.getString("ownerEmail") ?: ""
        val isHotDeal = arguments?.getBoolean("isHotDeal") ?: false
        val isNewModel = arguments?.getBoolean("isNewModel") ?: false

        hotDealBadge.visibility = if (isHotDeal) View.VISIBLE else View.GONE
        newModelBadge.visibility = if (isNewModel) View.VISIBLE else View.GONE

        carDetailsTitle.text = brand + " " + model
        carYearPlaceHolder.text = year.toString()
        carPricePlaceHolder.text = price
        mileagePlaceHolder.text = "$mileage km"
        carDetailHorsepowerPlace.text = "$horsepower HP"
        carLocationPlaceHolder.text = location
        ratingText.text = "$rating"
        carDoorsPlaceHolder.text = doors.toString()
        seatsPlaceHolder.text = seats.toString()
        fuelTypePlaceholder.text = fuelType
        topSpeedPlaceHolder.text = topSpeed.toString()
        cardBodyTypePlaceholder.text = bodyType
        EmailRenter.text = ownerEmail
        textView6.text = ownerName
        PhoneNumberRenter.text = ownerPhone

        Glide.with(requireContext())
            .load(imageUrl)
            .fitCenter()
            .into(carImagePlaceHolder)

        backButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.placeHolder, MainFragment())
                .commit()
        }

        val currentCar = Car(
            brand = brand,
            model = model,
            year = year,
            price = price,
            imageUrl = imageUrl,
            rating = rating,
            mileage = mileage,
            horsepower = horsepower,
            location = location,
            doors = doors,
            seats = seats,
            fuelType = fuelType,
            topSpeed = topSpeed,
            bodyType = bodyType,
            ownerName = ownerName,
            ownerPhone = ownerPhone,
            ownerEmail = ownerEmail,
            isHotDeal = isHotDeal,
            isNewModel = isNewModel
        )

        rentButton.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
            val userId = sharedPref.getString("logged_in_user_id", null)

            if (userId == null) {
                Toast.makeText(requireContext(), "User ID not found. Please login again.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userRef = FirebaseDatabase.getInstance().getReference("User").child(userId)
            val rentedCarsRef = userRef.child("rented_cars")
            val balanceRef = userRef.child("balance")

            balanceRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val currentBalance = snapshot.getValue(Double::class.java) ?: 0.0
                    val priceString = currentCar.price
                    val rentPrice = priceString.filter { it.isDigit() || it == '.' }.toDoubleOrNull()

                    if (rentPrice == null) {
                        Toast.makeText(requireContext(), "Invalid price format", Toast.LENGTH_SHORT).show()
                        return
                    }

                    if (currentBalance >= rentPrice) {
                        rentedCarsRef.push().setValue(currentCar)
                            .addOnSuccessListener {
                                val newBalance = currentBalance - rentPrice
                                val moneySpentRef = userRef.child("moneySpent")

                                balanceRef.setValue(newBalance)
                                    .addOnSuccessListener {
                                        moneySpentRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                val currentSpent = snapshot.getValue(Double::class.java) ?: 0.0
                                                val newSpent = currentSpent + rentPrice

                                                moneySpentRef.setValue(newSpent)
                                                    .addOnSuccessListener {
                                                        Toast.makeText(requireContext(), "Car rented! Balance: ₾${"%.2f".format(newBalance)}", Toast.LENGTH_SHORT).show()
                                                    }
                                                    .addOnFailureListener {
                                                        Toast.makeText(requireContext(), "Failed to update money spent.", Toast.LENGTH_SHORT).show()
                                                    }
                                            }

                                            override fun onCancelled(error: DatabaseError) {
                                                Toast.makeText(requireContext(), "Error reading moneySpent: ${error.message}", Toast.LENGTH_SHORT).show()
                                            }
                                        })
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(requireContext(), "Car rented but failed to update balance.", Toast.LENGTH_SHORT).show()
                                    }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(requireContext(), "Failed to rent car: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        Toast.makeText(requireContext(), "Balance is too low to rent this car.", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Failed to check balance: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }



    }


    companion object {
        @JvmStatic
        fun newInstance() = CarDetailFragment()
    }
}