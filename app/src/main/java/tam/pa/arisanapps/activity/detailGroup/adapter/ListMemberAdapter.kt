package tam.pa.arisanapps.activity.detailGroup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.detailGroup.dialog.ListMemberDialog
import tam.pa.arisanapps.activity.home.adapter.ListGroupAdapter
import tam.pa.arisanapps.model.DataListMember

class ListMemberAdapter(val context: Context, val list: ArrayList<DataListMember>): RecyclerView.Adapter<ListMemberAdapter.vholder>() {
    class vholder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPhone: TextView = view.findViewById(R.id.tvPhone)
        val tvStatusPayment: TextView = view.findViewById(R.id.tvStatusPayment)
        fun bind(get: DataListMember) {
            tvName.text = get.nameMember
            tvPhone.text = get.phone
            tvStatusPayment.text = get.statusPayment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vholder {
        return vholder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_list_member, parent, false)
        )
    }

    override fun onBindViewHolder(holder: vholder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
            val dialog = ListMemberDialog(context, list.get(position))
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}