package tam.pa.arisanapps.model

import android.os.Parcel
import android.os.Parcelable

data class DataListMember(
    val id: String,
    val nameMember: String,
    val phone: String?,
    val statusPayment: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<DataListMember> {
        override fun createFromParcel(parcel: Parcel): DataListMember {
            return DataListMember(parcel)
        }

        override fun newArray(size: Int): Array<DataListMember?> {
            return arrayOfNulls(size)
        }
    }
}