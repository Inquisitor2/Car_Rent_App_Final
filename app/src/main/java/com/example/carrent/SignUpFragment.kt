package com.example.carrent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carrent.databinding.FragmentSignUpBinding
import com.example.carrent.models.User
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        super.onViewCreated(view, savedInstanceState)
        signUpButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val lastname = binding.editTextLastName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val phone = binding.editTextPhoneNumber.text.toString()
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()
            val balance = 0
            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (name.isBlank() || lastname.isBlank() || email.isBlank() || phone.isBlank() || username.isBlank() || password.isBlank()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id = FirebaseDatabase.getInstance().reference.push().key ?: return@setOnClickListener
            val user = User(name,lastname,email,phone,username,password, emptyList(),balance)
            FirebaseDatabase.getInstance().getReference("User")
                .child(id)
                .setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "User registered!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed to register: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            parentFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}