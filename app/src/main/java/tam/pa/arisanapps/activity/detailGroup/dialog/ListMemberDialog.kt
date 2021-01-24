package tam.pa.arisanapps.activity.detailGroup.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_member.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.detailGroup.DetailGroupActivity
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.model.DataListMember

class ListMemberDialog(val ctx: Context, val dataMember: DataListMember): Dialog(ctx), View.OnClickListener {
    lateinit var db: DbHandler
    var paid = ""
    var win = ""
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_member)
        db = DbHandler(ctx)
        setDataDialog()
        btnCall.setOnClickListener(this)
        btnSimpan.setOnClickListener(this)
    }

    private fun setDataDialog() {
        tvName.text = dataMember.nameMember
        if (!dataMember.win.equals("0"))
            cbWin.isChecked = true
        else
            cbWin.isChecked = false
        if (!dataMember.statusPayment.equals("0"))
            cbPaid.isChecked = true
        else
            cbPaid.isChecked = false
    }

    override fun onClick(view: View?) {
        if (view == btnSimpan ){
            if (cbPaid.isChecked) paid = "1"
            else paid = "0"
            if (cbWin.isChecked) win = "1"
            else win = "0"
            val update = db.updateMember(DataListMember(dataMember.id,
            dataMember.idGroup,
            dataMember.nameMember,
            dataMember.phone,
            paid,
            win))
            if (update){
                context.startActivity(Intent(context, DetailGroupActivity::class.java))
            }else{
                Toast.makeText(context, "Data gagal diperbaharui!", Toast.LENGTH_SHORT).show()
            }
        }else if (view == btnCall){
            val call = Intent(Intent.ACTION_DIAL)
            call.data = Uri.parse("tel:"+dataMember.phone)
            ctx.startActivity(call)
        }
    }
}