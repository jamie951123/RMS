package com.example.james.rms.data.remote

import com.example.james.rms.data.TestDataSource
import com.example.james.rms.network.RetrofitUtil
import com.example.james.rms.retrofit.ProductApi
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

/**
 * Created by steve_000 on 25/1/2019.
 * for project RMS
 * package name com.example.james.rms.data.remote
 */
class TestRemoteDataSource :TestDataSource {
    private val productApi:ProductApi

    init {
        productApi=RetrofitUtil.getDefaultRetrofit().create(ProductApi::class.java)
    }

    override fun uploadProductImage(productId: Int, mineType: String, imageFile: File, callback: TestDataSource.Callback) {

//        val multipartBody = MultipartBody.Part.create(MediaType.parse(mineType),imageFile)
//        val call = productApi.uploadProductImage("eric ",multipartBody)



        val partName = MultipartBody.Part.createFormData("productId", productId.toString())
        val partImage = MultipartBody.Part.createFormData(
                "file","product.image",
                RequestBody.create(MediaType.parse("image/jpeg"),imageFile))
        val call = productApi.uploadProductImage(partName,partImage)
        call.enqueue(object :Callback<Boolean>{
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                callback.onError(t);
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                if (!response.isSuccessful) {
                    callback.onError(Throwable("response"))
                    return
                }

                callback.onSuccess()
            }
        })
    }
}