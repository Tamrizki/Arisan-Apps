package tam.pa.arisanapps.activity.creategroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_create_group.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.adapter.MemberAdapter
import tam.pa.arisanapps.activity.home.HomeActivity
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.model.DataListGroup
import tam.pa.arisanapps.model.DataListMember

class CreateGroupActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var db: DbHandler
    var idGroup: Int = 0
    lateinit var adapter: MemberAdapter
    lateinit var listMember: MutableList<DataListMember>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)
        db = DbHandler(this)
        idGroup = db.readData().size+1
        setListMember()

        btnSave.setOnClickListener(this)
        rlBack.setOnClickListener(this)
        btnSaveMember.setOnClickListener(this)
    }
    private fun setListMember(){
        listMember = db.readMember(idGroup)
        adapter = MemberAdapter(listMember, this)
        rvMember.setHasFixedSize(true)
        rvMember.adapter = adapter
        rvMember.layoutManager = LinearLayoutManager(this)
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onClick(view: View?) {
        if (view == btnSave){
            if (validateForm()){
                inputData(etNameGroup.text.toString(), spType.selectedItem.toString(), etPriceMember.text.toString())
            }
        }else if (view == rlBack){
            onBackPressed()
        }else if (view == btnSaveMember){
            if (validateFormMember()){
                val input= db.insertMember(DataListMember(getIdMember(), idGroup, etNameMember.text.toString(), etPhoneMember.text.toString(), "0", "0"))
                if (!input){
                    Toast.makeText(this, getString(R.string.fail_input), Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this, getString(R.string.success_input), Toast.LENGTH_LONG).show()
                    clearformMember()
                    setListMember()
                }
            }
        }
    }

    private fun inputData(nameGroup: String, typeGroup: String, price: String) {
        var totalMember = db.readMember(idGroup).size
        if (totalMember>0){
            val input = db.insertData(DataListGroup(idGroup, nameGroup, typeGroup, price, totalMember, ""))
            if (!input)
                Toast.makeText(this, getString(R.string.fail_input), Toast.LENGTH_LONG).show()
            else{
                Toast.makeText(this, getString(R.string.success_input), Toast.LENGTH_LONG).show()
                clearFormGroup()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }else{
            Toast.makeText(this, getString(R.string.info_minim_member), Toast.LENGTH_LONG).show()
        }
    }

    private fun validateForm(): Boolean{
        if (etNameGroup.text.isNullOrEmpty()) {
            tilNameGroup.error = getString(R.string.valid_form)
            tilNameGroup.requestFocus()
            return false
        } else if (etPriceMember.text.isNullOrEmpty()) {
            tilPrice.error = getString(R.string.valid_form)
            tilPrice.requestFocus()
            return false
        }
        return true
    }

    private fun validateFormMember(): Boolean{
        if (etNameMember.text.isNullOrEmpty()){
            tilNameMember.error = getString(R.string.valid_form)
            tilNameMember.requestFocus()
            return false
        }else if (etPhoneMember.text.isNullOrEmpty()){
            tilPhone.error = getString(R.string.valid_form)
            tilPhone.requestFocus()
            return false
        }
        return true
    }

    private fun getIdMember(): Int{
        var number = idGroup.toString()
        for (num in 1..5){
            number += (0..9).random().toString()
        }
        return number.toInt()
    }
    private fun clearformMember(){
        etNameMember.text = "".toEditable()
        etPhoneMember.text = "".toEditable()
    }
    private fun clearFormGroup(){
        etNameGroup.text = "".toEditable()
        etPriceMember.text = "".toEditable()
    }
}