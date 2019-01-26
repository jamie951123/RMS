package com.example.james.rms.network

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.james.rms.App
import com.example.james.rms.R
import com.example.james.rms.common.AsyncMessage
import com.example.james.rms.common.CommonFactory
import com.example.james.rms.common.util.ObjectUtil
import com.example.james.rms.core.model.NetworkModel
import com.example.james.rms.retrofit.ProductApi
import com.facebook.stetho.inspector.network.RequestBodyHelper
import de.keyboardsurfer.android.widget.crouton.Crouton
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by steve_000 on 24/1/2019.
 * for project RMS
 * package name com.example.james.rms.network
 */
class ProductImagePostAsync(val file: File) : AsyncTask<String, Int, String>() {

    var productApi:ProductApi = RetrofitUtil.getDefaultRetrofit().create(ProductApi::class.java)



    override fun doInBackground(vararg params: String): String {
        val result = ""

        try {
            val part = MultipartBody.Part.create(RequestBody.create(MediaType.parse("jpeg"), file))
//            val al =
//            val call = productApi.uploadProductImage("apple", part)
//            val response = call.execute()
//            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }




}