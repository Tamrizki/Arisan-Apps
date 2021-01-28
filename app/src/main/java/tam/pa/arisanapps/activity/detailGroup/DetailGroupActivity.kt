package tam.pa.arisanapps.activity.detailGroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail_group.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.CreateGroupActivity
import tam.pa.arisanapps.activity.detailGroup.adapter.ListMemberAdapter
import tam.pa.arisanapps.activity.home.HomeActivity
import tam.pa.arisanapps.activity.shake.ShakeActivity
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.helper.PoinHelper
import tam.pa.arisanapps.helper.SharedPref
import tam.pa.arisanapps.model.DataListGroup
import kotlin.math.log

class DetailGroupActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var poinHelper: PoinHelper
    lateinit var dataGroup: DataListGroup
    lateinit var db: DbHandler
    lateinit var sharedPref: SharedPref
    var idGroup = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_group)
        poinHelper = PoinHelper(this)
        db = DbHandler(this)
        sharedPref = SharedPref(this)
        idGroup = sharedPref.getValue("DetailIdGroup").toString()
        dataGroup = db.readDataGroup(idGroup.toInt())
        setDataGroup()
        setupList()
        rlBack.setOnClickListener(this)
        btnVote.setOnClickListener(this)
        btnEditGroup.setOnClickListener(this)
    }

    private fun setupList() {
        rvListMember.setHasFixedSize(true)
        rvListMember.layoutManager = LinearLayoutManager(this)
        rvListMember.adapter = ListMemberAdapter(this, db.readMember(dataGroup.id))
    }

    private fun setDataGroup() {
        tvTitlePage.text = dataGroup.nameGroup
        var tvTotal = dataGroup.priceMember.toInt() * db.readMember(dataGroup.id).size
        tvTotalMoney.text = poinHelper.formatRupiah(tvTotal.toLong())
        tvPriceMember.text = poinHelper.formatRupiah(dataGroup.priceMember.toLong())
        imgProfile.setImageResource(poinHelper.getProfileImage(dataGroup.img!!.toInt()))
    }

    override fun onClick(view: View?) {
        if (view == rlBack){
            onBackPressed()
        } else if (view == btnVote){
            if (db.readUnpaidMember(idGroup.toInt()).size == 0){
                val intent = Intent(this, ShakeActivity::class.java)
                intent.putExtra("idgroup", dataGroup.id)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Masih ada member yang belum membayar!", Toast.LENGTH_SHORT).show()
            }
        } else if (view == btnEditGroup){
            val intent = Intent(this, CreateGroupActivity::class.java)
            intent.putExtra("editGroup", dataGroup)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
    }

}