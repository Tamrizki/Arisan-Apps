package tam.pa.arisanapps.activity.creategroup.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_image_profile.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.dialog.adapter.ProfileAdapter
import tam.pa.arisanapps.activity.creategroup.getProfile

class ProfileDialog(val ctx: Context, val getProfile: getProfile): BottomSheetDialog(ctx, R.style.BottomSheetDialogTheme){
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_image_profile)
        rvProfile.setHasFixedSize(true)
        rvProfile.adapter = ProfileAdapter(context, getProfile)
        rvProfile.layoutManager = GridLayoutManager(context, 3)
    }

}