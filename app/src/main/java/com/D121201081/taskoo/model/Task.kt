package com.D121201081.taskoo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nama_tugas:String,
    val deskripsi_tugas:String,
    val kategori_tugas:String,
    val status:String
):Parcelable
