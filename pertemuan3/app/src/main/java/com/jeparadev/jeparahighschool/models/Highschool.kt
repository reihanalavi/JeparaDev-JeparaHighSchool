package com.jeparadev.jeparahighschool.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class HighschoolResponses (
    @SerializedName("data")
    val `data`: List<Highschool>?
)

@Entity
data class Highschool (
    @SerializedName("alamat_jalan")
    @ColumnInfo(name = "alamat_jalan") val alamatJalan: String?,

    @SerializedName("bentuk")
    @ColumnInfo(name = "bentuk") val bentuk: String?,

    @SerializedName("bujur")
    @ColumnInfo(name = "bujur") val bujur: String?,

    @SerializedName("id")
    @ColumnInfo(name = "id") val id: String?,

    @SerializedName("kabupaten_kota")
    @ColumnInfo(name = "kabupaten_kota") val kabupatenKota: String?,

    @SerializedName("kecamatan")
    @ColumnInfo(name = "kecamatan") val kecamatan: String?,

    @SerializedName("kode_kab_kota")
    @ColumnInfo(name = "kode_kabkota") val kodeKabKota: String?,

    @SerializedName("kode_kec")
    @ColumnInfo(name = "kode_kec") val kodeKec: String?,

    @SerializedName("kode_prop")
    @ColumnInfo(name = "kode_prop") val kodeProp: String?,

    @SerializedName("lintang")
    @ColumnInfo(name = "kode_lintang") val lintang: String?,

    @SerializedName("npsn")
    @ColumnInfo(name = "npsn") val npsn: String?,

    @SerializedName("propinsi")
    @ColumnInfo(name = "propinsi") val propinsi: String?,

    @SerializedName("sekolah")
    @ColumnInfo(name = "sekolah") val sekolah: String?,

    @SerializedName("status")
    @ColumnInfo(name = "status") val status: String?

) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

}