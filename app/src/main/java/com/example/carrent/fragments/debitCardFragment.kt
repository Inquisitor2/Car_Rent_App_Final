package com.example.carrent.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrent.MainFragment
import com.example.carrent.R
import com.example.carrent.adapters.RecyclerViewAdapter
import com.example.carrent.adapters.debitCardRVA
import com.example.carrent.databinding.DialogAddBalanceBinding
import com.example.carrent.databinding.DialogAddCardBinding
import com.example.carrent.databinding.FragmentDebitCardBinding
import com.example.carrent.databinding.FragmentHomeBinding
import com.example.carrent.models.bankCard
import com.google.firebase.database.FirebaseDatabase

class debitCardFragment : Fragment() {

    private var _binding: FragmentDebitCardBinding?= null
    private val binding get() = _binding!!

    private lateinit var debitCardRVA: debitCardRVA
    private val cardsList = mutableListOf<bankCard>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDebitCardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =with(binding){
        super.onViewCreated(view, savedInstanceState)
//        addCardButton.setOnClickListener {
//            val dialogBinding = DialogAddCardBinding.inflate(layoutInflater)
//
//            val dialog = AlertDialog.Builder(requireContext())
//                .setView(dialogBinding.root)
//                .setPositiveButton("Save", null)
//                .setNegativeButton("Cancel", null)
//                .create()
//
//            dialog.setOnShowListener {
//                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//                val saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
//                saveButton.setOnClickListener {
//                    val ownerName = dialogBinding.inputOwnerName.text.toString()
//                    val cardNumber = dialogBinding.inputCardNumber.text.toString()
//                    val expiryDate = dialogBinding.inputExpiryDate.text.toString()
//                    val cvv = dialogBinding.inputCVV.text.toString()
//
//                    val cardType = when (dialogBinding.cardTypeGroup.checkedRadioButtonId) {
//                        R.id.visaOption -> "Visa"
//                        R.id.mastercardOption -> "MasterCard"
//                        else -> ""
//                    }
//
//                    if (ownerName.isNotBlank() && cardNumber.length == 16 && expiryDate.isNotBlank() && cvv.length in 3..4 && cardType.isNotBlank()) {
//                        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
//                        val userId = sharedPref.getString("logged_in_user_id", null)
//
//                        if (userId != null) {
//                            val newCard = bankCard(
//                                bankName = "YourBank",
//                                cardOwner = ownerName,
//                                cardNumber = cardNumber,
//                                expirationDate = expiryDate,
//                                cvv = cvv,
//                                cardType = cardType
//                            )
//                            val databaseRef = FirebaseDatabase.getInstance().getReference("User")
//                                .child(userId)
//                                .child("cards")
//                            val newCardId = databaseRef.push().key
//
//                            if (newCardId != null) {
//                                databaseRef.child(newCardId).setValue(newCard)
//                                    .addOnSuccessListener {
//                                        Toast.makeText(requireContext(), "Card added!", Toast.LENGTH_SHORT).show()
//                                        dialog.dismiss()
//                                        loadCardsFromFirebase()
//                                    }
//                                    .addOnFailureListener {
//                                        Toast.makeText(requireContext(), "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
//                                    }
//                            }
//                        }
//                    } else {
//                        Toast.makeText(requireContext(), "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            dialog.show()
//        }

        addCardButton.setOnClickListener {
            val dialogBinding = DialogAddCardBinding.inflate(layoutInflater)

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

            dialog.setOnShowListener {
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                dialogBinding.cancelButton.setOnClickListener {
                    dialog.dismiss()
                }

                dialogBinding.saveButton.setOnClickListener {
                    val ownerName = dialogBinding.inputOwnerName.text.toString()
                    val cardNumber = dialogBinding.inputCardNumber.text.toString()
                    val expiryDate = dialogBinding.inputExpiryDate.text.toString()
                    val cvv = dialogBinding.inputCVV.text.toString()

                    val cardType = when (dialogBinding.cardTypeGroup.checkedRadioButtonId) {
                        R.id.visaOption -> "Visa"
                        R.id.mastercardOption -> "MasterCard"
                        else -> ""
                    }

                    if (ownerName.isNotBlank() && cardNumber.length == 16 && expiryDate.isNotBlank() && cvv.length in 3..4 && cardType.isNotBlank()) {
                        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
                        val userId = sharedPref.getString("logged_in_user_id", null)

                        if (userId != null) {
                            val newCard = bankCard(
                                bankName = "YourBank",
                                cardOwner = ownerName,
                                cardNumber = cardNumber,
                                expirationDate = expiryDate,
                                cvv = cvv,
                                cardType = cardType
                            )
                            val databaseRef = FirebaseDatabase.getInstance().getReference("User").child(userId).child("cards")
                            val newCardId = databaseRef.push().key
                            if (newCardId != null) {
                                databaseRef.child(newCardId).setValue(newCard)
                                    .addOnSuccessListener {
                                        Toast.makeText(requireContext(), "Card added!", Toast.LENGTH_SHORT).show()
                                        loadCardsFromFirebase()
                                        dialog.dismiss()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(requireContext(), "Failed to add card: ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            dialog.show()
        }

        debitCardRVA = debitCardRVA(cardsList) { clickedCard ->
            showAddBalanceDialog(clickedCard)
        }
        binding.cardsRecyclerView.adapter = debitCardRVA
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        loadCardsFromFirebase()

        backButton2.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.placeHolder, MainFragment())
                .commit()
        }
    }

    private fun loadCardsFromFirebase() {
        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
        val userId = sharedPref.getString("logged_in_user_id", null)
        if (userId == null) return

        val databaseRef = FirebaseDatabase.getInstance().getReference("User").child(userId).child("cards")
        databaseRef.addListenerForSingleValueEvent(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                cardsList.clear()
                for (cardSnapshot in snapshot.children) {
                    val card = cardSnapshot.getValue(bankCard::class.java)
                    card?.let { cardsList.add(it) }
                }
                debitCardRVA.updateData(cardsList)
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                Toast.makeText(requireContext(), "Failed to load cards: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAddBalanceDialog(card: bankCard) {
        val dialogBinding = DialogAddBalanceBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialog.setOnShowListener {
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            dialogBinding.saveButton.setOnClickListener {
                val amountText = dialogBinding.inputBalance.text.toString()
                val amount = amountText.toDoubleOrNull()

                if (amount != null && amount > 0) {
                    addBalanceToUser(amount)
                    dialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialog.show()
    }



    private fun addBalanceToUser(amount: Double) {
        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", android.content.Context.MODE_PRIVATE)
        val userId = sharedPref.getString("logged_in_user_id", null)
        if (userId == null) return

        val userRef = FirebaseDatabase.getInstance().getReference("User").child(userId)

        userRef.child("balance").addListenerForSingleValueEvent(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                val currentBalance = snapshot.getValue(Double::class.java) ?: 0.0
                val newBalance = currentBalance + amount

                userRef.child("balance").setValue(newBalance)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Balance updated! New balance: $newBalance", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Failed to update balance: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = debitCardFragment()
    }
}