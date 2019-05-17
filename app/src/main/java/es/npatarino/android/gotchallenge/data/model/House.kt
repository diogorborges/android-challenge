package es.npatarino.android.gotchallenge.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "houses")
data class House(
    @PrimaryKey @ColumnInfo(name = "houseId") val houseId: String,
    val houseImageUrl: String,
    val houseName: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(houseId)
        parcel.writeString(houseImageUrl)
        parcel.writeString(houseName)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<House> {
        override fun createFromParcel(parcel: Parcel): House = House(parcel)

        override fun newArray(size: Int): Array<House?> = arrayOfNulls(size)
    }
}