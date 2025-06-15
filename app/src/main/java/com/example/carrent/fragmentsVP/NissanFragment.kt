package com.example.carrent.fragmentsVP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrent.CarDetailFragment
import com.example.carrent.R
import com.example.carrent.adapters.vpRVA
import com.example.carrent.databinding.FragmentFerrariBinding
import com.example.carrent.databinding.FragmentNissanBinding
import com.example.carrent.models.Car


class NissanFragment : Fragment() {
    private var _binding:  FragmentNissanBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val carList = listOf(
        Car(
            brand = "Nissan",
            model = "Sentra",
            year = 2023,
            price = "₾135/day",
            imageUrl = "https://inv.assets.sincrod.com/ChromeColorMatch/us/TRANSPARENT_cc_2024NIC060014_01_1280_QM1.png",
            rating = 4.2f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 15000,
            horsepower = 149,
            location = "Tbilisi",
            ownerName = "Giorgi Kapanadze",
            ownerPhone = "+995 555 123 456",
            ownerEmail = "giorgi.kapanadze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 190,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Altima",
            year = 2022,
            price = "₾162/day",
            imageUrl = "https://di-uploads-pod46.dealerinspire.com/nissanandalusia/uploads/2023/02/mlp-img-top-2023-altima-temp.png",
            rating = 4.5f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 20000,
            horsepower = 188,
            location = "Batumi",
            ownerName = "Luka Beridze",
            ownerPhone = "+995 577 234 567",
            ownerEmail = "luka.beridze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 210,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Rogue",
            year = 2024,
            price = "₾189/day",
            imageUrl = "https://images.carprices.com/pricebooks_data/usa/colorized/2025/Nissan/View2/Rogue/Platinum/22815_XKJ.png",
            rating = 4.7f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 5000,
            horsepower = 201,
            location = "Kutaisi",
            ownerName = "Nino Chkheidze",
            ownerPhone = "+995 599 345 678",
            ownerEmail = "nino.chkheidze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 200,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Pathfinder",
            year = 2023,
            price = "₾230/day",
            imageUrl = "https://cdn-ds.com/media/sz_232746/Nissan/2025_Nissan_Pathfinder/trims/2025-pathfinder-RCJ-deeop-ocean-blue-sv.png",
            rating = 4.6f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 12000,
            horsepower = 284,
            location = "Tbilisi",
            ownerName = "Tamar Gogia",
            ownerPhone = "+995 551 456 789",
            ownerEmail = "tamar.gogia@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 220,
            bodyType = "SUV",
            doors = 4,
            seats = 7
        ),
        Car(
            brand = "Nissan",
            model = "Juke",
            year = 2021,
            price = "₾149/day",
            imageUrl = "https://pngimg.com/d/nissan_PNG13.png",
            rating = 4.3f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 25000,
            horsepower = 117,
            location = "Batumi",
            ownerName = "Davit Mchedlishvili",
            ownerPhone = "+995 593 567 890",
            ownerEmail = "davit.mchedlishvili@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 180,
            bodyType = "Crossover",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Leaf",
            year = 2024,
            price = "₾176/day",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Nissan-Leaf-Transparent-Free-PNG.png",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 3000,
            horsepower = 147,
            location = "Tbilisi",
            ownerName = "Ana Shubitidze",
            ownerPhone = "+995 595 678 901",
            ownerEmail = "ana.shubitidze@gmail.com",
            fuelType = "Electric",
            topSpeed = 155,
            bodyType = "Hatchback",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Murano",
            year = 2022,
            price = "₾203/day",
            imageUrl = "https://cdn-ds.com/media/sz_41407/Nissan/2023_Nissan_Murano/23SV-Midnight_AWD.png",
            rating = 4.4f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 18000,
            horsepower = 260,
            location = "Kutaisi",
            ownerName = "Irakli Tsiklauri",
            ownerPhone = "+995 557 789 012",
            ownerEmail = "irakli.tsiklauri@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 210,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Versa",
            year = 2023,
            price = "₾122/day",
            imageUrl = "https://static.foxdealer.com/global/2023/10/2024_versa_sv_6.png",
            rating = 4.1f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 14000,
            horsepower = 122,
            location = "Batumi",
            ownerName = "Mariam Japaridze",
            ownerPhone = "+995 599 890 123",
            ownerEmail = "mariam.japaridze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 185,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "X-Trail",
            year = 2024,
            price = "₾216/day",
            imageUrl = "https://www-europe.nissan-cdn.net/content/dam/Nissan/gb/vehicles/x-trail-my24-assets-webp/23TDIEULHD_XT_GSR_N-Connecta_NBL_18AW_001.webp",
            rating = 4.6f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 4000,
            horsepower = 181,
            location = "Tbilisi",
            ownerName = "Zurab Khurtsidze",
            ownerPhone = "+995 555 901 234",
            ownerEmail = "zurab.khurtsidze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 205,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Nissan",
            model = "Qashqai",
            year = 2022,
            price = "₾162/day",
            imageUrl = "https://www-europe.nissan-cdn.net/content/dam/Nissan/nissan_europe/Configurator/Qashqai-my24/configurator-webp/QQMC-ICE-N-Connecta.png.webp",
            rating = 4.3f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 22000,
            horsepower = 158,
            location = "Kutaisi",
            ownerName = "Eka Gvazava",
            ownerPhone = "+995 577 012 345",
            ownerEmail = "eka.gvazava@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 195,
            bodyType = "Crossover",
            doors = 4,
            seats = 5
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNissanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(carList) { selectedCar ->
            val fragment = CarDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("brand", selectedCar.brand)
                    putString("model", selectedCar.model)
                    putInt("year", selectedCar.year)
                    putString("price", selectedCar.price)
                    putString("imageUrl", selectedCar.imageUrl)
                    putFloat("rating", selectedCar.rating)
                    putBoolean("isHotDeal", selectedCar.isHotDeal)
                    putBoolean("isNewModel", selectedCar.isNewModel)
                    putInt("mileage", selectedCar.mileage)
                    putInt("horsepower", selectedCar.horsepower)
                    putString("location", selectedCar.location)
                    putString("ownerName", selectedCar.ownerName)
                    putString("ownerPhone", selectedCar.ownerPhone)
                    putString("ownerEmail", selectedCar.ownerEmail)
                    putString("fuelType", selectedCar.fuelType)
                    putInt("topSpeed", selectedCar.topSpeed)
                    putString("bodyType", selectedCar.bodyType)
                    putInt("doors", selectedCar.doors)
                    putInt("seats", selectedCar.seats)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, fragment)
                .addToBackStack(null)
                .commit()
        }

        nissanRecyclerView.adapter = recyclerAdapter
        nissanRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance() = NissanFragment()
    }
}