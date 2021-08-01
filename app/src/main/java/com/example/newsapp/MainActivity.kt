package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.hepler.Logout
import com.example.newsapp.interfaces.Newsservies
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val navcontrollar = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(navigationbar, navcontrollar)
        NavigationUI.setupWithNavController(bottomnavigationbar, navcontrollar)
        NavigationUI.setupActionBarWithNavController(this, navcontrollar, drawerlaoyout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            drawerlaoyout
        )
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item?.itemId == R.id.action_logout){

            AlertDialog.Builder(this).apply{
                setTitle("Are you sure to Logout?")
                setNegativeButton("No"){_,_ ->

                }
                setPositiveButton("Yes"){ _,_ ->
                    FirebaseAuth.getInstance().signOut()
                    Logout()
                }

            }.create().show()
        }
        if (item?.itemId == R.id.toprofile){
            startActivity(Intent(this, SeeprofileActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}