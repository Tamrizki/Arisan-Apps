package tam.pa.arisanapps.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import tam.pa.arisanapps.model.DataListGroup

private val DB_NAME = "MyDB"
private val DB_VERSION = 1
private val TABLE_NAME = "Group"
private val ID = "Id"
private val NAME = "Name"
private val IMG = "Img"
private val TOTAL_MEMBER = "TotalMember"
private val PRICE = "Price"
private val TYPE = "TypeGroup"

class DatabaseHandler(val context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +"("+
                ID +" INTEGER PRIMARY KEY," +
                NAME + "TEXT," +
                IMG + "TEXT,"
                TYPE + "TEXT,"
                PRICE + "TEXT,"+
                TOTAL_MEMBER + "INTEGER);"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(dataGroup: DataListGroup){
        val db = this.writableDatabase
        var values = ContentValues()

    }
}