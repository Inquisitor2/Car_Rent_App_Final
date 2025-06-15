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
import com.example.carrent.databinding.FragmentAudiBinding
import com.example.carrent.databinding.FragmentMercBinding
import com.example.carrent.models.Car

class AudiFragment : Fragment() {
    private var _binding: FragmentAudiBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val listOfAudiCars = listOf(
        Car(
            brand = "Audi",
            model = "A6",
            year = 2021,
            price = "180₾/დღეში",
            imageUrl = "https://www.pngarts.com/files/11/Expensive-Audi-A6-PNG-Transparent-Image.png",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 32000,
            horsepower = 248,
            location = "Tbilisi",
            ownerName = "დავით ჯანაშვილი",
            ownerPhone = "+995 555 111 222",
            ownerEmail = "davit.janashvili@gmail.com",
            fuelType = "Diesel",
            topSpeed = 240,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Audi",
            model = "Q7",
            year = 2022,
            price = "220₾/დღეში",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-audi-q7-caraudicars-961524670848felfc.png",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 15000,
            horsepower = 335,
            location = "Batumi",
            ownerName = "მარიამ ლომიძე",
            ownerPhone = "+995 555 333 444",
            ownerEmail = "mariam.lomidze@gmail.com",
            fuelType = "Petrol",
            topSpeed = 250,
            bodyType = "SUV",
            doors = 5,
            seats = 7
        ),
        Car(
            brand = "Audi",
            model = "RS6 Avant",
            year = 2023,
            price = "350₾/დღეში",
            imageUrl = "https://purepng.com/public/uploads/large/purepng.com-grey-audi-rs6-avant-caraudicarvehicletransport-961524657186kghk4.png",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 5000,
            horsepower = 600,
            location = "Kutaisi",
            ownerName = "გიორგი ხარაბაძე",
            ownerPhone = "+995 555 555 666",
            ownerEmail = "giorgi.kharabadze@gmail.com",
            fuelType = "Petrol",
            topSpeed = 305,
            bodyType = "Wagon",
            doors = 5,
            seats = 5
        ),
        Car(
            brand = "Audi",
            model = "A4",
            year = 2020,
            price = "130₾/დღეში",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Audi-A4-Transparent-Free-PNG.png",
            rating = 4.4f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 45000,
            horsepower = 190,
            location = "Rustavi",
            ownerName = "ნინო მელაძე",
            ownerPhone = "+995 555 777 888",
            ownerEmail = "nino.meladze@gmail.com",
            fuelType = "Diesel",
            topSpeed = 230,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Audi",
            model = "e-tron GT",
            year = 2023,
            price = "300₾/დღეში",
            imageUrl = "https://aws.audimiddleeast.com/audi/feature-apps/e-tron/stock/assets/img/stock/gtrs/Y1Y1-44I.png",
            rating = 4.7f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 3000,
            horsepower = 522,
            location = "Tbilisi",
            ownerName = "ლუკა ცინცაძე",
            ownerPhone = "+995 555 999 000",
            ownerEmail = "luka.tsintsadze@gmail.com",
            fuelType = "Electric",
            topSpeed = 245,
            bodyType = "Coupe",
            doors = 4,
            seats = 4
        ),
        Car(
            brand = "Audi",
            model = "Q5",
            year = 2021,
            price = "160₾/დღეში",
            imageUrl = "https://cka-dash.s3.amazonaws.com/042-0321-CA148/model1.png",
            rating = 4.5f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 28000,
            horsepower = 261,
            location = "Zugdidi",
            ownerName = "თამარ ნათენაძე",
            ownerPhone = "+995 555 121 212",
            ownerEmail = "tamar.natenadze@gmail.com",
            fuelType = "Petrol",
            topSpeed = 235,
            bodyType = "SUV",
            doors = 5,
            seats = 5
        ),
        Car(
            brand = "Audi",
            model = "A8 L",
            year = 2022,
            price = "250₾/დღეში",
            imageUrl = "https://images.dealer.com/ddc/vehicles/2022/Audi/A8/Sedan/perspective/front-left/2022_24.png",
            rating = 4.8f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 12000,
            horsepower = 335,
            location = "Tbilisi",
            ownerName = "ირაკლი ბოლქვაძე",
            ownerPhone = "+995 555 343 434",
            ownerEmail = "irakli.bolkvadze@gmail.com",
            fuelType = "Hybrid",
            topSpeed = 250,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Audi",
            model = "SQ7",
            year = 2021,
            price = "280₾/დღეში",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Audi-Q7-Download-Free-PNG.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 22000,
            horsepower = 500,
            location = "Gori",
            ownerName = "სოფიო მიქაძე",
            ownerPhone = "+995 555 565 656",
            ownerEmail = "sofio.mikadze@gmail.com",
            fuelType = "Petrol",
            topSpeed = 250,
            bodyType = "SUV",
            doors = 5,
            seats = 7
        ),
        Car(
            brand = "Audi",
            model = "TT RS",
            year = 2020,
            price = "200₾/დღეში",
            imageUrl = "https://www.pngplay.com/wp-content/uploads/13/Audi-TT-RS-Transparent-Background.png",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 18000,
            horsepower = 400,
            location = "Kutaisi",
            ownerName = "ლევან ზარქუა",
            ownerPhone = "+995 555 787 878",
            ownerEmail = "levan.zarkua@gmail.com",
            fuelType = "Petrol",
            topSpeed = 280,
            bodyType = "Coupe",
            doors = 2,
            seats = 4
        ),
        Car(
            brand = "Audi",
            model = "A3 Sportback",
            year = 2023,
            price = "110₾/დღეში",
            imageUrl = "https://mediaservice.audi.com/media/cdb/data/07ee0b04-e276-485e-a868-519c080c1ee7.jpg",
            rating = 4.3f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 8000,
            horsepower = 150,
            location = "Batumi",
            ownerName = "თეონა დვალი",
            ownerPhone = "+995 555 909 090",
            ownerEmail = "teona.dvali@gmail.com",
            fuelType = "Petrol",
            topSpeed = 220,
            bodyType = "Hatchback",
            doors = 5,
            seats = 5
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAudiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(listOfAudiCars) { selectedCar ->
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
        audiRecyclerView.adapter = recyclerAdapter
        audiRecyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

    companion object {

        @JvmStatic
        fun newInstance() = AudiFragment()
    }
}