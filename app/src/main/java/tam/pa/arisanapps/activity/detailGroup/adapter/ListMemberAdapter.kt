package tam.pa.arisanapps.activity.detailGroup.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.detailGroup.dialog.ListMemberDialog
import tam.pa.arisanapps.activity.home.adapter.ListGroupAdapter
import tam.pa.arisanapps.model.DataListMember

class ListMemberAdapter(val context: Context, val list: MutableList<DataListMember>): RecyclerView.Adapter<ListMemberAdapter.vholder>() {
    class vholder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPhone: TextView = view.findViewById(R.id.tvPhone)
        val llBackground: LinearLayout = view.findViewById(R.id.llBackground)
        val tvStatusPayment: TextView = view.findViewById(R.id.tvStatusPayment)
        fun bind(get: DataListMember) {
            tvName.text = get.nameMember
            tvPhone.text = get.phone
            if (get.statusPayment.equals("1")){
                tvStatusPayment.text = itemView.resources.getString(R.string.paid)
                tvStatusPayment.setTextColor(itemView.resources.getColor(R.color.green))
            }else{
                tvStatusPayment.text = itemView.resources.getString(R.string.unpaid)
                tvStatusPayment.setTextColor(itemView.resources.getColor(R.color.red))
            }
            if (get.win.equals("1")){
                llBackground.setBackgroundDrawable(itemView.resources.getDrawable(R.drawable.bg_list_member_dark))
            }else{
                llBackground.setBackgroundDrawable(itemView.resources.getDrawable(R.drawable.bg_list_member_white))
            }
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