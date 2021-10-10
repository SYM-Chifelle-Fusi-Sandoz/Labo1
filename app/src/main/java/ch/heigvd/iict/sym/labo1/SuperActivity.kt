package ch.heigvd.iict.sym.labo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class SuperActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(getTag(), "onCreate Called");
    }

    override fun onStart() {
        super.onStart()
        Log.i(getTag(), "onStart Called");
    }

    override fun onResume() {
        super.onResume()
        Log.i(getTag(), "onResume Called");
    }

    override fun onPause() {
        super.onPause()
        Log.i(getTag(), "onPause Called");
    }

    override fun onStop() {
        super.onStop()
        Log.i(getTag(), "onStop Called");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(getTag(), "onDestroy Called");
    }

    abstract fun getTag(): String?


}