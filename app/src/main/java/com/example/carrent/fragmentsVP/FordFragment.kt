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
import com.example.carrent.databinding.FragmentFordBinding
import com.example.carrent.models.Car

class FordFragment : Fragment() {
    private var _binding: FragmentFordBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val fordCars = listOf(
        Car(
            brand = "Ford",
            model = "Focus",
            year = 2019,
            price = "120₾/დღე",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-blue-ford-focus-carcarvehicletransportford-961524667633zolgu.png",
            rating = 4.5f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 28000,
            horsepower = 160,
            location = "Tbilisi",
            ownerName = "Lasha Kapanadze",
            ownerPhone = "+995 599 234 567",
            ownerEmail = "lasha.kapanadze@ford.ge",
            fuelType = "Petrol",
            topSpeed = 200,
            bodyType = "Hatchback",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "Mondeo",
            year = 2021,
            price = "135₾/დღე",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-ford-mondeo-red-carcarvehicletransportford-961524638418lugc0.png",
            rating = 4.6f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 19000,
            horsepower = 187,
            location = "Batumi",
            ownerName = "Nino Bregvadze",
            ownerPhone = "+995 591 876 543",
            ownerEmail = "n.bregvadze@mail.ge",
            fuelType = "Diesel",
            topSpeed = 220,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "Mustang",
            year = 2022,
            price = "200₾/დღე",
            imageUrl = "https://pngimg.com/d/mustang_PNG30.png",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 10000,
            horsepower = 450,
            location = "Kutaisi",
            ownerName = "Irakli Tedoradze",
            ownerPhone = "+995 598 321 987",
            ownerEmail = "irakli.tedoradze@auto.ge",
            fuelType = "Petrol",
            topSpeed = 250,
            bodyType = "Coupe",
            doors = 2,
            seats = 4
        ),
        Car(
            brand = "Ford",
            model = "Kuga",
            year = 2020,
            price = "140₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Ford-Kuga-PNG-Free-File-Download.png",
            rating = 4.4f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 23000,
            horsepower = 180,
            location = "Rustavi",
            ownerName = "Gvantsa Jorbenadze",
            ownerPhone = "+995 555 987 654",
            ownerEmail = "gvantsa.jorbenadze@fordcars.ge",
            fuelType = "Diesel",
            topSpeed = 210,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "Explorer",
            year = 2023,
            price = "180₾/დღე",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Ford-Explorer-PNG-Images-HD.png",
            rating = 4.8f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 8000,
            horsepower = 300,
            location = "Gori",
            ownerName = "Tamar Gelashvili",
            ownerPhone = "+995 577 112 358",
            ownerEmail = "tamar.gelashvili@rentford.ge",
            fuelType = "Petrol",
            topSpeed = 240,
            bodyType = "SUV",
            doors = 4,
            seats = 7
        ),
        Car(
            brand = "Ford",
            model = "Edge",
            year = 2021,
            price = "150₾/დღე",
            imageUrl = "https://media.chromedata.com/MediaGallery/media/MjkzOTU4Xk1lZGlhIEdhbGxlcnk/EzRP385toB09CRkE1aHJ-eb9FU5CTF9rXRQztupPU6MqRdAXkgirMMy9wdSkzKk_zgbUjQV8lSdbUqj1IEnt4-nWsvpzPNRs2gvn9V72tO-ul-FIxxH-BcvKeDXBHSfUXCuoQyNscFHM90bHzZg6ekX3KSw5YZEDdlZQqjrx0y0/cc_2024FOS150033_01_640_B3.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 17000,
            horsepower = 250,
            location = "Zugdidi",
            ownerName = "Sandro Gogoladze",
            ownerPhone = "+995 595 443 211",
            ownerEmail = "s.gogoladze@edgerentals.ge",
            fuelType = "Petrol",
            topSpeed = 230,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "Fiesta",
            year = 2018,
            price = "110₾/დღე",
            imageUrl = "https://pngimg.com/d/ford_PNG12210.png",
            rating = 4.3f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 35000,
            horsepower = 120,
            location = "Telavi",
            ownerName = "Lela Chumburidze",
            ownerPhone = "+995 593 665 100",
            ownerEmail = "lela.chumburidze@fordauto.ge",
            fuelType = "Petrol",
            topSpeed = 180,
            bodyType = "Hatchback",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "EcoSport",
            year = 2020,
            price = "125₾/დღე",
            imageUrl = "https://platform.cstatic-images.com/xlarge/in/v2/stock_photos/c57bea1e-7f90-4a0e-a3db-e12721078d43/d3249a0f-f015-4bdb-aa42-0525993c3797.png",
            rating = 4.4f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 22000,
            horsepower = 140,
            location = "Poti",
            ownerName = "Nika Chichinadze",
            ownerPhone = "+995 597 778 899",
            ownerEmail = "nika.chichinadze@ecosport.ge",
            fuelType = "Diesel",
            topSpeed = 190,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "Ranger",
            year = 2022,
            price = "175₾/დღე",
            imageUrl = "https://carsguide-res.cloudinary.com/image/upload/e_trim:10,f_auto/c_scale,t_cg_base,w_678/v1/editorial/ford-ranger-my23-index-1.png",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 11000,
            horsepower = 270,
            location = "Marneuli",
            ownerName = "Zurab Tabidze",
            ownerPhone = "+995 599 912 347",
            ownerEmail = "zurab.tabidze@ranger.ge",
            fuelType = "Diesel",
            topSpeed = 210,
            bodyType = "Pickup",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Ford",
            model = "Transit",
            year = 2019,
            price = "130₾/დღე",
            imageUrl = "https://s3.amazonaws.com/cka-dash/053-0622-ZPF249/model1.png",
            rating = 4.2f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 40000,
            horsepower = 185,
            location = "Tbilisi",
            ownerName = "Dato Nemsadze",
            ownerPhone = "+995 555 666 777",
            ownerEmail = "dato.nemsadze@transit.ge",
            fuelType = "Diesel",
            topSpeed = 160,
            bodyType = "Van",
            doors = 4,
            seats = 8
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(fordCars) { selectedCar ->
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

        fordRecyclerView.adapter = recyclerAdapter
        fordRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance() = FordFragment()
    }
}