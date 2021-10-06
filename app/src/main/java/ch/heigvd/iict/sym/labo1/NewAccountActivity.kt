package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewAccountActivity : AppCompatActivity(){

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.new_account)

        email = findViewById(R.id.new_email)
        password = findViewById(R.id.new_password)
        cancelButton = findViewById(R.id.new_cancel)
        validateButton = findViewById(R.id.new_validate)

        validateButton.setOnClickListener {
            if (email.text?.toString()?.contains('@') == true) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("email", email.text?.toString())
                    putExtra("password", password.text?.toString())
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                val toast = Toast.makeText(applicationContext, getString(R.string.toast_email_error), Toast.LENGTH_SHORT)
                toast.show()
                return@setOnClickListener
            }
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, null)
            finish()
        }
    }
}