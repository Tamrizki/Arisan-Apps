package tam.pa.arisanapps.model

import android.os.Parcel
import android.os.Parcelable

data class DataListGroup(
    val id: String,
    val nameGroup: String,
    val type: String,
    val member: String,
    val img: String, ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<DataListGroup> {
        override fun createFromParcel(parcel: Parcel): DataListGroup {
            return DataListGroup(parcel)
        }

        override fun newArray(size: Int): Array<DataListGroup?> {
            return arrayOfNulls(size)
        }
    }

}