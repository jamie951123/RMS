package com.example.james.rms.retrofit

import com.example.james.rms.core.model.QuantityProfileModel
import com.example.james.rms.core.model.ResponseMessage

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface QuantityProfileApi {
    fun findAll(): List<QuantityProfileModel>
    fun findByPartyId(partyId: String): List<QuantityProfileModel>
    fun delete(quantityProfile: String): ResponseMessage
    fun save(json: String): QuantityProfileModel
}