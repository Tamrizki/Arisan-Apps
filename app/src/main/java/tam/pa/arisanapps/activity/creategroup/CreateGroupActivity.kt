package tam.pa.arisanapps.activity.creategroup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_create_group.*
import tam.pa.arisanapps.R
import tam.pa.arisanapps.activity.creategroup.adapter.MemberAdapter
import tam.pa.arisanapps.activity.creategroup.dialog.ProfileDialog
import tam.pa.arisanapps.activity.home.HomeActivity
import tam.pa.arisanapps.helper.DbHandler
import tam.pa.arisanapps.helper.PoinHelper
import tam.pa.arisanapps.helper.SharedPref
import tam.pa.arisanapps.model.DataListGroup
import tam.pa.arisanapps.model.DataListMember

class CreateGroupActivity : AppCompatActivity(), View.OnClickListener, ViewGetProfile {
    lateinit var ViewGetProfile: ViewGetProfile
    lateinit var db: DbHandler
    lateinit var poinHelper: PoinHelper
    var idGroup: Int = 0
    var img = "0"
    lateinit var adapter: MemberAdapter
    lateinit var listMember: MutableList<DataListMember>
    lateinit var sharedPref: SharedPref
    lateinit var dialog :ProfileDialog
    lateinit var datagroup: DataListGroup
    var input: Boolean = false
    var modeEdit: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)
        sharedPref = SharedPref(this)
        poinHelper = PoinHelper(this)
        ViewGetProfile = this
        db = DbHandler(this)
        dialog = ProfileDialog(this, ViewGetProfile)
        idGroup = db.readData().size+1
        try {
            datagroup = intent.getParcelableExtra("editGroup")!!
            setFormData(datagroup)
            idGroup = datagroup.id
            modeEdit = true
        }catch (e: Exception){}
        setListMember()
        btnSave.setOnClickListener(this)
        rlBack.setOnClickListener(this)
        btnSaveMember.setOnClickListener(this)
        btnProfile.setOnClickListener(this)
    }
    private fun setFormData(datagroup: DataListGroup) {
        etNameGroup.text = datagroup.nameGroup.toEditable()
        etPriceMember.text = datagroup.priceMember.toEditable()
        listMember = db.readMember(datagroup.id)
        btnProfile.setImageResource(poinHelper.getProfileImage(datagroup.img!!.toInt()))
    }

    private fun setListMember(){
        listMember = db.readMember(idGroup)
        adapter = MemberAdapter(listMember, this, ViewGetProfile)
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
            if (modeEdit){
                if (validateForm()){
                    Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }
            }else {
                onBackPressed()
            }
        }else if (view == btnSaveMember){
            if (validateFormMember()){
                val input= db.insertMember(DataListMember(getIdMember(), idGroup, etNameMember.text.toString(), etPhoneMember.text.toString(), "0", "0"))
                if (!input){
                    Toast.makeText(this, getString(R.string.fail_input), Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, getString(R.string.success_input), Toast.LENGTH_SHORT).show()
                    clearformMember()
                    setListMember()
                }
            }
        } else if (view == btnProfile){
            dialog.show()
            dialog.setCanceledOnTouchOutside(true)
        }
    }

    private fun inputData(nameGroup: String, typeGroup: String, price: String) {
        var totalMember = db.readMember(idGroup).size
        if (totalMember>0){
            if (modeEdit) {
                input = db.updateData(DataListGroup(idGroup, nameGroup, typeGroup, price, totalMember, img))
            }else {
                input = db.insertData(DataListGroup(idGroup, nameGroup, typeGroup, price, totalMember, img))
            }
            if (!input)
                Toast.makeText(this, getString(R.string.fail_input), Toast.LENGTH_SHORT).show()
            else{
                Toast.makeText(this, getString(R.string.success_input), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                clearFormGroup()
                finish()
            }
        }else{
            Toast.makeText(this, getString(R.string.info_minim_member), Toast.LENGTH_SHORT).show()
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

    override fun onGetProflie(index: Int) {
        img = index.toString()
        btnProfile.setImageResource(poinHelper.getProfileImage(index))
        dialog.dismiss()
    }

    override fun onUpdateList() {
        setListMember()
    }
}