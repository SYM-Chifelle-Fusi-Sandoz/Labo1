package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : SuperActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_secondary)

        //on récupère les information envoyer et on affiche l'email
        val bundle = intent.extras
        if (bundle != null) {
            email.text = bundle.getString("emailInput")
        }

        //on telecharge l'image sur internet
        ImageDownloader(connected_image, "https://thispersondoesnotexist.com/image").show()
    }

    override fun getTag(): String? {
        return TAG
    }


    companion object {
        private const val TAG: String = "SecondaryActivity"
    }
}