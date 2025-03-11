package com.revan.weathermate.presentation.activity.splash
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.revan.weathermate.R
import com.revan.weathermate.databinding.ActivitySplashBinding
import com.revan.weathermate.presentation.activity.weatherforecast.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var _binding : ActivitySplashBinding? = null
    private val binding get() = _binding!!
    private val splashViewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkIsFinishedGuide()
    }

    fun checkIsFinishedGuide() {
        splashViewModel.getData("isFinishedGuide")
        val isFinishedGuide = splashViewModel.isFinishedGuide.value
        if (isFinishedGuide == null) {
            navigate(false)
        } else {
            if (isFinishedGuide == "true") {
                navigate(true)
            }
        }
    }

    private fun navigate(isFinishedGuide : Boolean) {
        lifecycleScope.launch{
            delay(2000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            if(isFinishedGuide){
                intent.putExtra("isFinishedGuide", true)
            }
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}