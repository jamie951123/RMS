package com.example.james.rms.retrofit

import com.example.james.rms.core.model.ReceivingItemModel
import com.example.james.rms.core.model.ResponseMessage

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface ReceivingItemApi {
    //Find
    fun findReceivingItemByPartyId(json: String): List<ReceivingItemModel>

    //Save
    fun saves(json: String): List<ReceivingItemModel>

    //Delete
    fun delete(json: String): ResponseMessage
}