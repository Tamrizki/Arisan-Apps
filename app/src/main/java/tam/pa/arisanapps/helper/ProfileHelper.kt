package tam.pa.arisanapps.helper

import android.content.Context
import tam.pa.arisanapps.R

class ProfileHelper(val context: Context){
    fun getProfileImage(index: Int): Int{
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
}