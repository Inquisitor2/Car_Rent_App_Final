package com.example.carrent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrent.databinding.FragmentMainBinding
import com.example.carrent.fragments.CarsFragment
import com.example.carrent.fragments.HomeFragment
import com.example.carrent.fragments.ProfileFragment
import com.example.carrent.fragments.ResFragment

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
            private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    private fun loadFragment(f : Fragment){
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.navHostFragment, f)
                .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        loadFragment(HomeFragment.newInstance())
        bottomNavMenu.setOnItemSelectedListener {
            when(it.itemId){
                    R.id.home -> {
                loadFragment(HomeFragment.newInstance())
                true
            }
                R.id.cars -> {
                    loadFragment(CarsFragment())
                    true
                }
                R.id.reservation -> {
                    loadFragment(ResFragment.newInstance())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment.newInstance())
                    true
                }
                else -> {
                    loadFragment(HomeFragment.newInstance())
                    true
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}