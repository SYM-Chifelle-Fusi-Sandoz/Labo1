package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader

class SecondaryActivity : AppCompatActivity() {
    private lateinit var email: TextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        email = findViewById<TextView>(R.id.email)
        val bundle = intent.extras
        if (bundle != null) {
            email.text = bundle.getString("emailInput")
        }

        image = findViewById<ImageView>(R.id.connected_image)
        ImageDownloader(image, "https://thispersondoesnotexist.com/image").show()
    }
}