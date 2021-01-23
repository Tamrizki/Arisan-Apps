package tam.pa.arisanapps.activity.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.arisanapps.R
import tam.pa.arisanapps.model.DataListGroup
import tam.pa.arisanapps.activity.detailGroup.DetailGroupActivity
import tam.pa.arisanapps.activity.home.dialog.EditListDialog
import tam.pa.arisanapps.helper.ProfileHelper

class ListGroupAdapter(val listGroup: ArrayList<DataListGroup>, val context: Context):
    RecyclerView.Adapter<ListGroupAdapter.vholder>() {
    val profileHelper = ProfileHelper(context)
    class vholder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameGroup: TextView = view.findViewById(R.id.tvNameGroup)
        val tvType: TextView = view.findViewById(R.id.tvType)
        val tvMember: TextView = view.findViewById(R.id.tvMember)
        val imgProfile: ImageView = view.findViewById(R.id.imgProfile)

        fun bind(get: DataListGroup, profileHelper: ProfileHelper) {
            tvNameGroup.text = get.nameGroup
            tvType.text = get.type
            tvMember.text = get.totalMember.toString()+" member"
            imgProfile.setImageResource(profileHelper.getProfileImage(get.img!!.toInt()))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vholder {
        return vholder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list_group, parent, false))
    }

    override fun onBindViewHolder(holder: vholder, position: Int) {
        holder.bind(listGroup.get(position), profileHelper)
        holder.itemView.setOnLongClickListener {
            val dialog = EditListDialog(context, listGroup.get(position).nameGroup)
            dialog.show()
            return@setOnLongClickListener(true)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailGroupActivity::class.java)
            intent.putExtra("dataGroup", listGroup.get(position))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listGroup.size
    }
}