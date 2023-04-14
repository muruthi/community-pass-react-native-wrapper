package com.mastercard.compass.cp3.lib.react_native_wrapper.route

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.mastercard.compass.cp3.lib.react_native_wrapper.R
import com.mastercard.compass.cp3.lib.react_native_wrapper.ui.GetUserVerificationCompassApiHandlerActivity
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.ErrorCode
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key


class GetUserVerificationAPIRoute(private val context: ReactApplicationContext, private val currentActivity: Activity?) {
  companion object {
    val REQUEST_CODE_RANGE = 900 until 1000

    const val GET_USER_VERIFICATION_REQUEST_CODE = 900
    private const val TAG = "GetUserVerificationAPIRoute"
  }

  fun startGetUserVerificationIntent(getUserVerificationParams: ReadableMap){

    val programGUID: String = getUserVerificationParams.getString("programGUID")!!
    val reliantGUID: String = getUserVerificationParams.getString("reliantGUID")!!

    val intent = Intent(context, GetUserVerificationCompassApiHandlerActivity::class.java).apply {
      putExtra(Key.PROGRAM_GUID, programGUID)
      putExtra(Key.RELIANT_APP_GUID, reliantGUID)
    }

    currentActivity?.startActivityForResult(intent, GET_USER_VERIFICATION_REQUEST_CODE)
  }

  fun handleGetUserVerificationIntentResponse(
    resultCode: Int,
    data: Intent?,
    promise: Promise
  ) {
    when (resultCode) {
      Activity.RESULT_OK -> {
        val resultMap = Arguments.createMap()
        val response = data?.extras?.get(Key.DATA) as String
        resultMap.apply {
          putString("bioToken", response)
        }
        promise.resolve(resultMap);
      }
      Activity.RESULT_CANCELED -> {
        val code = data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString()
        val message = data?.getStringExtra(Key.ERROR_MESSAGE) ?: context.getString(R.string.error_unknown)
        Log.e(TAG, "Error $code Message $message")
        promise.reject(code, Throwable(message))
      }
    }
  }
}
