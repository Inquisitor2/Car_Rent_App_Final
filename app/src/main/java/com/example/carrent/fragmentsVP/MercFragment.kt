package com.example.carrent.fragmentsVP

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrent.CarDetailFragment
import com.example.carrent.R
import com.example.carrent.adapters.NewModelRVA
import com.example.carrent.adapters.RecyclerViewAdapter
import com.example.carrent.adapters.vpRVA
import com.example.carrent.databinding.FragmentHomeBinding
import com.example.carrent.databinding.FragmentMercBinding
import com.example.carrent.models.Car
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MercFragment : Fragment() {

    private var _binding: FragmentMercBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: vpRVA

    val mercedesCars = listOf(
        Car(
            brand = "Mercedes-Benz",
            model = "C-Class",
            year = 2020,
            price = "180₾/დღე",
            imageUrl = "https://di-uploads-pod7.dealerinspire.com/mercedesbenzofgreenwichredesign/uploads/2020/05/2020-mercedes-Benz-C-Class.png",
            rating = 4.7f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 25000,
            horsepower = 255,
            location = "Tbilisi",
            ownerName = "Giorgi Beridze",
            ownerPhone = "+995 599 123 456",
            ownerEmail = "giorgi.beridze@gmail.com",
            fuelType = "Petrol",
            topSpeed = 250,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "E-Class",
            year = 2022,
            price = "220₾/დღე",
            imageUrl = "https://di-uploads-pod7.dealerinspire.com/mercedesbenzofneworleans/uploads/2021/07/mlp-img-top-2021-eclass.png",
            rating = 4.9f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 12000,
            horsepower = 302,
            location = "Batumi",
            ownerName = "Ana Chikvaidze",
            ownerPhone = "+995 599 654 321",
            ownerEmail = "ana.chikva@gmail.com",
            fuelType = "Diesel",
            topSpeed = 240,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "GLC",
            year = 2021,
            price = "200₾/დღე",
            imageUrl = "https://di-shared-assets.dealerinspire.com/legacy/rackspace/ldm-images/2021-Mercedes-Benz-GLC-hero.png",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 18000,
            horsepower = 255,
            location = "Kutaisi",
            ownerName = "Nika Abashidze",
            ownerPhone = "+995 598 334 455",
            ownerEmail = "nika.abashidze@mail.ge",
            fuelType = "Petrol",
            topSpeed = 230,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "A-Class",
            year = 2019,
            price = "140₾/დღე",
            imageUrl = "https://platform.cstatic-images.com/xlarge/in/v2/stock_photos/c06a4145-15ef-4fad-9f37-11c1e6c14c79/aafb2a33-cf44-4882-b761-1052e34d9a5d.png",
            rating = 4.4f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 30000,
            horsepower = 188,
            location = "Rustavi",
            ownerName = "Tamar Kalandadze",
            ownerPhone = "+995 599 765 432",
            ownerEmail = "t.kalandadze@auto.ge",
            fuelType = "Petrol",
            topSpeed = 215,
            bodyType = "Hatchback",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "S-Class",
            year = 2023,
            price = "300₾/დღე",
            imageUrl = "https://file.kelleybluebookimages.com/kbb/base/evox/CP/51368/2023-Mercedes-Benz-S-Class-front_51368_032_2400x1800_922_nologo.png",
            rating = 5.0f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 5000,
            horsepower = 429,
            location = "Tbilisi",
            ownerName = "Levan Nadiradze",
            ownerPhone = "+995 591 112 233",
            ownerEmail = "levan.nadiradze@gmail.com",
            fuelType = "Petrol",
            topSpeed = 280,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "GLE",
            year = 2022,
            price = "240₾/დღე",
            imageUrl = "https://images.dealer.com/ddc/vehicles/2023/Mercedes-Benz/GLE%20350/SUV/perspective/front-left/2023_24.png",
            rating = 4.6f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 14000,
            horsepower = 362,
            location = "Batumi",
            ownerName = "Nino Tkeshelashvili",
            ownerPhone = "+995 595 223 344",
            ownerEmail = "nino.tkeshela@mail.ge",
            fuelType = "Diesel",
            topSpeed = 250,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "CLA",
            year = 2020,
            price = "170₾/დღე",
            imageUrl = "https://cka-dash.s3.amazonaws.com/129-1219-CMD156/model1.png",
            rating = 4.5f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 22000,
            horsepower = 221,
            location = "Zugdidi",
            ownerName = "Irakli Giorgadze",
            ownerPhone = "+995 599 888 999",
            ownerEmail = "irakli.giorgadze@cars.ge",
            fuelType = "Petrol",
            topSpeed = 240,
            bodyType = "Sedan",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "GLA",
            year = 2021,
            price = "190₾/დღე",
            imageUrl = "https://cka-dash.s3.amazonaws.com/157-1219-CMD156/model1.png",
            rating = 4.7f,
            isHotDeal = false,
            isNewModel = false,
            mileage = 17000,
            horsepower = 221,
            location = "Gori",
            ownerName = "Salome Tsiklauri",
            ownerPhone = "+995 598 112 233",
            ownerEmail = "salome.tsiklauri@gmail.com",
            fuelType = "Petrol",
            topSpeed = 225,
            bodyType = "SUV",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "Coupé",
            year = 2022,
            price = "230₾/დღე",
            imageUrl = "https://di-shared-assets.dealerinspire.com/legacy/rackspace/ldm-images/2020-MB-E-Class-Coupe-Brilliant-Blue-Metallic.png",
            rating = 4.8f,
            isHotDeal = false,
            isNewModel = true,
            mileage = 10000,
            horsepower = 382,
            location = "Telavi",
            ownerName = "Gvantsa Nozadze",
            ownerPhone = "+995 599 556 677",
            ownerEmail = "g.nozadze@carbook.ge",
            fuelType = "Petrol",
            topSpeed = 260,
            bodyType = "Coupe",
            doors = 2,
            seats = 4
        ),
        Car(
            brand = "Mercedes-Benz",
            model = "V-Class",
            year = 2019,
            price = "160₾/დღე",
            imageUrl = "https://carsguide-res.cloudinary.com/image/upload/f_auto,fl_lossy,q_auto,t_default/v1/editorial/vhs/Mercedes-Benz-V-Class.png",
            rating = 4.3f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 35000,
            horsepower = 190,
            location = "Batumi",
            ownerName = "Dato Mchedlishvili",
            ownerPhone = "+995 599 445 566",
            ownerEmail = "dato.mchedlishvili@mb.ge",
            fuelType = "Diesel",
            topSpeed = 200,
            bodyType = "Minivan",
            doors = 4,
            seats = 7
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMercBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = vpRVA(mercedesCars) { selectedCar ->
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

        mercRecyclerView.adapter = recyclerAdapter
        mercRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
    companion object {
        @JvmStatic
        fun newInstance() = MercFragment()
    }
}