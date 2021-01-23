package tam.pa.arisanapps.activity.creategroup.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.getProfile

class ProfileAdapter(val context: Context, val getProfile: getProfile): RecyclerView.Adapter<ProfileAdapter.vholder>() {
    val list = arrayListOf(R.drawable.img_,
            R.drawable.img_abstract,
            R.drawable.img_leaves,
            R.drawable.img_mountain,
            R.drawable.img_scrapbook,
            R.drawable.img_polka,
            R.drawable.img_background,
            R.drawable.img_cactus,
            R.drawable.img_cat,
            R.drawable.img_flowerjpg,
            R.drawable.img_confetti,
            R.drawable.img_leaf,
            R.drawable.img_rabbits,
            R.drawable.img_shabby,
            R.drawable.img_wood,
            )
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
            getProfile.onGetProflie(position+1)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}