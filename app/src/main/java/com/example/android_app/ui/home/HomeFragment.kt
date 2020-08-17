package com.example.android_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android_app.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.rvtutorial.CustomAdapter
import com.example.android_app.ApiService
import com.example.android_app.CellClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.*

class HomeFragment : Fragment() {
    companion object {
        private val apiService by lazy { ApiService.create() }
        private var disposable: Disposable? = null
    }
//    var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")
    val animals: ArrayList<String> = ArrayList()

    private lateinit var homeViewModel: HomeViewModel

    @ImplicitReflectionSerializer
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.e("DUY", "HomeFRagment onCreateView--------------------")


        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val rv_animal_list = root.findViewById(R.id.rv_animal_list) as RecyclerView
        rv_animal_list.layoutManager = LinearLayoutManager(root.context)

        val navController = activity?.findNavController(R.id.nav_host_fragment_activity)
//        val navController = (parentFragmentManager.findFragmentById(R.id.main_nav_fragments) as NavHostFragment).navController
//        val navController = findNavController(this)
        Log.e("TUNT", "HomeFragment aa ${navController}")
        val bundle = Bundle()

        disposable = apiService.getAllList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> rv_animal_list.adapter = CustomAdapter(result as ArrayList<Any> , object : CellClickListener {
                    override fun onCellClickListener(data: Any) {
                        bundle.putString("nameSong", data.toString())
                        navController?.navigate(R.id.navigation_play_music,bundle)
                    }
                })},
                {error -> Log.e("anh duy error", "anh  duy error",error)}
            )
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("DUY", "FragmentHome onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("DUY", "FragmentHome onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("DUY", "FragmentHome onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("DUY", "FragmentHome onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("DUY", "FragmentHome onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("DUY", "FragmentHome onDestroy")
    }

}
