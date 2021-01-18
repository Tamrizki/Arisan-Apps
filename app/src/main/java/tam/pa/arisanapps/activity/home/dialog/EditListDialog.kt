package tam.pa.arisanapps.activity.home.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_edit_list.*
import tam.pa.arisanapps.R

class EditListDialog (val ctx: Context, val nameList: String): Dialog( ctx), View.OnClickListener {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_edit_list)
        tvTitle.text = nameList
        btnDelete.setOnClickListener(this)
        btnEdit.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == btnDelete){
            Toast.makeText(ctx, "Delete clicked!", Toast.LENGTH_LONG).show()
        }
        else if (view == btnEdit){
            Toast.makeText(ctx, "Edit clicked!", Toast.LENGTH_LONG).show()
        }
    }

}