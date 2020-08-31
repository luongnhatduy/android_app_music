package com.example.android_app.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.rvtutorial.CustomAdapter
import com.example.android_app.ApiService
import com.example.android_app.CellClickListener
import com.example.android_app.R
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.serialization.ImplicitReflectionSerializer
import org.json.JSONArray
import org.json.JSONObject


class HomeFragment : Fragment() {
    companion object {
        private val apiService by lazy { ApiService.create() }
        private var disposable: Disposable? = null
    }

    var rowsArrayList: ArrayList<Any> = ArrayList()
    var isLoading = false
    var recyclerViewAdapter: CustomAdapter? = null
    var page = 1


    @ImplicitReflectionSerializer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val rv_animal_list = root.findViewById(R.id.rv_animal_list) as RecyclerView
//        rvMovies.layoutManager = LinearLayoutManager(root.context)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.layoutManager = GridLayoutManager(context, 2)

        (rvMovies.layoutManager as GridLayoutManager).setSpanSizeLookup(object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == rowsArrayList.size) {
                    2
                } else {
                    1
                }
            }
        })
        val navController = activity?.findNavController(R.id.nav_host_fragment_activity)
//        val navController = (parentFragmentManager.findFragmentById(R.id.main_nav_fragments) as NavHostFragment).navController
//        val navController = findNavController(this)
        val bundle = Bundle()

        recyclerViewAdapter = CustomAdapter(rowsArrayList, object :
            CellClickListener {
            override fun onCellClickListener(data: Any) {
                                bundle.putString("movieId", data.toString())
                                navController?.navigate(R.id.navigation_movie_detail,bundle)
            }
        })

        rvMovies.adapter = recyclerViewAdapter
        fetchData(page)
        initScrollListener()
    }

    private fun fetchData(page : Int){
        disposable = apiService.getNowPlaying(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    val results = result.get("results") as ArrayList<Any>
                    rowsArrayList.addAll(results)
                    recyclerViewAdapter?.plusItem = 1
                    recyclerViewAdapter?.items = rowsArrayList
                    recyclerViewAdapter?.notifyDataSetChanged()
                    isLoading = false
                },
                { error -> Log.e("anh duy error", "anh  duy error", error) }
            )
    }

    private fun initScrollListener() {
        rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(
                @NonNull recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading && linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size - 1) {
                    //bottom of list!
                    loadMore()
                    isLoading = true
                }
            }
        })
    }

    private fun loadMore() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            page++
            fetchData(page)
        }, 2000)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.e("DUY", "FragmentHome onCreate")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.e("DUY", "FragmentHome onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.e("DUY", "FragmentHome onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.e("DUY", "FragmentHome onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.e("DUY", "FragmentHome onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.e("DUY", "FragmentHome onDestroy")
//    }

}
