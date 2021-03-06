package tam.pa.arisanapps.helper

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import tam.pa.arisanapps.R
import tam.pa.arisanapps.model.DataListMember
import java.text.NumberFormat
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

    fun formatRupiah(number: Long): String{
        val localId = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localId)
        return numberFormat.format(number)
    }

    fun cekStatusPayment(list: MutableList<DataListMember>): Boolean{
        var complete = false
        Log.d("asda", list.toString())
        for (num in 0..list.size){
            if (!list[num].statusPayment.equals("0"))
                complete = true
            else
                complete = false
        }
        return complete
    }

    fun playSound(context: Context) {
        val mMediaPlayer = MediaPlayer.create(context, R.raw.ta_da)
        mMediaPlayer!!.isLooping = false
        mMediaPlayer!!.start()
    }
}