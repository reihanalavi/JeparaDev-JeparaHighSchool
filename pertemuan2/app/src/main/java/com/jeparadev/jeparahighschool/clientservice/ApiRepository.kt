package com.jeparadev.jeparahighschool.clientservice

import com.jeparadev.jeparahighschool.models.HighschoolResponses
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {

    @GET("api/index.php/Csekolah/detailSekolahGET?")
    fun getHighschools(
        @Query ("mst_kode_wilayah") regionCode: String?,
        @Query ("bentuk") schoolKind: String?
    ): Observable<HighschoolResponses>
}