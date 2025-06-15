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
import com.example.carrent.databinding.FragmentMercBinding
import com.example.carrent.models.Car

class BMWFragment : Fragment() {
    private var _binding: FragmentBMWBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val bmwCars = listOf(
        Car(
            brand = "BMW",
            model = "3 Series",
            year = 2021,
            price = "130₾/დღე",
            imageUrl = "https://www.bmw-georgia.com/content/dam/bmw/common/all-models/3-series/sedan/2022/navigation/bmw-3-series-sedan-lci-modelfinder.png",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 27000,
            horsepower = 255,
            location = "Tbilisi",
            ownerName = "Giorgi T.",
            ownerPhone = "+995 599 123 456",
            ownerEmail = "giorgi@example.com",
            fuelType = "Petrol",
            topSpeed = 250,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "5 Series",
            year = 2023,
            price = "180₾/დღე",
            imageUrl = "https://www.bmw.cc/content/dam/bmw/common/all-models/5-series/sedan/2023/ice2_Titan-Bronze_810-501.png",
            rating = 4.9f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 9000,
            horsepower = 335,
            location = "Batumi",
            ownerName = "Nino K.",
            ownerPhone = "+995 599 234 567",
            ownerEmail = "nino@example.com",
            fuelType = "Hybrid",
            topSpeed = 260,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "X5",
            year = 2022,
            price = "200₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/BMW-X5-Transparent-PNG.png",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 15000,
            horsepower = 375,
            location = "Kutaisi",
            ownerName = "Levan M.",
            ownerPhone = "+995 599 345 678",
            ownerEmail = "levan@example.com",
            fuelType = "Diesel",
            topSpeed = 240,
            bodyType = "SUV",
            doors = 5,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "X3",
            year = 2020,
            price = "140₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/BMW-X3-XDrive30e-PNG-Images-HD.png",
            rating = 4.5f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 32000,
            horsepower = 248,
            location = "Rustavi",
            ownerName = "Maka D.",
            ownerPhone = "+995 599 456 789",
            ownerEmail = "maka@example.com",
            fuelType = "Petrol",
            topSpeed = 230,
            bodyType = "SUV",
            doors = 5,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "7 Series",
            year = 2023,
            price = "230₾/დღე",
            imageUrl = "https://www.bmw.lk/content/dam/bmw/common/all-models/7-series/sedan/2021/navigation/bmw-7-series-sedan-modelfinder-stage2-890x501.png",
            rating = 5.0f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 5000,
            horsepower = 523,
            location = "Tbilisi",
            ownerName = "Irakli B.",
            ownerPhone = "+995 599 567 890",
            ownerEmail = "irakli@example.com",
            fuelType = "Electric",
            topSpeed = 250,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "X1",
            year = 2019,
            price = "110₾/დღე",
            imageUrl = "https://vehicle-images.dealerinspire.com/stock-images/chrome/e7b29d8fbc5a202180bce091c079f0f4.png",
            rating = 4.3f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 40000,
            horsepower = 228,
            location = "Gori",
            ownerName = "Sopo J.",
            ownerPhone = "+995 599 678 901",
            ownerEmail = "sopo@example.com",
            fuelType = "Petrol",
            topSpeed = 210,
            bodyType = "SUV",
            doors = 5,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "i4",
            year = 2022,
            price = "190₾/დღე",
            imageUrl = "https://prod.cosy.bmw.cloud/bmwweb/cosySec?COSY-EU-100-7331cqgv2Z7d%25i02uCaY3MuO2kOHUtWPfbYf6J7P10tLhu1XzWVo7puMLWFmdkAj5DOP4tpFZ8XgY1nTNIowJ4HO3zkyXq%25sGM8snpq6v6ODubLz2aKqfUKTjmB2fJj5DOP5Eagd%25kcWExHWpbl8FO2k3Hy2o24bDVTQBrXpF7gNlZ24riI1RoscpF4Hv5tX0KiIFJG7hBABHvIT9FoZO2JGvloImVgpT9GsLvcqUilo90yYw6bHsLoACeRThJ0yLOEo29qTACygN6U7mlOECUkaQp7sgNEbnRpy10UkNh5x64VAbnkq8WoqzOh5nmPeisagq857MjFpRUmP81D6BAxb7MPVYXz9Wh1DMztIc5eqVYDafvKUjmztYRSGBO67aftxdTAww1RSfWQoSD%25VxdSeZLd2uzWQdjcy9T3aeZQ6Kdv6XRjcZwBQGErx6Kc%252ZKz4WwBKupCHeFe%252B3inucIjA2y5oHBzcMNpAlZyQjKrPXYuNWC",
            rating = 4.9f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 12000,
            horsepower = 335,
            location = "Zugdidi",
            ownerName = "Tornike G.",
            ownerPhone = "+995 599 789 012",
            ownerEmail = "tornike@example.com",
            fuelType = "Electric",
            topSpeed = 225,
            bodyType = "Coupe",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "M3",
            year = 2021,
            price = "210₾/დღე",
            imageUrl = "https://images.dealer.com/ddc/vehicles/2021/BMW/M3/Sedan/perspective/front-left/2021_24.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 18000,
            horsepower = 473,
            location = "Telavi",
            ownerName = "Ana Z.",
            ownerPhone = "+995 599 890 123",
            ownerEmail = "ana@example.com",
            fuelType = "Petrol",
            topSpeed = 280,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "iX",
            year = 2023,
            price = "220₾/დღე",
            imageUrl = "https://www.bmw.com.my/content/dam/bmw/marketMY/bmw_com_my/discover-BMW/news/2024/update_on_ix.png",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 7000,
            horsepower = 516,
            location = "Batumi",
            ownerName = "David L.",
            ownerPhone = "+995 599 901 234",
            ownerEmail = "david@example.com",
            fuelType = "Electric",
            topSpeed = 250,
            bodyType = "SUV",
            doors = 5,
            seats = 5
        ),
        Car(
            brand = "BMW",
            model = "4 Series",
            year = 2020,
            price = "150₾/დღე",
            imageUrl = "https://www.bmw-georgia.com/content/dam/bmw/common/all-models/4-series/gran-coupe/2021/navigation/bmw-4-series-gran-coupe-modelfinder-890x501.png",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 25000,
            horsepower = 255,
            location = "Tbilisi",
            ownerName = "Lika N.",
            ownerPhone = "+995 599 012 345",
            ownerEmail = "lika@example.com",
            fuelType = "Petrol",
            topSpeed = 240,
            bodyType = "Coupe",
            doors = 2,
            seats = 4
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBMWBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(bmwCars) { selectedCar ->
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

        bmwRecyclerView.adapter = recyclerAdapter
        bmwRecyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

    companion object {
        @JvmStatic
        fun newInstance() = BMWFragment()
    }
}