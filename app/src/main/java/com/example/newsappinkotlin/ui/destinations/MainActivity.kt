package com.example.newsappinkotlin.ui.destinations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.network.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        bottom_nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment_container))

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

        bottom_nav_view.setupWithNavController(navController)
        syncNavBar()

        ApiClient.topHeadlinesResponse(1, ::shareData, ::noData)
    }


    private fun noData(reason: String) {
        Toast.makeText(this, "Reason → $reason", Toast.LENGTH_SHORT).show()
    }


    private fun shareData(headlines: ArrayList<FullNewsModel>) {

    }


    private fun syncNavBar() {
//        NavigationUI.setupWithNavController(bottom_nav_view, navController)
        navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id == R.id.itemDetailsFragment || dest.id == R.id.splashFragment) {
                bottom_nav_view.visibility = View.GONE
            } else {
                bottom_nav_view.visibility = View.VISIBLE
            }
        }
    }


}