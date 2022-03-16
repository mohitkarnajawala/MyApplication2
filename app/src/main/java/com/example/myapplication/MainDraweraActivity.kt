package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Constant.showToastMessage
import com.example.myapplication.LiveDataExample.LiveDataMain
import com.example.myapplication.MutableExample.MutableExampleFragment
import com.example.myapplication.databinding.ActivityMainDraweraBinding
import com.example.myapplication.ui.gallery.GalleryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Array.newInstance

class MainDraweraActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainDraweraBinding

    companion object{
        val Tag: String= MainDraweraActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainDraweraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainDrawera.toolbar)

        binding.appBarMainDrawera.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navView

        val navView1: BottomNavigationView = binding.appBarMainDrawera.containMain.navView1

        val navController = findNavController(R.id.nav_host_fragment_content_main_drawera)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.nav_share,
                R.id.nav_livedata

            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView1.setupWithNavController(navController)

       /* navView.setNavigationItemSelectedListener {

        }*/
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_gallery->{
                    supportActionBar?.setTitle("LiveData")
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main_drawera, MutableExampleFragment()).addToBackStack(null)
                        .commit()
                    drawerLayout.close()
                    true
                }
                R.id.nav_share ->{
                    val sendIntent: Intent = Intent().apply {
                        action=Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT,"Hello this is mohit")
                        type="text/plain"

                    }
                    val share= Intent.createChooser(sendIntent,null)
                    startActivity(share)

                    true
                }
                R.id.nav_livedata ->{

                    val intent= Intent(this,LiveDataMain::class.java)
                    startActivity(intent)
                    true
                }
                else->{
                    false
                }
            }
        }

        //showToastMessage("OnCreat",Toast.LENGTH_SHORT)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_drawera, menu)


        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main_drawera)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        //showToastMessage("onStart")
    }

    override fun onResume() {
        super.onResume()
        //showToastMessage("onResume")
    }

    override fun onPause() {
        super.onPause()
        //showToastMessage("onPause")
    }

    override fun onStop() {
        super.onStop()
        //showToastMessage("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        //showToastMessage("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        //showToastMessage("onDestroy")
    }

}

/*
*
* …or create a new repository on the command line

echo "# Demo" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/mohitkarnajawala/MyApplication2.git
git push -u origin main

…or push an existing repository from the command line

git remote add origin https://github.com/mohitkarnajawala/Demo.git
git branch -M main
git push -u origin main*/