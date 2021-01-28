package tam.pa.arisanapps.activity.creategroup.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.ViewGetProfile
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.model.DataListMember

class MemberAdapter(val list: MutableList<DataListMember>, val context: Context, viewGetProfile: ViewGetProfile): RecyclerView.Adapter<MemberAdapter.vholder>() {
    val db: DbHandler = DbHandler(context)
    val alert = AlertDialog.Builder(context)
    val viewList = viewGetProfile
    class vholder(view: View): RecyclerView.ViewHolder(view) {
        val vw = view
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPhone: TextView = view.findViewById(R.id.tvPhone)
        val tvStatusPayment: TextView = view.findViewById(R.id.tvStatusPayment)
        val btnRemove: ImageView = view.findViewById(R.id.btnRemoveMember)
        val llBackground: LinearLayout = view.findViewById(R.id.llBackground)
        fun bind(get: DataListMember) {
            tvName.setTextColor(itemView.resources.getColor(R.color.white))
            tvPhone.setTextColor(itemView.resources.getColor(R.color.cl_red))
            tvName.text = get.nameMember
            tvPhone.text = get.phone
            tvStatusPayment.visibility = View.GONE
            llBackground.setBackgroundDrawable(itemView.resources.getDrawable(R.drawable.bg_list_dark))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vholder {
        return vholder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list_member, parent, false))
    }
    
    override fun onBindViewHolder(holder: vholder, position: Int) {
        holder.bind(list.get(position))
        holder.btnRemove.visibility = View.VISIBLE
        holder.btnRemove.setOnClickListener {
            alert.setTitle("Hapus Anggota")
            alert.setMessage("Apakah anda yalin akan menghapus "+list.get(position).nameMember+" ?")
                    .setPositiveButton("Hapus", DialogInterface.OnClickListener { dialogInterface, i ->
                        db.deleteMember(list.get(position).id)
                        viewList.onUpdateList()
                    }).setNegativeButton("Batal", DialogInterface.OnClickListener { dialogInterface, i ->

                    }).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}