package tam.pa.arisanapps.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPref (val context: Context){
    companion object{
        const val SPLASH_SCREEN = "PROFILR_PIC"
    }
    var sharedPref = context.getSharedPreferences(SPLASH_SCREEN, 0)

    fun setValue(key: String, value: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun getValue(key: String): String?{
        return sharedPref.getString(key, "")
    }
}