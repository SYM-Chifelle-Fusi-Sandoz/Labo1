package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // log pour signaler l'appel à la méthode "onCreate"
        Log.i("SecondaryActivity", "onCreate Called");

        setContentView(R.layout.activity_secondary)
        val bundle = intent.extras
        if (bundle != null) {
            email.text = bundle.getString("emailInput")
        }

        ImageDownloader(connected_image, "https://thispersondoesnotexist.com/image").show()
    }
}