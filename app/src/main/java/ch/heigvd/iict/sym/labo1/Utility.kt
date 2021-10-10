package ch.heigvd.iict.sym.labo1

import android.content.Context
import android.widget.Toast

class Utility {
    companion object {
        fun isEmailOk (applicationContext : Context, emailInput : String?, message : String?) : Boolean{
            if (emailInput?.contains('@') == false) {
                val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
                toast.show()
                return false
            }
            return true
        }
    }
}