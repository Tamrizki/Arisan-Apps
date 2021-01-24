package tam.pa.arisanapps.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import tam.pa.arisanapps.model.DataListGroup
import tam.pa.arisanapps.model.DataListMember

class DbHandler(val context: Context): SQLiteOpenHelper(context, DbHandler.DB_NAME, null, DbHandler.DB_VERSION) {
    companion object{
        private val DB_NAME = "MyDB"
        private val DB_VERSION = 1
        private val TABLE_NAME = "TBGroup"
        private val ID = "Id"
        private val IDM = "IdM"
        private val IDM_GROUP = "IdMGroup"
        private val NAME = "Name"
        private val NAMEM = "NameM"
        private val IMG = "Img"
        private val TOTAL_MEMBER = "TotalMember"
        private val PRICE = "Price"
        private val TYPE = "TypeGroup"
        private val TABLE_MEMBER = "TBMember"
        private val PHONE = "Phone"
        private val PAYMENT_STATUS = "PaymentStatus"
        private val WIN_STATUS = "WinStatus"

        val CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +" ("+
                ID +" INTEGER PRIMARY KEY," +
                NAME + " TEXT," +
                IMG + " TEXT,"+
                TYPE + " TEXT,"+
                PRICE+ " TEXT,"+
                TOTAL_MEMBER+ " INTEGER);"

        val CREATE_TB_MEMBER = "CREATE TABLE "+ TABLE_MEMBER +" ("+
                IDM +" INTEGER PRIMARY KEY," +
                IDM_GROUP +" INTEGER," +
                NAMEM + " TEXT,"+
                PHONE + " TEXT,"+
                PAYMENT_STATUS + " TEXT,"+
                WIN_STATUS + " TEXT);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TB_MEMBER)
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE_MEMBER = "DROP TABLE IF EXISTS "+ TABLE_MEMBER
        val DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME
        db!!.execSQL(DROP_TABLE_MEMBER)
        db!!.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun insertMember(data: DataListMember): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(IDM, data.id)
        values.put(IDM_GROUP, data.idGroup)
        values.put(NAMEM, data.nameMember)
        values.put(PHONE, data.phone)
        values.put(PAYMENT_STATUS, data.statusPayment)
        values.put(WIN_STATUS, data.win)
        val _success = db.insert(TABLE_MEMBER, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }
    fun updateMember(data: DataListMember): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(IDM_GROUP, data.idGroup)
        values.put(NAMEM, data.nameMember)
        values.put(PHONE, data.phone)
        values.put(PAYMENT_STATUS, data.statusPayment)
        values.put(WIN_STATUS, data.win)
        val _success = db.update(TABLE_MEMBER, values, IDM + "=?", arrayOf(data.id.toString())).toLong()
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }
    fun readMember(idGroup: Int): MutableList<DataListMember>{
        val listData: MutableList<DataListMember> = ArrayList()
        val db = writableDatabase
        val query =  "SELECT * FROM $TABLE_MEMBER WHERE $IDM_GROUP = $idGroup"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                listData.add(DataListMember(
                        result.getString(result.getColumnIndex(IDM)).toInt(),
                        result.getString(result.getColumnIndex(IDM_GROUP)).toInt(),
                        result.getString(result.getColumnIndex(NAMEM)),
                        result.getString(result.getColumnIndex(PHONE)),
                        result.getString(result.getColumnIndex(PAYMENT_STATUS)),
                        result.getString(result.getColumnIndex(WIN_STATUS))
                ))
            }while (result.moveToNext())
        }
        return listData
    }
    fun deleteMember(id: Int): Boolean{
        val db = this.writableDatabase
        val _success = db.delete(TABLE_MEMBER, IDM + "=?", arrayOf(id.toString())).toLong()
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun insertData(data: DataListGroup): Boolean{
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(ID, data.id)
        value.put(NAME, data.nameGroup)
        value.put(IMG, data.img)
        value.put(TYPE, data.type)
        value.put(PRICE, data.priceMember)
        value.put(TOTAL_MEMBER, data.totalMember)
        val _success = db.insert(TABLE_NAME, null, value)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun deleteData(id: Int): Boolean{
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(id.toString())).toLong()
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun updateData(data: DataListGroup): Boolean{
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(NAME, data.nameGroup)
        value.put(IMG, data.img)
        value.put(TYPE, data.type)
        value.put(PRICE, data.priceMember)
        value.put(TOTAL_MEMBER, data.totalMember)
        val _success = db.update(TABLE_NAME, value, ID + "=?", arrayOf(data.id.toString())).toLong()
        db.close()
        if (Integer.parseInt("$_success") != -1){
            Toast.makeText(context, "Success input data!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "GAGAL input data!", Toast.LENGTH_LONG).show()
        }
        return (Integer.parseInt("$_success") != -1)
    }

    fun readDataGroup(index: Int): DataListGroup{
        var dataGroup = DataListGroup(0, "", "", "", 0, "")
        val db = writableDatabase
        val query =  "SELECT * FROM $TABLE_NAME WHERE $ID = $index"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do { dataGroup = DataListGroup(
                        result.getString(result.getColumnIndex(ID)).toInt(),
                        result.getString(result.getColumnIndex(NAME)),
                        result.getString(result.getColumnIndex(TYPE)),
                        result.getString(result.getColumnIndex(PRICE)),
                        result.getString(result.getColumnIndex(TOTAL_MEMBER)).toInt(),
                        result.getString(result.getColumnIndex(IMG)))
            }while (result.moveToNext())
        }
        return dataGroup
    }

    fun readData(): MutableList<DataListGroup>{
        val listData: MutableList<DataListGroup> = ArrayList()
        val db = writableDatabase
        val query =  "SELECT * FROM "+TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                listData.add(DataListGroup(
                        result.getString(result.getColumnIndex(ID)).toInt(),
                        result.getString(result.getColumnIndex(NAME)),
                        result.getString(result.getColumnIndex(TYPE)),
                        result.getString(result.getColumnIndex(PRICE)),
                        result.getString(result.getColumnIndex(TOTAL_MEMBER)).toInt(),
                        result.getString(result.getColumnIndex(IMG))
                ))
            }while (result.moveToNext())
        }
        return listData
    }
}