package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondaryActivity : AppCompatActivity() {
    private lateinit var email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        email = findViewById<TextView>(R.id.email)
        val bundle = intent.extras
        if (bundle != null) {
            email.text = bundle.getString("emailInput")
        }

    }
}