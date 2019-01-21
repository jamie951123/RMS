package com.example.james.rms.retrofit

import com.example.james.rms.core.model.ReceivingOrderModel
import com.example.james.rms.core.model.ResponseMessage

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface ReceivingOrderApi {
    //Find
    fun findByPartyId(json: String): List<ReceivingOrderModel>

    fun findByPartyIdAndStatus(json: String): List<ReceivingOrderModel>

    fun findByOrderIdAndStatus(json: String): List<ReceivingOrderModel>
    //Save
    fun save(json: String): ReceivingOrderModel

    fun saveOrderAndItem(json: String): ReceivingOrderModel

    //Delete
    fun delete(json: String): ResponseMessage

}