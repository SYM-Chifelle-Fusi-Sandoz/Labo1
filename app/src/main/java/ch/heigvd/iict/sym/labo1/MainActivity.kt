package ch.heigvd.iict.sym.labo1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    // /!\ listOf() retourne une List<T> qui est immuable
    private val credentials = mutableListOf(
                                Pair("user1@heig-vd.ch","1234"),
                                Pair("user2@heig-vd.ch","abcd")
                            )

    var launchRegisterActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val newEmail = data?.getStringExtra("email")
            val newPassword = data?.getStringExtra("password")
            credentials.add(Pair(newEmail, newPassword) as Pair<String, String>)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // log pour signaler l'appel à la méthode "onCreate"
        Log.i(TAG, "onCreate Called");
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main)

        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        main_cancel.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            main_email.text?.clear()
            main_password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            main_email.error = null
            main_password.error = null
        }

        main_new_account.setOnClickListener {
            val intent = Intent(this, NewAccountActivity::class.java)
            launchRegisterActivity.launch(intent)
        }

        main_validate.setOnClickListener {
            //on réinitialise les messages d'erreur
            main_email.error = null
            main_password.error = null

            //on récupère le contenu de deux champs dans des variables de type String
            val emailInput = main_email.text?.toString()
            val passwordInput = main_password.text?.toString()

            if(emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(TAG, "Au moins un des deux champs est vide")
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if(emailInput.isNullOrEmpty())
                    main_email.error = getString(R.string.main_mandatory_field)
                if(passwordInput.isNullOrEmpty())
                    main_password.error = getString(R.string.main_mandatory_field)
                // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
                // doit être appliqué
                return@setOnClickListener
            }
/*
            //Si le format de l'email est invalide, lance le toast avec le message d'erreur
            if (emailInput?.contains('@') == false) {
                val toast = Toast.makeText(applicationContext, getString(R.string.toast_email_error), Toast.LENGTH_SHORT)
                toast.show()
                return@setOnClickListener
            }*/

            if (!Utility.isEmailOk(applicationContext, emailInput, getString(R.string.toast_email_error))) {
                return@setOnClickListener
            }

            //Si les credentials sont incorrectes on affiche un message d'erreur dans un Dialogue
            if (!credentials.contains(Pair(emailInput, passwordInput)))
            {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle(getString(R.string.dialog_invalid_credentials_title))
                alertDialogBuilder.setMessage(getString(R.string.dialog_invalid_credentials_error))
                alertDialogBuilder.show()
                return@setOnClickListener

            }

            //Si les credentials sont valides, on lance la nouvelle activity
            else
            {
                val intent = Intent(this, SecondaryActivity::class.java).apply {
                    putExtra("emailInput", emailInput)
                }
                startActivity(intent)
            }
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
