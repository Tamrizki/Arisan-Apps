package tam.pa.arisanapps.activity.detailGroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail_group.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.detailGroup.adapter.ListMemberAdapter
import tam.pa.arisanapps.model.DataListGroup
import tam.pa.arisanapps.model.DataListMember

class DetailGroupActivity : AppCompatActivity() {

    var list: ArrayList<DataListMember> = ArrayList()
    lateinit var dataGroup: DataListGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_group)
        dataGroup = intent.getParcelableExtra("dataGroup")!!
        setDataArray()
        setDataGroup()
        rvListMember.setHasFixedSize(true)
        rvListMember.layoutManager = LinearLayoutManager(this)
        rvListMember.adapter = ListMemberAdapter(this, list)

    }

    private fun setDataGroup() {
        tvTitlePage.text = dataGroup.nameGroup
        var tvTotal = dataGroup.priceMember.toInt() * list.size
        tvTotalMoney.text = tvTotal.toString()
        tvPriceMember.text = "Rp. "+dataGroup.priceMember
    }

    private fun setDataArray() {
        for (data in 1..5){
//            list.add(DataListMember(data, "nama "+data.toString(), data.toString(), false, false))
//            list.add(DataListMember(data, "nama "+data.toString(), data.toString(), true, true))
        }
    }
}