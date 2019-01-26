package com.example.james.rms.ui.test_upload

import android.net.Uri
import mvp.BasePresenter
import mvp.BaseView

/**
 * Created by steve_000 on 25/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.test_upload
 */
interface TestUploadPhotoContract {


    interface Presenter:BasePresenter{

        fun uploadProductImage(productName:String,imageUri:Uri)

    }

    interface View :BaseView<Presenter>{
        fun onUploadComplete()
        fun onUploadError(t:Throwable)
    }
}