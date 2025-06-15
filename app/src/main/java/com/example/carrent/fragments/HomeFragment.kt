package com.example.carrent.fragments

import UserViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.carrent.adapters.RecyclerViewAdapter
import com.example.carrent.databinding.FragmentHomeBinding
import com.example.carrent.models.Car
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrent.CarDetailFragment
import com.example.carrent.R
import com.example.carrent.SignUpFragment
import com.example.carrent.adapters.NewModelRVA
import com.example.carrent.adapters.vpRVA
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private lateinit var newModelAdapter: RecyclerViewAdapter
    private lateinit var economyModelAdapter: NewModelRVA

    private val newModelCars = listOf(
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
        ),        Car(
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
    )
    private val hotDeal = listOf(
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
        ),
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
    val specialOfferCars = listOf(
        Car(
            brand = "DeLorean",
            model = "DMC-12 Time Machine",
            year = 1985,
            price = "800₾/დღე",
            imageUrl = "https://static.wikia.nocookie.net/yunas-princess-adventure/images/8/84/DeLorean_Time_Machine.png/revision/latest?cb=20151201014056",
            rating = 4.9f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 88000,
            horsepower = 300,
            location = "Tbilisi",
            ownerName = "Doc Brown",
            ownerPhone = "+995 591 198 520",
            ownerEmail = "doc@timetravel.ge",
            fuelType = "Plutonium",
            topSpeed = 140,
            bodyType = "Coupe",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Ghostbusters",
            model = "Ecto-1",
            year = 1984,
            price = "600₾/დღე",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/4/47/Ectomobile.png",
            rating = 4.8f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 45000,
            horsepower = 390,
            location = "Tbilisi",
            ownerName = "Peter Venkman",
            ownerPhone = "+995 598 777 777",
            ownerEmail = "venkman@ecto.ge",
            fuelType = "Supernatural Energy",
            topSpeed = 180,
            bodyType = "Hearse",
            doors = 4,
            seats = 5
        ),
        Car(
            brand = "Mach 5",
            model = "Speed Racer Edition",
            year = 2025,
            price = "750₾/დღე",
            imageUrl = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/a03a0300-c9de-484b-8af8-3e45718e58a1/disbi8r-ae006c43-13fb-48ea-8211-555ed3b442a2.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2EwM2EwMzAwLWM5ZGUtNDg0Yi04YWY4LTNlNDU3MThlNThhMVwvZGlzYmk4ci1hZTAwNmM0My0xM2ZiLTQ4ZWEtODIxMS01NTVlZDNiNDQyYTIucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.2p9JmOYn3a1ctyNs3bnm66Y2cwMf1yZe3Hjq5gYMhMQ",
            rating = 4.6f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 500,
            horsepower = 900,
            location = "Rustavi",
            ownerName = "Speed Racer",
            ownerPhone = "+995 595 909 090",
            ownerEmail = "speed@mach5.ge",
            fuelType = "High-Octane Petrol",
            topSpeed = 320,
            bodyType = "Race Car",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Mad Max",
            model = "Interceptor V8",
            year = 1973,
            price = "700₾/დღე",
            imageUrl = "https://www.drivencarguide.co.nz/media/41976/mad-max-interceptor-build-hero-2.jpg?width=1028&quality=85&v=1d095b33f741080",
            rating = 4.5f,
            isHotDeal = true,
            isNewModel = false,
            mileage = 60000,
            horsepower = 600,
            location = "Kutaisi",
            ownerName = "Max Rockatansky",
            ownerPhone = "+995 598 555 666",
            ownerEmail = "madmax@wasteland.ge",
            fuelType = "Nitro",
            topSpeed = 280,
            bodyType = "Muscle",
            doors = 2,
            seats = 2
        ),
        Car(
            brand = "Wayne Enterprises",
            model = "Batmobile Tumbler",
            year = 2023,
            price = "1000₾/დღე",
            imageUrl = "https://i.pinimg.com/736x/0a/2a/47/0a2a47538842dcc148c542827ab80344.jpg",
            rating = 5.0f,
            isHotDeal = true,
            isNewModel = true,
            mileage = 100,
            horsepower = 1200,
            location = "Gotham (Tbilisi branch)",
            ownerName = "Bruce Wayne",
            ownerPhone = "+995 599 000 001",
            ownerEmail = "wayne@batmail.com",
            fuelType = "Jet Fuel",
            topSpeed = 350,
            bodyType = "Armored",
            doors = 2,
            seats = 2
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        userViewModel.firstname.observe(viewLifecycleOwner) { name ->
            personNameFromDB.text = name
        }

        recyclerAdapter = RecyclerViewAdapter(hotDeal) { selectedCar ->
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
        hotDealsRecyclerView.adapter = recyclerAdapter
        hotDealsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        newModelAdapter = RecyclerViewAdapter(newModelCars) { selectedCar ->
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
        newModelsRecyclerView.adapter = newModelAdapter
        newModelsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        economyModelAdapter = NewModelRVA(specialOfferCars) { selectedCar ->
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
        economyModelsRecyclerView.adapter = economyModelAdapter
        economyModelsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        balanceClickableLayout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, debitCardFragment())
                .addToBackStack(null)
                .commit()
        }
        loadUserBalance()
    }
    private fun loadUserBalance() {
        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("logged_in_user_id", null) ?: return

        val userRef = FirebaseDatabase.getInstance().getReference("User").child(userId).child("balance")
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val balance = snapshot.getValue(Double::class.java) ?: 0.0
                binding.balanceFromDB.text = "${String.format("%.2f", balance)}₾"
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to load balance: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}