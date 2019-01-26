package com.example.james.rms.data

import java.io.File

/**
 * Created by steve_000 on 25/1/2019.
 * for project RMS
 * package name com.example.james.rms.data
 */
interface TestDataSource {

    fun uploadProductImage(productId:Int, mineType:String,imageFile: File, callback: Callback)

    interface Callback{

        fun onSuccess()

        fun onError(t:Throwable)
    }
}