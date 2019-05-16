package es.npatarino.android.gotchallenge.data.model.house

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "houses")
data class House(
    @PrimaryKey @ColumnInfo(name = "houseId") val houseId: String,
    val houseImageUrl: String,
    val houseName: String
)
