package com.mastercard.compass.cp3.lib.react_native_wrapper.route

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.mastercard.compass.cp3.lib.react_native_wrapper.ui.RegisterBasicUserCompassApiHandlerActivity
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.ErrorCode
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key
import timber.log.Timber

class RegisterBasicUserAPIRoute(private val context: ReactApplicationContext, private val currentActivity: Activity?) {
    companion object {
        val REQUEST_CODE_RANGE = 400 until 500

        const val REGISTER_BASIC_USER_REQUEST_CODE = 400
    }

    fun startRegisterBasicUserIntent(
      RegisterBasicUserParams: ReadableMap
    ){
      val reliantAppGUID: String = RegisterBasicUserParams.getString("reliantAppGUID")!!
      val programGUID: String = RegisterBasicUserParams.getString("programGUID")!!
      Timber.d("reliantAppGuid: {$reliantAppGUID}")
      Timber.d("programGuid: {$programGUID}")

      val intent = Intent(context, RegisterBasicUserCompassApiHandlerActivity::class.java).apply {
          putExtra(Key.RELIANT_APP_GUID, reliantAppGUID)
          putExtra(Key.PROGRAM_GUID, programGUID)
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
          Timber.d("rId: {$data?.extras?.get(Key.DATA).toString()}")
          promise.resolve(resultMap);
        }
        Activity.RESULT_CANCELED -> {
          val code = data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString()
          val message = data?.getStringExtra(Key.ERROR_MESSAGE)!!
          Timber.e("Error $code Message $message")
          promise.reject(code, Throwable(message))
        }
      }
    }
}
