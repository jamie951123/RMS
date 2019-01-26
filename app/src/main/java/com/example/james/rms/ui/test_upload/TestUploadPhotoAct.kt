package com.example.james.rms.ui.test_upload

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.james.rms.Injection
import com.example.james.rms.R
import com.example.james.rms.constant.Constant
import com.example.james.rms.util.PermissionUtils
import com.example.james.rms.util.bind
import com.soundcloud.android.crop.Crop
import java.io.File
import java.io.IOException

/**
 * Created by steve_000 on 25/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.test_upload
 */
class TestUploadPhotoAct : AppCompatActivity(), TestUploadPhotoContract.View {


    private val iv_product by bind<ImageView>(R.id.iv_product)
    private val btn_upload by bind<Button>(R.id.btn_upload)


    var imageUri: Uri? = null

    val REQ_PERMISSION: Int = 552

    @JvmField
    var presenter: TestUploadPhotoContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_test_upload_photo)
        TestUploadPhotoPresenter(this,this,Injection.provideTestRepository())
        initView()
    }

    private fun initView() {
        btn_upload.setOnClickListener {
            if (imageUri != null) {
                presenter?.uploadProductImage("eric", imageUri!!)
            } else {
                Toast.makeText(this, "plz select imageFirst", Toast.LENGTH_SHORT).show()
            }
        }

        iv_product.setOnClickListener {
            PermissionUtils.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQ_PERMISSION)
        }

    }

    override fun onUploadComplete() {
        Toast.makeText(this, "upload complete", Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: TestUploadPhotoContract.Presenter) {
        this.presenter = presenter
    }

    override fun onUploadError(t: Throwable) {
        Toast.makeText(this, "upload fail !!!!@@", Toast.LENGTH_SHORT).show()
    }

    private fun triggerCropImage(sourceUri: Uri) {
        val folder = this.cacheDir
        try {
            val outputFile = File.createTempFile("product", "jpg", folder)
            imageUri = Uri.fromFile(outputFile)
            Crop.of(sourceUri, imageUri).asSquare().start(this)

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == Crop.REQUEST_PICK) {
            if (resultCode == Activity.RESULT_OK) {
                val pickUrl = data.data
                triggerCropImage(pickUrl)
            }
        }
        if (requestCode == Crop.REQUEST_CROP && resultCode == Activity.RESULT_OK) {
            Glide.with(this)
                    .load(imageUri)
                    .into(iv_product)
            presenter?.uploadProductImage("eric",imageUri!!)
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("AAAAAAAA", "Permission: " + permissions[0] + "was " + grantResults[0])
            //resume tasks needing this permission (pick image and crop)
            Crop.pickImage(this)
        }
    }
}