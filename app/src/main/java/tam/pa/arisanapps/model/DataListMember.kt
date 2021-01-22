package tam.pa.arisanapps.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataListMember(
    val id: Int,
    val idGroup: Int,
    val nameMember: String,
    val phone: String?,
    val statusPayment: String,
    val win: String): Parcelable {
}
