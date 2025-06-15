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
import com.example.carrent.databinding.FragmentToyotaBinding
import com.example.carrent.models.Car

class ToyotaFragment : Fragment() {
    private var _binding: FragmentToyotaBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val carList = listOf(
        Car(
            brand = "Toyota",
            model = "Corolla",
            year = 2023,
            price = "₾135/day",
            imageUrl = "https://toyota-tegeta.ge/storage/4265/conversions/day-exterior-33_1k0-webp.webp",
            rating = 4.4f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 14000,
            horsepower = 139,
            location = "Tbilisi",
            ownerName = "Giorgi Mgeladze",
            ownerPhone = "+995 555 234 567",
            ownerEmail = "giorgi.mgeladze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 190,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Toyota",
            model = "Camry",
            year = 2022,
            price = "₾162/day",
            imageUrl = "https://di-sitebuilder-assets.dealerinspire.com/Toyota/MLP/Camry/2025/color-Ocean-Gem.png",
            rating = 4.6f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 18000,
            horsepower = 203,
            location = "Batumi",
            ownerName = "Luka Tskhvaradze",
            ownerPhone = "+995 577 345 678",
            ownerEmail = "luka.tskhvaradze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 210,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Toyota",
            model = "RAV4",
            year = 2024,
            price = "₾189/day",
            imageUrl = "https://www.clickheretesting.com/ToyotaNewBern/research/2023/rav4/images/trim-2023-rav4-xle.png",
            rating = 4.8f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 6000,
            horsepower = 203,
            location = "Kutaisi",
            ownerName = "Nino Kvernadze",
            ownerPhone = "+995 599 456 789",
            ownerEmail = "nino.kvernadze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 200,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Toyota",
            model = "Highlander",
            year = 2023,
            price = "₾230/day",
            imageUrl = "https://images.dealer.com/ddc/vehicles/2021/Toyota/Highlander/SUV/perspective/front-left/2021_24.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 11000,
            horsepower = 295,
            location = "Tbilisi",
            ownerName = "Tamar Dolidze",
            ownerPhone = "+995 551 567 890",
            ownerEmail = "tamar.dolidze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 220,
            bodyType = "SUV",
            doors = 4,
            seats = 7
        ),
        Car(
            brand = "Toyota",
            model = "Yaris",
            year = 2021,
            price = "₾122/day",
            imageUrl = "https://pngimg.com/d/toyota_PNG1924.png",
            rating = 4.2f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 23000,
            horsepower = 106,
            location = "Batumi",
            ownerName = "Davit Gagnidze",
            ownerPhone = "+995 593 678 901",
            ownerEmail = "davit.gagnidze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 175,
            bodyType = "Hatchback",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Toyota",
            model = "Prius",
            year = 2024,
            price = "₾176/day",
            imageUrl = "https://images.carprices.com/pricebooks_data/usa/colorized/2024/Toyota/View2/Prius/Limited/1227_3U5.png",
            rating = 4.9f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 4000,
            horsepower = 121,
            location = "Tbilisi",
            ownerName = "Ana Khmaladze",
            ownerPhone = "+995 595 789 012",
            ownerEmail = "ana.khmaladze@gmail.com",
            fuelType = "Hybrid",
            topSpeed = 165,
            bodyType = "Hatchback",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Toyota",
            model = "Land Cruiser",
            year = 2022,
            price = "₾270/day",
            imageUrl = "https://files.toyota.com.bh/s3fs-public/2021-06/040%20Super%20White%202_2.png?VersionId=7gQdKbWeXAxaAygh54KlEomL._70rkjJ",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 15000,
            horsepower = 381,
            location = "Kutaisi",
            ownerName = "Irakli Shengelia",
            ownerPhone = "+995 557 890 123",
            ownerEmail = "irakli.shengelia@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 230,
            bodyType = "SUV",
            doors = 4,
            seats = 7
        ),
        Car(
            brand = "Toyota",
            model = "C-HR",
            year = 2023,
            price = "₾162/day",
            imageUrl = "https://d18zm77o7qzu1y.cloudfront.net/uploads/files/000/006/312/original/chrtransparent.png?1480524803",
            rating = 4.5f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 13000,
            horsepower = 144,
            location = "Batumi",
            ownerName = "Mariam Bakradze",
            ownerPhone = "+995 599 901 234",
            ownerEmail = "mariam.bakradze@gmail.com",
            fuelType = "Hybrid",
            topSpeed = 190,
            bodyType = "Crossover",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Toyota",
            model = "Sienna",
            year = 2024,
            price = "₾216/day",
            imageUrl = "https://www.toyotahawaii.com/on/demandware.static/-/Sites-Servco_master/default/dwbd1c26db/images/model/Sienna/360/ruby-flare-pearl/24_Sienna_LE_RubyFlarePearl_1.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 5000,
            horsepower = 245,
            location = "Tbilisi",
            ownerName = "Zurab Lomidze",
            ownerPhone = "+995 555 012 345",
            ownerEmail = "zurab.lomidze@gmail.com",
            fuelType = "Hybrid",
            topSpeed = 185,
            bodyType = "Minivan",
            doors = 4,
            seats = 8
        ),
        Car(
            brand = "Toyota",
            model = "Tacoma",
            year = 2022,
            price = "₾203/day",
            imageUrl = "https://tmna.aemassets.toyota.com/is/image/toyota/toyota/jellies/max/2025/tacoma/trdpro/7598/m16/36/5.png?fmt=png-alpha&wid=930&qlt=90",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 20000,
            horsepower = 278,
            location = "Kutaisi",
            ownerName = "Eka Chikhladze",
            ownerPhone = "+995 577 123 456",
            ownerEmail = "eka.chikhladze@gmail.com",
            fuelType = "Gasoline",
            topSpeed = 205,
            bodyType = "Pickup",
            doors = 4,
            seats = 5
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToyotaBinding.inflate(layoutInflater, container, false)
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

        toyotaRecyclerView.adapter = recyclerAdapter
        toyotaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToyotaFragment()
    }
}