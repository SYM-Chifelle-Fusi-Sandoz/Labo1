package ch.heigvd.iict.sym.labo1

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    // /!\ listOf() retourne une List<T> qui est immuable
    private val credentials = mutableListOf(
                                Pair("user1@heig-vd.ch","1234"),
                                Pair("user2@heig-vd.ch","abcd")
                            )

    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cancelButton: Button
    private lateinit var validateButton: Button
    private lateinit var newAccount: TextView

    val LAUNCH_NEWACCOUNT_ACTIVITY = 1


    var launchRegisterActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val newEmail = data?.getStringExtra("email")
            val newPassword = data?.getStringExtra("password")
            credentials.add(Pair(newEmail, newPassword) as Pair<String, String>)
            // your operation...
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email)
        password = findViewById(R.id.main_password)
        cancelButton = findViewById(R.id.main_cancel)
        validateButton = findViewById(R.id.main_validate)
        newAccount = findViewById(R.id.main_new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
        }

        newAccount.setOnClickListener {
            val intent = Intent(this, NewAccountActivity::class.java)
            launchRegisterActivity.launch(intent)
        }

        validateButton.setOnClickListener {
            //on réinitialise les messages d'erreur
            email.error = null
            password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()

            if(emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if(emailInput.isNullOrEmpty())
                    email.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    password.error = getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            }

            if (emailInput?.contains('@') == false) {
                val toast = Toast.makeText(applicationContext, getString(R.string.toast_email_error), Toast.LENGTH_SHORT)
                toast.show()
                return@setOnClickListener
            }
            if (!credentials.contains(Pair(emailInput, passwordInput)))
            {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle(getString(R.string.dialog_invalid_credentials_title))
                alertDialogBuilder.setMessage(getString(R.string.dialog_invalid_credentials_error))
                alertDialogBuilder.show()
                return@setOnClickListener

            }
            else
            {
                val intent = Intent(this, SecondaryActivity::class.java).apply {
                    putExtra("emailInput", emailInput)
                }
                startActivity(intent)
            }
        }
    }



    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_NEWACCOUNT_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                val newEmail = data?.getStringExtra("email")
                val newPassword = data?.getStringExtra("password")
                credentials.add(Pair(newEmail, newPassword) as Pair<String, String>)
            }
            if (resultCode == RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    } //onActivityResult
    */

    // En Kotlin, les variables static ne sont pas tout à fait comme en Java
    // pour des raison de lisibilité du code, les variables et méthodes static
    // d'une classe doivent être regroupées dans un bloc à part: le companion object
    // cela permet d'avoir un aperçu plus rapide de tous les éléments static d'une classe
    // sans devoir trouver le modifieur dans la définition de ceux-ci, qui peuvent être mélangé
    // avec les autres éléments non-static de la classe
    companion object {
        private const val TAG: String = "MainActivity"
    }

}
