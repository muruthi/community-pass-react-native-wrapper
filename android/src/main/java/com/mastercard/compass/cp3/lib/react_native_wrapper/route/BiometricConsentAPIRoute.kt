package com.mastercard.compass.cp3.lib.react_native_wrapper.route

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.mastercard.compass.model.consent.ConsentResponse
import com.mastercard.compass.cp3.lib.react_native_wrapper.R
import com.mastercard.compass.cp3.lib.react_native_wrapper.ui.BiometricConsentCompassApiHandlerActivity
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.ErrorCode
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key


class BiometricConsentAPIRoute(private val context: ReactApplicationContext, private val currentActivity: Activity?) {
    companion object {
        val REQUEST_CODE_RANGE = 600 until 700

        const val BIOMETRIC_CONSENT_REQUEST_CODE = 600
        private const val TAG = "BiometricConsentAPIRoute"
    }

    fun startBiometricConsentIntent(SaveBiometricConsentParams: ReadableMap){
      val reliantAppGUID: String = SaveBiometricConsentParams.getString("reliantAppGUID")!!
      val programGUID: String = SaveBiometricConsentParams.getString("programGUID")!!
      val consumerConsentValue: Boolean = SaveBiometricConsentParams.getBoolean("consumerConsentValue")

      val intent = Intent(context, BiometricConsentCompassApiHandlerActivity::class.java).apply {
          putExtra(Key.PROGRAM_GUID, programGUID)
          putExtra(Key.RELIANT_APP_GUID, reliantAppGUID )
          putExtra(Key.CONSUMER_CONSENT_VALUE, consumerConsentValue)
      }

        currentActivity?.startActivityForResult(intent, BIOMETRIC_CONSENT_REQUEST_CODE)
    }

    fun handleBiometricConsentIntentResponse(
        resultCode: Int,
        data: Intent?,
        promise: Promise
    ) {

      when (resultCode) {
        Activity.RESULT_OK -> {
          val resultMap = Arguments.createMap()
          val response: ConsentResponse = data?.extras?.get(Key.DATA) as ConsentResponse
          resultMap.putString("consentId", response.consentId)
          resultMap.putString("responseStatus", response.responseStatus.toString())
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
