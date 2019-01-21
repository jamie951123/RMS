package com.example.james.rms.retrofit

import com.example.james.rms.core.model.ResponseMessage
import com.example.james.rms.core.model.WeightProfileModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface WeightProfileApi {
    @GET()
    fun findAll(): Call<List<WeightProfileModel>>
    @POST
    fun findByPartyId(partyId: String): Call<List<WeightProfileModel>>
    @POST
    fun delete(weightProfile: String): Call<ResponseMessage>
    @POST
    fun save(json: String): Call<WeightProfileModel>
}