package com.example.james.rms.core.serve_path

import com.example.james.rms.network.ServeProfile

/**
 * Created by jamie on 2017/4/17.
 */

object ProductServerPath {
    val serve: String
        get() = ServeProfile.serve

    fun serve_findAll(): String {
        val path = ServeProfile.product_findAll
        return serve + path
    }

    fun serve_findByPartyId(): String {
        val path = ServeProfile.product_findByPartyId
        return serve + path
    }

    fun serve_insertProduct(): String {
        val path = ServeProfile.product_save
        return serve + path
    }

    fun serve_updateWeightIdNullByWeightIdAndPartyId(): String {
        val path = ServeProfile.product_updateWeightIdNullByWeightIdAndPartyId
        return serve + path
    }

    fun serve_updateQuantityIdNullByQuantityIdAndPartyId(): String {
        val path = ServeProfile.product_updateQuantityIdNullByQuantityIdAndPartyId
        return serve + path
    }

    fun serve_findByProductId(): String {
        val path = ServeProfile.product_findByProductId
        return serve + path
    }

    fun serve_updateQuantityIdAndWeightIdNullByProductId(): String {
        val path = ServeProfile.product_updateQuantityIdAndWeightIdNullByProductId
        return serve + path
    }

    fun serve_deleteByProductId(): String {
        val path = ServeProfile.product_deleteByProductId
        return serve + path
    }

    fun serve_delete(): String {
        val path = ServeProfile.product_delete
        return serve + path
    }

}
