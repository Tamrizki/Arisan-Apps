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
import tam.pa.arisanapps.activity.home.HomeView
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.model.DataListGroup

class EditListDialog(val ctx: Context, val dataGroup: DataListGroup, homeView: HomeView): Dialog( ctx), View.OnClickListener {
    lateinit var dbHandler: DbHandler
    var homeView = homeView
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_edit_list)
        dbHandler = DbHandler(ctx)
        tvTitle.text = dataGroup.nameGroup
        btnDelete.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == btnDelete){
            dbHandler.deleteData(dataGroup.id)
            dbHandler.deleteAllMemberGroup(dataGroup.id)
            homeView.onUpdateList()
            dismiss()
        }
    }

}