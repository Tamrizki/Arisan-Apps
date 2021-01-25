package tam.pa.arisanapps.helper

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat.getSystemService
import tam.pa.arisanapps.R
import java.util.*

class PoinHelper(val context: Context){
    lateinit var vibrator: Vibrator

    fun getProfileImage(index: Int?): Int{
        var imgDrawable = 0
        when(index){
            1 -> imgDrawable = R.drawable.img_
            2 -> imgDrawable = R.drawable.img_abstract
            3 -> imgDrawable = R.drawable.img_leaves
            4 -> imgDrawable = R.drawable.img_mountain
            5 -> imgDrawable = R.drawable.img_scrapbook
            6 -> imgDrawable = R.drawable.img_polka
            7 -> imgDrawable = R.drawable.img_background
            8 -> imgDrawable = R.drawable.img_cactus
            9 -> imgDrawable = R.drawable.img_cat
            10 -> imgDrawable = R.drawable.img_flowerjpg
            11 -> imgDrawable = R.drawable.img_confetti
            12 -> imgDrawable = R.drawable.img_leaf
            13 -> imgDrawable = R.drawable.img_rabbits
            14 -> imgDrawable = R.drawable.img_shabby
            15 -> imgDrawable = R.drawable.img_wood
        }
        return imgDrawable
    }
    fun getVibrator(vibrateMS: Long){
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(vibrateMS, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
        }
    }

    fun getRandom(to: Int) : Int {
        val random = Random()
        return random.nextInt(to - 0) + 0
    }
}