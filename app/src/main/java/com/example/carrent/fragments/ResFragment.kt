package com.example.carrent.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrent.adapters.resRVA
import com.example.carrent.databinding.FragmentResBinding
import com.example.carrent.models.Car
import com.google.firebase.database.*

class ResFragment : Fragment() {

    private var _binding: FragmentResBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: resRVA
    private val rentedCarsList = mutableListOf<Car>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
        val userId = sharedPref.getString("logged_in_user_id", null)

        if (userId == null) {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
            return
        }

        val dbRef = FirebaseDatabase.getInstance()
            .getReference("User")
            .child(userId)
            .child("rented_cars")

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                rentedCarsList.clear()
                for (carSnapshot in snapshot.children) {
                    val car = carSnapshot.getValue(Car::class.java)
                    car?.let { rentedCarsList.add(it) }
                }

                recyclerAdapter = resRVA(rentedCarsList) { selectedCar ->
                    showCancelRentDialog(selectedCar,userId)
                }
                binding.reservationRV.adapter = recyclerAdapter
                binding.reservationRV.layoutManager = LinearLayoutManager(requireContext())
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to load rented cars: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })


    }

    private fun showCancelRentDialog(car: Car,userId: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Cancel Rent")
            .setMessage("Do you want to cancel rent for ${car.brand} ${car.model}?")
            .setPositiveButton("Yes") { dialog, _ ->
                cancelRent(userId, car)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun cancelRent(userId: String, car: Car) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("User").child(userId)
        val rentedCarsRef = userRef.child("rented_cars")

        rentedCarsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var foundKey: String? = null
                for (carSnapshot in snapshot.children) {
                    val rentedCar = carSnapshot.getValue(Car::class.java)
                    if (rentedCar != null &&
                        rentedCar.model == car.model &&
                        rentedCar.brand == car.brand &&
                        rentedCar.location == car.location
                    ) {
                        foundKey = carSnapshot.key
                        break
                    }
                }
                if (foundKey != null) {
                    rentedCarsRef.child(foundKey).removeValue()
                        .addOnSuccessListener {
                            val priceStr = car.price.replace("[^\\d.]".toRegex(), "")
                            val price = priceStr.toDoubleOrNull() ?: 0.0
                            updateUserBalance(userRef, price)
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Failed to cancel rent", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(requireContext(), "Car not found in rented cars", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to access database", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun updateUserBalance(userRef: DatabaseReference, priceToAdd: Double) {
        userRef.child("balance").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentBalance = snapshot.getValue(Double::class.java) ?: 0.0
                val newBalance = currentBalance + priceToAdd

                userRef.child("balance").setValue(newBalance).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Rent cancelled and balance updated", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed to update balance", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to update balance", Toast.LENGTH_SHORT).show()
            }
        })
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResFragment()
    }
}
