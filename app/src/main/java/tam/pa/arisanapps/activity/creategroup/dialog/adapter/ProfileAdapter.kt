package tam.pa.arisanapps.activity.creategroup.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import tam.pa.arisanapps.R
import tam.pa.arisanapps.helper.SharedPref

class ProfileAdapter(val context: Context): RecyclerView.Adapter<ProfileAdapter.vholder>() {
    val list = arrayListOf(R.drawable.img_, R.drawable.img_abstract, R.drawable.img_leaves, R.drawable.img_mountain, R.drawable.img_scrapbook, R.drawable.img_polka)
    val sharedPref = SharedPref(context)
    class vholder(view: View): RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgProfile)
        fun bind(get: Int) {
            img.setImageResource(get)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vholder {
        return vholder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list_profile, parent, false))
    }

    override fun onBindViewHolder(holder: vholder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
            sharedPref.setValue("profile", (position+1).toString())

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}