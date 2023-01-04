package android.example.com.secondproject

import android.example.com.secondproject.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val API_KEY = BuildConfig.API_KEY
    }
}
