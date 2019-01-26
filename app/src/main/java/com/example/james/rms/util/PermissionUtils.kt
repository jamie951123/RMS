package com.example.james.rms.util

import android.preference.PreferenceManager
import android.app.Activity
import android.content.Intent
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.Fragment


/**
 * Created by steve_000 on 24/1/2019.
 * for project RMS
 * package name com.example.james.rms.util
 */
object PermissionUtils {
    @JvmStatic
    fun useRunTimePermissions(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }
    @JvmStatic
    fun hasPermission(activity: Activity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else true
    }
    @JvmStatic
    fun requestPermissions(activity: Activity, permission: Array<String>, requestCode: Int) {
        if (useRunTimePermissions()) {
            activity.requestPermissions(permission, requestCode)
        }
    }
    @JvmStatic
    fun requestPermissions(fragment: Fragment, permission: Array<String>, requestCode: Int) {
        if (useRunTimePermissions()) {
            fragment.requestPermissions(permission, requestCode)
        }
    }
    @JvmStatic
    fun shouldShowRational(activity: Activity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            activity.shouldShowRequestPermissionRationale(permission)
        } else false
    }
    @JvmStatic
    fun shouldAskForPermission(activity: Activity, permission: String): Boolean {
        return if (useRunTimePermissions()) {
            !hasPermission(activity, permission) && (!hasAskedForPermission(activity, permission) || shouldShowRational(activity, permission))
        } else false
    }
    @JvmStatic
    fun goToAppSettings(activity: Activity) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", activity.packageName, null))
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }
    @JvmStatic
    fun hasAskedForPermission(activity: Activity, permission: String): Boolean {
        return PreferenceManager
                .getDefaultSharedPreferences(activity)
                .getBoolean(permission, false)
    }
    @JvmStatic
    fun markedPermissionAsAsked(activity: Activity, permission: String) {
        PreferenceManager
                .getDefaultSharedPreferences(activity)
                .edit()
                .putBoolean(permission, true)
                .apply()
    }
}