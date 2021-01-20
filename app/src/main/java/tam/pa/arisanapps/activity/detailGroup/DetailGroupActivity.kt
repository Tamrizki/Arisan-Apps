package tam.pa.arisanapps.activity.detailGroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail_group.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.detailGroup.adapter.ListMemberAdapter
import tam.pa.arisanapps.model.DataListMember

class DetailGroupActivity : AppCompatActivity() {

    var list: ArrayList<DataListMember> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_group)

        setDataArray()
        rvListMember.setHasFixedSize(true)
        rvListMember.layoutManager = LinearLayoutManager(this)
        rvListMember.adapter = ListMemberAdapter(this, list)

    }

    private fun setDataArray() {
        for (data in 1..10){
            list.add(DataListMember(data.toString(), "nama "+data.toString(), data.toString(), "Has paid"))
        }
    }
}