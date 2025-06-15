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
import com.example.carrent.databinding.FragmentBMWBinding
import com.example.carrent.databinding.FragmentFerrariBinding
import com.example.carrent.models.Car

class FerrariFragment : Fragment() {
    private var _binding: FragmentFerrariBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val ferrariCars = listOf(
        Car(
            brand = "Ferrari",
            model = "488 GTB",
            year = 2018,
            price = "850₾/დღე",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-red-ferrari-488-gtb-carcarferrarivehicletransport-961524661486wvwf1.png",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 15000,
            horsepower = 670,
            location = "Tbilisi",
            ownerName = "Giorgi Khutsishvili",
            ownerPhone = "+995 599 123 456",
            ownerEmail = "giorgi.khutsishvili@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 330,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Ferrari",
            model = "Portofino",
            year = 2020,
            price = "900₾/დღე",
            imageUrl = "https://cdn.ferrari.com/cms/network/media/img/resize/5f60fede966ae519cbd62beb-ferrari-portofino-m-design-hotspot-mob-new_3?",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 11000,
            horsepower = 600,
            location = "Batumi",
            ownerName = "Nino Goguadze",
            ownerPhone = "+995 591 987 654",
            ownerEmail = "nino.goguadze@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 320,
            bodyType = "Convertible",
            doors = 2,
            seats = 4
        ),
        Car(
            brand = "Ferrari",
            model = "F8 Tributo",
            year = 2022,
            price = "1200₾/დღე",
            imageUrl = "https://www.pngarts.com/files/8/Ferrari-F8-Tributo-Transparent-Images.png",
            rating = 5.0f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 7000,
            horsepower = 710,
            location = "Kutaisi",
            ownerName = "Irakli Vashakidze",
            ownerPhone = "+995 598 234 789",
            ownerEmail = "irakli.vashakidze@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 340,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Ferrari",
            model = "Roma",
            year = 2021,
            price = "1000₾/დღე",
            imageUrl = "https://www.motortrend.com/uploads/2021/03/2021-Ferrari-Roma-1.png",
            rating = 4.9f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 9000,
            horsepower = 620,
            location = "Rustavi",
            ownerName = "Gvantsa Mchedlishvili",
            ownerPhone = "+995 555 876 321",
            ownerEmail = "gvantsa.mchedlishvili@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 320,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Ferrari",
            model = "SF90 Stradale",
            year = 2023,
            price = "1500₾/დღე",
            imageUrl = "https://www.pngarts.com/files/8/Ferrari-SF90-Stradale-PNG-Image-Background.png",
            rating = 5.0f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 4000,
            horsepower = 1000,
            location = "Gori",
            ownerName = "Tamar Beridze",
            ownerPhone = "+995 577 654 321",
            ownerEmail = "tamar.beridze@ferrarige.ge",
            fuelType = "Hybrid Petrol",
            topSpeed = 340,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Ferrari",
            model = "California T",
            year = 2019,
            price = "800₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Ferrari-California-T-Transparent-Image.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 18000,
            horsepower = 560,
            location = "Zugdidi",
            ownerName = "Sandro Tsereteli",
            ownerPhone = "+995 595 321 654",
            ownerEmail = "sandro.tsereteli@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 310,
            bodyType = "Convertible",
            doors = 2,
            seats = 4
        ),
        Car(
            brand = "Ferrari",
            model = "458 Italia",
            year = 2017,
            price = "750₾/დღე",
            imageUrl = "https://platform.cstatic-images.com/xxlarge/in/v2/stock_photos/b5ed207f-ea2f-47fb-8166-cd47f33e5df9/4d820403-a002-4ce7-9fab-e8707a328571.png",
            rating = 4.8f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 23000,
            horsepower = 570,
            location = "Telavi",
            ownerName = "Lela Vashakidze",
            ownerPhone = "+995 593 444 555",
            ownerEmail = "lela.vashakidze@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 325,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Ferrari",
            model = "GTC4Lusso",
            year = 2020,
            price = "950₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Ferrari-GTC4Lusso-Transparent-Free-PNG.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 13000,
            horsepower = 680,
            location = "Poti",
            ownerName = "Nika Javakhishvili",
            ownerPhone = "+995 597 556 789",
            ownerEmail = "nika.javakhishvili@ferrarige.ge",
            fuelType = "Petrol",
            topSpeed = 320,
            bodyType = "Shooting Brake",
            doors = 4,
            seats = 4
        ),
        Car(
            brand = "Ferrari",
            model = "LaFerrari",
            year = 2016,
            price = "2000₾/დღე",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-red-ferrari-laferrari-carcarferrarivehicletransport-961524666256ces49.png",
            rating = 5.0f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 5000,
            horsepower = 950,
            location = "Tbilisi",
            ownerName = "Dato Mgeladze",
            ownerPhone = "+995 555 999 888",
            ownerEmail = "dato.mgeladze@ferrarige.ge",
            fuelType = "Hybrid Petrol",
            topSpeed = 350,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFerrariBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(ferrariCars) { selectedCar ->
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

        ferrariRecyclerView.adapter = recyclerAdapter
        ferrariRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance() = FerrariFragment()
    }
}