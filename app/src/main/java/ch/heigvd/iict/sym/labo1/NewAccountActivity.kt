package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.new_account.*

class NewAccountActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // log pour signaler l'appel à la méthode "onCreate"
        Log.i("NewAccountActivity", "onCreate Called");

        setContentView(R.layout.new_account)

        new_validate.setOnClickListener {
            if (new_email.text?.toString()?.contains('@') == true) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("email", new_email.text?.toString())
                    putExtra("password", new_password.text?.toString())
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                val toast = Toast.makeText(applicationContext, getString(R.string.toast_email_error), Toast.LENGTH_SHORT)
                toast.show()
                return@setOnClickListener
            }
        }

        new_cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, null)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart Called");
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume Called");
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause Called");
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop Called");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy Called");
    }

    companion object {
        private const val TAG: String = "NewAccountActivity"
    }
}