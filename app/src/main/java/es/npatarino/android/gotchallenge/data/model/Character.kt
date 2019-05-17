package es.npatarino.android.gotchallenge.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    val imageUrl: String,
    val description: String,
    val houseId: String,
    val houseImageUrl: String,
    val houseName: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeString(houseId)
        parcel.writeString(houseImageUrl)
        parcel.writeString(houseName)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character =
            Character(parcel)

        override fun newArray(size: Int): Array<Character?> = arrayOfNulls(size)
    }
}