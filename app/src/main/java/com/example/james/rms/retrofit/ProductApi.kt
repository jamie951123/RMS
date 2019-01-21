package com.example.james.rms.retrofit

import com.example.james.rms.core.model.ProductModel
import com.example.james.rms.core.model.ResponseMessage
import com.example.james.rms.core.serve_path.ProductServerPath
import retrofit2.Call
import retrofit2.http.POST

/**
 * A Dao for product's API
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 * @link {com.example.james.rms.core.dao.ProductDao}
 */
interface ProductApi {
//    @POST(ProductServerPath.serve_findAll())
    fun findAll(): Call<List<ProductModel>>

//    @POST(ProductServerPath.serve_findByProductId())
    fun findByProductId(json: String): Call<ProductModel>

    fun findByPartyId(json: String): Call<List<ProductModel>>

    fun save(json: String): Call<ProductModel>

    fun updateWeightIdNullByWeightIdAndPartyId(json: String): Call<Int?>

    fun updateQuantityIdNullByQuantityIdAndPartyId(json: String): Call<Int?>

    fun updateQuantityIdAndWeightIdNullByProductId(json: String): Call<Int?>

    fun deleteByProductId(json: String): Call<ResponseMessage>

    fun delete(product_json: String): Call<ResponseMessage>
}