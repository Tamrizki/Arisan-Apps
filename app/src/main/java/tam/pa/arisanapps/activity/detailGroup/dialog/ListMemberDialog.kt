package tam.pa.arisanapps.activity.detailGroup.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.dialog_member.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.model.DataListMember

class ListMemberDialog(val ctx: Context, val dataMember: DataListMember): Dialog(ctx), View.OnClickListener {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_member)
        tvName.text = dataMember.nameMember
        btnCall.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == btnPaid ){

        }else if (view == btnCall){
            val call = Intent(Intent.ACTION_DIAL)
            call.data = Uri.parse("tel:"+"081234567890")
            ctx.startActivity(call)
        }
    }
}