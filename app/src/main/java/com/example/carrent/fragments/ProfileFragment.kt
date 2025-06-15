package com.example.carrent.fragments

import UserViewModel
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.carrent.LoginFragment
import com.example.carrent.MainFragment
import com.example.carrent.R
import com.example.carrent.databinding.DialogEditFieldBinding
import com.example.carrent.databinding.FragmentCarsBinding
import com.example.carrent.databinding.FragmentMainBinding
import com.example.carrent.databinding.FragmentProfileBinding
import com.google.firebase.database.FirebaseDatabase


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.firstname.observe(viewLifecycleOwner) { name ->
            val lastname = userViewModel.lastName.value ?: ""
            profileName.text = "$name $lastname"
        }

        userViewModel.lastName.observe(viewLifecycleOwner) { lastName ->
            val name = userViewModel.firstname.value ?: ""
            profileName.text = "$name $lastName"
        }

        userViewModel.email.observe(viewLifecycleOwner) { lastName ->
            val email = userViewModel.email.value ?: ""
            emailAddress.text = "$email"
        }

        userViewModel.phoneNumber.observe(viewLifecycleOwner) { lastName ->
            val phone = userViewModel.phoneNumber.value ?: ""
            if(phone.length == 9){
                val formattedPhone = "+995 ${phone.substring(0, 3)} ${phone.substring(3, 6)} ${phone.substring(6)}"
                phoneNumber.text = "$formattedPhone"
            }
        }

        logoutButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, LoginFragment())
                .addToBackStack(null)
                .commit()
        }

        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
        val userId = sharedPref.getString("logged_in_user_id", null)

        if (userId != null) {
            val moneySpentRef = com.google.firebase.database.FirebaseDatabase.getInstance()
                .getReference("User").child(userId).child("moneySpent")

            moneySpentRef.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                    val moneySpent = snapshot.getValue(Double::class.java) ?: 0.0

                    when {
                        moneySpent < 100 -> {
                            pointsText.text = "Points: %.0f / 100".format(moneySpent)
                            statusIcon.setImageResource(R.drawable.ic_bronze)
                            loyaltyLevel.text = "Bronze"

                            pointsProgressbar.progress = moneySpent.toInt()
                            pointsProgressbar.max = 100
//                            pointsProgressbar.progressTintList = ContextCompat.getColorStateList(requireContext(), R.color.bronze_color)

                        }
                        moneySpent < 1000 -> {
                            pointsText.text = "Points: %.0f / 1000".format(moneySpent)
                            statusIcon.setImageResource(R.drawable.ic_silver)
                            loyaltyLevel.text = "Silver"

                            pointsProgressbar.max = 1000
                            pointsProgressbar.progress = moneySpent.toInt()
//                            pointsProgressbar.progressTintList = ContextCompat.getColorStateList(requireContext(), R.color.silver_color)
                        }
                        moneySpent < 5000 -> {
                            pointsText.text = "Points: %.0f / 5000".format(moneySpent)
                            statusIcon.setImageResource(R.drawable.ic_gold)
                            loyaltyLevel.text = "Gold"

                            pointsProgressbar.max = 5000
                            pointsProgressbar.progress = moneySpent.toInt()
//                            pointsProgressbar.progressTintList = ContextCompat.getColorStateList(requireContext(), R.color.gold_color)

                        }
                        moneySpent < 15000 -> {
                            pointsText.text = "Points: %.0f / 15000".format(moneySpent)
                            statusIcon.setImageResource(R.drawable.ic_platinum)
                            loyaltyLevel.text = "Platinum"
                            pointsProgressbar.max = 15000
                            pointsProgressbar.progress = 10000
//                            pointsProgressbar.progressTintList = ContextCompat.getColorStateList(requireContext(), R.color.plat_color)

                        }
                        else -> {
                            pointsText.text = "Points: %.0f".format(moneySpent)
                            statusIcon.setImageResource(R.drawable.ic_diamond)
                            loyaltyLevel.text = "Diamond"

                            pointsProgressbar.max = 15000
                            pointsProgressbar.progress = 15000
//                            pointsProgressbar.progressTintList = ContextCompat.getColorStateList(requireContext(), R.color.diamond_color)

                        }
                    }

                }

                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                    pointsText.text = "Points: 0 / 100"
                }
            })
        }

        editPhoneNumberButton.setOnClickListener {
            if (userId != null) {
                showEditDialog("Edit Phone Number") { newPhone ->
                    updateUserFieldInFirebase(userId, "phone", newPhone)
                    phoneNumber.text = formatPhone(newPhone)
                }
            }
        }

        editEmailButton.setOnClickListener {
            if (userId != null) {
                showEditDialog("Edit Email") { newEmail ->
                    updateUserFieldInFirebase(userId, "email", newEmail)
                    emailAddress.text = newEmail
                }
            }
        }
    }

    private fun showEditDialog(title: String, onConfirm: (String) -> Unit) {
        val dialogBinding = DialogEditFieldBinding.inflate(layoutInflater)
        dialogBinding.dialogTitle.text = title

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.saveButton.setOnClickListener {
            val newValue = dialogBinding.editTextDialog.text.toString().trim()
            if (newValue.isNotEmpty()) {
                onConfirm(newValue)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }





    private fun formatPhone(phone: String): String {
        return if (phone.length == 9) {
            "+995 ${phone.substring(0, 3)} ${phone.substring(3, 6)} ${phone.substring(6)}"
        } else phone
    }

    private fun updateUserFieldInFirebase(userId: String, field: String, value: String) {
        val userRef = FirebaseDatabase.getInstance().getReference("User").child(userId)
        userRef.child(field).setValue(value)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "$field updated!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to update $field: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}