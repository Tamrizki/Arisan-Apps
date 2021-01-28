package tam.pa.arisanapps.activity.home

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.CreateGroupActivity
import tam.pa.arisanapps.activity.home.adapter.ListGroupAdapter
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.model.DataListGroup

class HomeActivity : AppCompatActivity(), View.OnClickListener, HomeView{
    lateinit var homeView: HomeView
    var listGroup: ArrayList<DataListGroup> = ArrayList()
    lateinit var mMediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeView = this

        setListGroup()
        btnCreateGroup.setOnClickListener(this)
    }
    private fun setListGroup(){
        listGroup.clear()
        setDataArray()
        rvListGroup.setHasFixedSize(true)
        rvListGroup.layoutManager = LinearLayoutManager(this)
        rvListGroup.adapter = ListGroupAdapter(listGroup, this, homeView)
    }
    fun setDataArray(){
        var db = DbHandler(this)
        var getData = db.readData()
        if (getData.size < 1){
            rvListGroup.visibility = View.GONE
            imgEmptyGroup.visibility = View.VISIBLE
        }else{
            rvListGroup.visibility = View.VISIBLE
            imgEmptyGroup.visibility = View.GONE
            for (i in 0..(getData.size-1)){
                listGroup.add(DataListGroup(getData.get(i).id, getData.get(i).nameGroup, getData.get(i).type, getData.get(i).priceMember,getData.get(i).totalMember, getData.get(i).img))
            }
        }
    }

    override fun onClick(view: View?) {
        if (view == btnCreateGroup){
            startActivity(Intent(this, CreateGroupActivity::class.java))
        }
    }

    override fun onUpdateList() {
        setListGroup()
    }
}