package com.reactnativecpklibrary.route

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.reactnativecpklibrary.ui.RegisterBasicUserCompassApiHandlerActivity
import com.reactnativecpklibrary.util.ErrorCode
import com.reactnativecpklibrary.util.Key

class RegisterBasicUserAPIRoute(private val context: ReactApplicationContext, private val currentActivity: Activity?) {
    companion object {
        val REQUEST_CODE_RANGE = 400 until 500

        const val REGISTER_BASIC_USER_REQUEST_CODE = 400
    }

    fun startRegisterBasicUserIntent(
      reliantAppGuid: String,
      programGuid: String,
    ){
        val intent = Intent(context, RegisterBasicUserCompassApiHandlerActivity::class.java).apply {
            putExtra(Key.RELIANT_APP_GUID, reliantAppGuid)
            putExtra(Key.PROGRAM_GUID, programGuid)
        }

        currentActivity?.startActivityForResult(intent, REGISTER_BASIC_USER_REQUEST_CODE)
    }

    fun handleRegisterBasicUserIntentResponse(
        resultCode: Int,
        data: Intent?,
        promise: Promise
    ) {

      when (resultCode) {
        Activity.RESULT_OK -> {
          val resultMap = Arguments.createMap()
          resultMap.putString("rId", data?.extras?.get(Key.DATA).toString())
          promise.resolve(resultMap);
        }
        Activity.RESULT_CANCELED -> {
          val code = data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString()
          val message = data?.getStringExtra(Key.ERROR_MESSAGE)!!
          promise.reject(code, Throwable(message))
        }
      }
    }
}
