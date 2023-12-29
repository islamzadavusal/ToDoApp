package com.islamzada.todoapp.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Notes (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "note") val note: String) : Parcelable {
}