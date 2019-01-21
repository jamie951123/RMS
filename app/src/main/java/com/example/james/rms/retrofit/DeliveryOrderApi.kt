package com.example.james.rms.retrofit

import com.example.james.rms.core.model.DeliveryOrderModel
import com.example.james.rms.core.model.ResponseMessage

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface DeliveryOrderApi {
    //    Find
    fun findAll(): List<DeliveryOrderModel>

    fun findByPartyIdAndStatus(deliverySearchObject: String): List<DeliveryOrderModel>
    fun findByOrderIdAndStatus(deliverySearchObject: String): List<DeliveryOrderModel>

    //    Save
    fun save(deliveryOrderModel: String): DeliveryOrderModel

    fun saveOrderAndItem(deliveryOrderModel: String): DeliveryOrderModel
    //    Delete
    fun delete(DeliveryOrderModel: String): ResponseMessage
}