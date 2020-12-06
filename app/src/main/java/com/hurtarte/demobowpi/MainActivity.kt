package com.hurtarte.demobowpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hurtarte.demobowpi.fragments.Done
import com.hurtarte.demobowpi.fragments.List
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myFragment: Fragment?= null

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            List()
        ).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener(this.navListener)




    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.getItemId()) {
                R.id.list -> selectedFragment = List()
                R.id.done -> selectedFragment = Done()

            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment
                ).commit()
            }
            true
        }
}