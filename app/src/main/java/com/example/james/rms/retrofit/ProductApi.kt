package com.example.james.rms.retrofit

import com.example.james.rms.core.model.ProductModel
import com.example.james.rms.core.model.ResponseMessage
import com.example.james.rms.core.serve_path.ProductServerPath
import com.example.james.rms.network.ServeProfile
import retrofit2.Call
import retrofit2.http.POST
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Query


/**
 * A Dao for product's API
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 * @link {com.example.james.rms.core.dao.ProductDao}
 */
interface ProductApi {
    @POST(ServeProfile.Product.product_findAll)
    fun findAll(): Call<List<ProductModel>>

    @POST(ServeProfile.Product.product_findByProductId)
    fun findByProductId(json: String): Call<ProductModel>
    @POST(ServeProfile.Product.product_findByPartyId)
    fun findByPartyId(json: String): Call<List<ProductModel>>
    @POST(ServeProfile.Product.product_save)
    fun save(json: String): Call<ProductModel>

    @Multipart
    @POST(ServeProfile.Product.product_uploadImage)
    @Deprecated("use uploadProductImage")
    fun uploadProductImageV2(@Part("productName") productName:String, @Part("file") filePart: RequestBody): Call<String>
    @Multipart
    @POST(ServeProfile.Product.product_uploadImage)
    fun uploadProductImage(@Part() productName:MultipartBody.Part, @Part() filePart: MultipartBody.Part): Call<Boolean>

    @POST(ServeProfile.Product.product_updateWeightIdNullByWeightIdAndPartyId)
    fun updateWeightIdNullByWeightIdAndPartyId(json: String): Call<Int?>
    @POST(ServeProfile.Product.product_updateQuantityIdNullByQuantityIdAndPartyId)
    fun updateQuantityIdNullByQuantityIdAndPartyId(json: String): Call<Int?>
    @POST(ServeProfile.Product.product_updateQuantityIdAndWeightIdNullByProductId)
    fun updateQuantityIdAndWeightIdNullByProductId(json: String): Call<Int?>
    @POST(ServeProfile.Product.product_deleteByProductId)
    fun deleteByProductId(json: String): Call<ResponseMessage>
    @POST(ServeProfile.Product.product_delete)
    fun delete(product_json: String): Call<ResponseMessage>
}