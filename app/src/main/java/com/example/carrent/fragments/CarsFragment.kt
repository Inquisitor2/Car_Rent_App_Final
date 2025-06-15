package com.example.carrent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.carrent.R
import com.example.carrent.adapters.ViewPagerAdapter
import com.example.carrent.databinding.FragmentCarsBinding
import com.example.carrent.fragmentsVP.AudiFragment
import com.example.carrent.fragmentsVP.BMWFragment
import com.example.carrent.fragmentsVP.FerrariFragment
import com.example.carrent.fragmentsVP.FordFragment
import com.example.carrent.fragmentsVP.LambFragment
import com.example.carrent.fragmentsVP.MercFragment
import com.example.carrent.fragmentsVP.NissanFragment
import com.example.carrent.fragmentsVP.ToyotaFragment
import com.google.android.material.tabs.TabLayoutMediator


class CarsFragment : Fragment() {

    private var _binding : FragmentCarsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewPagerAdapter: ViewPagerAdapter

    private val fList = listOf(
        MercFragment(),
        BMWFragment(),
        FordFragment(),
        AudiFragment(),
        ToyotaFragment(),
        NissanFragment(),
        FerrariFragment(),
        LambFragment(),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            if (isAdded) {
                viewPagerAdapter = ViewPagerAdapter(requireActivity(), fList)
                viewPagerCars.adapter = viewPagerAdapter
                val tList = listOf(
                    ContextCompat.getDrawable(requireContext(), R.drawable.merc),
                    ContextCompat.getDrawable(requireContext(), R.drawable.bmw),
                    ContextCompat.getDrawable(requireContext(), R.drawable.ford),
                    ContextCompat.getDrawable(requireContext(), R.drawable.audi),
                    ContextCompat.getDrawable(requireContext(), R.drawable.toyota),
                    ContextCompat.getDrawable(requireContext(), R.drawable.nissan),
                    ContextCompat.getDrawable(requireContext(), R.drawable.ferrari),
                    ContextCompat.getDrawable(requireContext(), R.drawable.lamb),
                )
                TabLayoutMediator(tabLayoutCarLogos, viewPagerCars) { tab, position ->
                    tab.icon = tList[position]
                }.attach()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CarsFragment()
    }
}