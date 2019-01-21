package com.example.james.rms.retrofit

import com.example.james.rms.core.model.DeliveryItemModel
import com.example.james.rms.core.model.ResponseMessage
import retrofit2.Call

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface DeliveryItemApi {
    //    Find
    fun findAll(): Call<List<DeliveryItemModel>>

    fun findByPartyIdAndStatus(deliveryItemSearch_json: String): Call<List<DeliveryItemModel>>
    fun findByDeliveryItemIdAndStatus(): Call<List<DeliveryItemModel>>

    //    Save
    fun save(deliveryItemModel: String): Call<DeliveryItemModel>

    fun saves(deliveryItemModels: String): Call<List<DeliveryItemModel>>

    //    Delete
    fun delete(deliveryItemModel: String): Call<ResponseMessage>
}