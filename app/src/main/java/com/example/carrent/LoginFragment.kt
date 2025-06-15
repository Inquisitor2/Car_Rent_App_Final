package com.example.carrent

import UserViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.carrent.databinding.FragmentLoginBinding
import com.example.carrent.fragments.HomeFragment
import com.example.carrent.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString().trim()
            val password = editTextTextPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbRef = FirebaseDatabase.getInstance().getReference("User")
            dbRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            var matched = false
                            for (userSnap in snapshot.children) {
                                val user = userSnap.getValue(User::class.java)
                                if (user?.password == password) {
                                    matched = true

                                    val userId = userSnap.key ?: ""
                                    val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
                                    sharedPref.edit().putString("logged_in_user_id", userId).apply()

                                    Toast.makeText(
                                        requireContext(),
                                        "Login successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
                                    userViewModel.firstname.value = user.name
                                    userViewModel.lastName.value = user.lastname
                                    userViewModel.email.value = user.email
                                    userViewModel.phoneNumber.value = user.phone
                                    userViewModel.balance.value = user.balance.toString()

                                    parentFragmentManager.beginTransaction()
                                            .replace(R.id.placeHolder, MainFragment())
                                            .addToBackStack(null)
                                            .commit()

                                    break
                                }
                            }
                            if (!matched) {
                                Toast.makeText(
                                    requireContext(),
                                    "Incorrect password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "No user found with this email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
        LoginPageSignUpButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}