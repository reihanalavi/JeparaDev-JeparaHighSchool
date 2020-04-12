package com.jeparadev.jeparahighschool.models


import com.google.gson.annotations.SerializedName

data class HighschoolResponses (
    @SerializedName("data")
    val `data`: List<Highschool>?
)

data class Highschool (
    @SerializedName("alamat_jalan") val alamatJalan: String?,
    @SerializedName("bentuk") val bentuk: String?,
    @SerializedName("bujur") val bujur: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("kabupaten_kota") val kabupatenKota: String?,
    @SerializedName("kecamatan") val kecamatan: String?,
    @SerializedName("kode_kab_kota") val kodeKabKota: String?,
    @SerializedName("kode_kec") val kodeKec: String?,
    @SerializedName("kode_prop") val kodeProp: String?,
    @SerializedName("lintang") val lintang: String?,
    @SerializedName("npsn") val npsn: String?,
    @SerializedName("propinsi") val propinsi: String?,
    @SerializedName("sekolah") val sekolah: String?,
    @SerializedName("status") val status: String?
)