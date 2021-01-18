package tam.pa.arisanapps.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.home.adapter.ListGroupAdapter
import tam.pa.arisanapps.model.DataListGroup

class HomeActivity : AppCompatActivity() {
    var listGroup: ArrayList<DataListGroup> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataArray()
        rvListGroup.setHasFixedSize(true)
        rvListGroup.layoutManager = LinearLayoutManager(this)
        rvListGroup.adapter = ListGroupAdapter(listGroup, this)
    }

    fun setDataArray(){
        for (num in 1..10){
            listGroup.add(DataListGroup("1", "group name 1", "Bulanan", "10", ""))
        }
    }
}