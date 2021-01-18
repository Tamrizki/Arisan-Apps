package tam.pa.arisanapps.activity.home

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.CreateGroupActivity
import tam.pa.arisanapps.activity.home.adapter.ListGroupAdapter
import tam.pa.arisanapps.model.DataListGroup

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    var listGroup: ArrayList<DataListGroup> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataArray()
        rvListGroup.setHasFixedSize(true)
        rvListGroup.layoutManager = LinearLayoutManager(this)
        rvListGroup.adapter = ListGroupAdapter(listGroup, this)

        btnCreateGroup.setOnClickListener(this)
    }

    fun setDataArray(){
        for (num in 1..10){
            listGroup.add(DataListGroup("1", "group name 1", "Bulanan", "10", ""))
        }
    }

    override fun onClick(view: View?) {
        if (view == btnCreateGroup){
            startActivity(Intent(this, CreateGroupActivity::class.java))
        }
    }
}