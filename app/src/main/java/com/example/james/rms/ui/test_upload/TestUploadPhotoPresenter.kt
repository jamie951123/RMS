package com.example.james.rms.ui.test_upload

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.example.james.rms.data.TestDataSource
import com.example.james.rms.data.repo.TestRepository
import java.io.File

/**
 * Created by steve_000 on 25/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.test_upload
 */
class TestUploadPhotoPresenter(
        private var c: Context,
        private val view: TestUploadPhotoContract.View,
        private val repo: TestRepository) : TestUploadPhotoContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun uploadProductImage(productName: String, imageUri: Uri) {

        val mineType = "image/jpeg"

        val imageFile: File = File(imageUri.path)

        repo.uploadProductImage(1, mineType, imageFile, object : TestDataSource.Callback {
            override fun onSuccess() {
                view.onUploadComplete()
            }

            override fun onError(t: Throwable) {
                view.onUploadError(t)
            }
        })
    }
}