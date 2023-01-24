package com.reactnativecpklibrary.route

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.reactnativecpklibrary.ui.BiometricConsentCompassApiHandlerActivity
import com.reactnativecpklibrary.util.ErrorCode
import com.reactnativecpklibrary.util.Key

class BiometricConsentAPIRoute(private val activity: Activity) {
    companion object {
        val REQUEST_CODE_RANGE = 600 until 700

        const val BIOMETRIC_CONSENT_REQUEST_CODE = 600
    }

    fun startBiometricConsentIntent(reliantAppGuid: String, programGuid: String){
        val intent = Intent(activity, BiometricConsentCompassApiHandlerActivity::class.java).apply {
            putExtra(Key.PROGRAM_GUID, programGuid)
            putExtra(Key.RELIANT_APP_GUID, reliantAppGuid )
        }

        activity.startActivityForResult(intent, BIOMETRIC_CONSENT_REQUEST_CODE)
    }

    fun handleBiometricConsentIntentResponse(
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
