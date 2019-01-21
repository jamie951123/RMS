package com.example.james.rms.retrofit

import com.example.james.rms.core.model.InventoryModel

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface InventoryApi {
    fun findByPartyIdAndStatus(json: String): List<InventoryModel>
    fun findByPartyIdAndStatusOrderByProductIdAsc(json: String): List<InventoryModel>
}