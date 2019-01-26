package com.example.james.rms.data.repo

import com.example.james.rms.data.TestDataSource
import java.io.File

/**
 * Created by steve_000 on 25/1/2019.
 * for project RMS
 * package name com.example.james.rms.data.repo
 */
class TestRepository(private val remoteDataSource:TestDataSource) :TestDataSource{


    override fun uploadProductImage(productId: Int, mineType: String, imageFile: File, callback: TestDataSource.Callback) {
        remoteDataSource.uploadProductImage(productId, mineType, imageFile, callback)
    }
}