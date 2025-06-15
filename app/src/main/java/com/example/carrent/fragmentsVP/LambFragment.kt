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
import com.example.carrent.databinding.FragmentLambBinding
import com.example.carrent.models.Car

class LambFragment : Fragment() {
    private var _binding: FragmentLambBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val lamborghiniCars = listOf(
        Car(
            brand = "Lamborghini",
            model = "Huracán EVO",
            year = 2021,
            price = "1300₾/დღე",
            imageUrl = "https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/search/model/2025/huracan_evo.png",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 9000,
            horsepower = 640,
            location = "Tbilisi",
            ownerName = "Giorgi Kalandadze",
            ownerPhone = "+995 599 123 789",
            ownerEmail = "giorgi.kalandadze@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 325,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Aventador S",
            year = 2019,
            price = "1500₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Lamborghini-Aventador-S-PNG-Photos.png",
            rating = 5.0f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 13000,
            horsepower = 730,
            location = "Batumi",
            ownerName = "Nino Tsiklauri",
            ownerPhone = "+995 591 234 567",
            ownerEmail = "nino.tsiklauri@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 350,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Urus",
            year = 2022,
            price = "1400₾/დღე",
            imageUrl = "https://file.aiquickdraw.com/imgcompressed/img/compressed_f8a5d008169ca16c21e0116352a91e8d.webp",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 8000,
            horsepower = 650,
            location = "Kutaisi",
            ownerName = "Irakli Gogoladze",
            ownerPhone = "+995 598 876 123",
            ownerEmail = "irakli.gogoladze@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 305,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Lamborghini",
            model = "Huracán Performante",
            year = 2020,
            price = "1350₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Lamborghini-Huracan-Spyder-Performante-PNG-Photos.png",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 11000,
            horsepower = 640,
            location = "Rustavi",
            ownerName = "Tamar Mchedlishvili",
            ownerPhone = "+995 555 432 789",
            ownerEmail = "tamar.mchedlishvili@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 325,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Sian FKP 37",
            year = 2023,
            price = "2500₾/დღე",
            imageUrl = "https://static.wikia.nocookie.net/forzamotorsport/images/c/c2/FM23_Lamborghini_Sian_FKP_37.png/revision/latest?cb=20240211004233",
            rating = 5.0f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 3000,
            horsepower = 819,
            location = "Zugdidi",
            ownerName = "Salome Beridze",
            ownerPhone = "+995 577 987 654",
            ownerEmail = "salome.beridze@lamborghinige.ge",
            fuelType = "Hybrid Petrol",
            topSpeed = 350,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Centenario",
            year = 2017,
            price = "2300₾/დღე",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-black-lamborghini-centenario-lp-770-4-carcarvehicletransportlamborghini-961524643309tcjkp.png",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 4000,
            horsepower = 770,
            location = "Gori",
            ownerName = "Dato Nadiradze",
            ownerPhone = "+995 593 654 321",
            ownerEmail = "dato.nadiradze@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 350,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Gallardo",
            year = 2016,
            price = "900₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Lamborghini-Galardo-PNG-Images-HD.png",
            rating = 4.6f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 25000,
            horsepower = 570,
            location = "Telavi",
            ownerName = "Lasha Japaridze",
            ownerPhone = "+995 595 321 432",
            ownerEmail = "lasha.japaridze@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 325,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Murciélago",
            year = 2015,
            price = "850₾/დღე",
            imageUrl = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/c594a622-6b80-4d66-8784-f52c4e04c90c/d8dcgvk-680ce9ac-6bd5-413f-aa95-42dd7131b048.png/v1/fill/w_1600,h_584/2005_lamborghini_murcielago_by_fast2ghl_d8dcgvk-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2M1OTRhNjIyLTZiODAtNGQ2Ni04Nzg0LWY1MmM0ZTA0YzkwY1wvZDhkY2d2ay02ODBjZTlhYy02YmQ1LTQxM2YtYWE5NS00MmRkNzEzMWIwNDgucG5nIiwiaGVpZ2h0IjoiPD01ODQiLCJ3aWR0aCI6Ijw9MTYwMCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS53YXRlcm1hcmsiXSwid21rIjp7InBhdGgiOiJcL3dtXC9jNTk0YTYyMi02YjgwLTRkNjYtODc4NC1mNTJjNGUwNGM5MGNcL2Zhc3QyZ2hsLTQucG5nIiwib3BhY2l0eSI6OTUsInByb3BvcnRpb25zIjowLjQ1LCJncmF2aXR5IjoiY2VudGVyIn19.aHgT7-04Lk0-DKcAy5RwGeZNpvwMmN7j5bAYkTlZqNQ",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 28000,
            horsepower = 670,
            location = "Poti",
            ownerName = "Khatuna Kapanadze",
            ownerPhone = "+995 598 876 543",
            ownerEmail = "khatuna.kapanadze@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 340,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Lamborghini",
            model = "Veneno",
            year = 2016,
            price = "2700₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Lamborghini-Veneno-Transparent-Free-PNG.png",
            rating = 5.0f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 1500,
            horsepower = 750,
            location = "Tbilisi",
            ownerName = "Levan Ghviniashvili",
            ownerPhone = "+995 555 789 654",
            ownerEmail = "levan.ghviniashvili@lamborghinige.ge",
            fuelType = "Petrol",
            topSpeed = 355,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLambBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(lamborghiniCars) { selectedCar ->
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

        lambRecyclerView.adapter = recyclerAdapter
        lambRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    companion object {
        @JvmStatic
        fun newInstance() = LambFragment()
    }
}