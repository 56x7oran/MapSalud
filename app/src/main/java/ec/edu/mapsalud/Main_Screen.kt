package ec.edu.mapsalud

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ec.edu.mapsalud.databinding.ActivityMainScreenBinding
import ec.edu.mapsalud.databinding.ActivitySignUpPageBinding
import ec.edu.mapsalud.fragments.BookingFragment
import ec.edu.mapsalud.fragments.CentersFragment
import ec.edu.mapsalud.fragments.HomeFragment
import ec.edu.mapsalud.fragments.ProfileFragment

class Main_Screen : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.commit {
                        val f1 = HomeFragment()
                        replace(R.id.fragmentContainer, f1)
                        addToBackStack(null)
                    }
                    true
                }

                R.id.nav_person -> {
                    supportFragmentManager.commit {
                        val f1 = ProfileFragment()
                        replace(R.id.fragmentContainer, f1)
                        addToBackStack(null)
                    }
                    true
                }

                R.id.nav_booking -> {
                    supportFragmentManager.commit {
                        val f1 = BookingFragment()
                        replace(R.id.fragmentContainer, f1)
                        addToBackStack(null)
                    }
                    true
                }

                R.id.nav_centers -> {
                    supportFragmentManager.commit {
                        val f1 = CentersFragment()
                        replace(R.id.fragmentContainer, f1)
                        addToBackStack(null)
                    }
                    true
                }

                else -> false
            }
        }


    }
}