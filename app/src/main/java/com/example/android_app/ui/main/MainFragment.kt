package com.example.android_app.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.serialization.ImplicitReflectionSerializer


class MainFragment : Fragment() {
//    companion object {
//        private val apiService by lazy { ApiService.create() }
//        private var disposable: Disposable? = null
//    }
//    var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")
//    val animals: ArrayList<String> = ArrayList()

    private lateinit var viewModel: MainViewModel

    @ImplicitReflectionSerializer
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

//        val rv_animal_list = root.findViewById(R.id.rv_animal_list) as RecyclerView
//        rv_animal_list.layoutManager = LinearLayoutManager(root.context)
//
//        val navController = activity?.findNavController(R.id.nav_host_fragment)
//
//        disposable = apiService.getAllList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {result -> rv_animal_list.adapter = CustomAdapter(result as ArrayList<Any> , object : CellClickListener {
//                    override fun onCellClickListener(data: Any) {
//                        navController?.navigate(R.id.navigation_play_music)
//                    }
//                })},
//                {error -> Log.e("anh duy error", "anh  duy error",error)}
//            )
//        getParentFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navView: BottomNavigationView = root.findViewById(R.id.nav_view)

//        val navController = activity?.findNavController(R.id.nav_host_fragment)
        Log.e("TUNT", "aa ${(main_nav_fragments != null)}")
        val navHostFragment: NavHostFragment = childFragmentManager.findFragmentById(R.id.main_nav_fragments) as NavHostFragment
//        val navController = findNavController();//activity?.findNavController(R.id.nav_host_fragment)
//
//
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(activity as AppCompatActivity, navHostFragment.navController, appBarConfiguration)

        navView.setupWithNavController(navHostFragment.navController)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("TUNT", "ab ${(childFragmentManager)}")
        val navHostFragment: NavHostFragment = childFragmentManager.findFragmentById(R.id.main_nav_fragments) as NavHostFragment
        Log.e("TUNT", "ab ${(navHostFragment != null)}")
        Log.e("TUNT", "ac ${NavHostFragment.findNavController(this)}")
        Log.e("TUNT", "ac ${(navHostFragment.navController)}")
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navHostFragment: NavHostFragment =
//            findFragmentById(R.id.nav_host)
//        nav_view.setupWithNavController(findNavController())


//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.e("DUY", "MainFragment onCreate")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.e("DUY", "MainFragment onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.e("DUY", "MainFragment onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.e("DUY", "MainFragment onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.e("DUY", "MainFragment onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.e("DUY", "MainFragment onDestroy")
//    }

}
