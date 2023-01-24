package com.reactnativecpklibrary.route

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.reactnativecpklibrary.ui.RegisterBasicUserCompassApiHandlerActivity
import com.reactnativecpklibrary.util.ErrorCode
import com.reactnativecpklibrary.util.Key

class RegisterBasicUserAPIRoute(private val activity: Activity) {
    companion object {
        val REQUEST_CODE_RANGE = 400 until 500

        const val REGISTER_BASIC_USER_REQUEST_CODE = 400
    }

    fun startRegisterBasicUserIntent(
      reliantAppGuid: String,
      programGuid: String,
      consentId: String,
      modalities: Array<String>,
    ){
        val intent = Intent(activity, RegisterBasicUserCompassApiHandlerActivity::class.java).apply {
            putExtra(Key.RELIANT_APP_GUID, reliantAppGuid)
            putExtra(Key.PROGRAM_GUID, programGuid)
        }

        activity.startActivityForResult(intent, REGISTER_BASIC_USER_REQUEST_CODE)
    }

    fun handleRegisterBasicUserIntentResponse(
        resultCode: Int,
        data: Intent?,
        promise: Promise
    ) {
      val resultMap = Arguments.createMap()

      when (resultCode) {
        Activity.RESULT_OK -> {
          resultMap.putString("data", data?.extras?.get(Key.DATA).toString())
          promise.resolve(resultMap);
        }
        Activity.RESULT_CANCELED -> {
          resultMap.putString("code", data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString())
          resultMap.putString("message", data?.getStringExtra(Key.ERROR_MESSAGE)!!)
          promise.resolve(resultMap)
        }
      }
    }
}
