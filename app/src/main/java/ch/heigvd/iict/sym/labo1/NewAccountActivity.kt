package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.new_account.*

class NewAccountActivity : SuperActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_account)

        new_validate.setOnClickListener {
            if (Utility.isEmailOk(applicationContext, new_email.text?.toString(), getString(R.string.toast_email_error))) {
                //preparation des données a renvoyer
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("email", new_email.text?.toString())
                    putExtra("password", new_password.text?.toString())
                }
                //on fixe le code de retour et les données
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                return@setOnClickListener
            }
        }

        new_cancel.setOnClickListener {
            //on fixe le code de retour et les données
            setResult(Activity.RESULT_CANCELED, null)
            finish()
        }
    }

    override fun getTag(): String {
        return TAG
    }

    companion object {
        private const val TAG: String = "NewAccountActivity"
    }
}