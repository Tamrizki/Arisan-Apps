package tam.pa.arisanapps.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataListGroup(
    val id: Int,
    val nameGroup: String,
    val type: String,
    val priceMember: String,
    val totalMember: Int,
    val img: String? ): Parcelable{
}