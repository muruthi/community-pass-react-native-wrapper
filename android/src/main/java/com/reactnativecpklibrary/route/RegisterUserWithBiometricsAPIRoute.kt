package com.reactnativecpklibrary.route

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.mastercard.compass.jwt.RegisterUserForBioTokenResponse
import com.reactnativecpklibrary.CompassKernelUIController
import com.reactnativecpklibrary.ui.RegisterUserForBioTokenCompassApiHandlerActivity
import com.reactnativecpklibrary.util.ErrorCode
import com.reactnativecpklibrary.util.Key

class RegisterUserWithBiometricsAPIRoute(
  private val context: ReactApplicationContext,
  private val currentActivity: Activity?,
  private val helperObject: CompassKernelUIController.CompassHelper
) {
    companion object {
        val REQUEST_CODE_RANGE = 300 until 400
        const val TAG = "REGISTER_USER_WITH_BIOMETRICS"
        const val REGISTER_BIOMETRICS_REQUEST_CODE = 300
    }

    fun startRegisterUserWithBiometricsIntent(reliantAppGuid: String, programGuid: String, consentId: String){
        val intent = Intent(context, RegisterUserForBioTokenCompassApiHandlerActivity::class.java).apply {
            putExtra(Key.RELIANT_APP_GUID, reliantAppGuid)
            putExtra(Key.PROGRAM_GUID, programGuid)
            putExtra(Key.CONSENT_ID, consentId)
        }

      currentActivity?.startActivityForResult(intent, REGISTER_BIOMETRICS_REQUEST_CODE)
    }

    fun handleRegisterUserWithBiometricsIntentResponse(
        resultCode: Int,
        data: Intent?,
        promise: Promise
    ) {
      val resultMap = Arguments.createMap()

      when (resultCode) {
            Activity.RESULT_OK -> {
              val jwt = data?.extras?.get(Key.DATA).toString()
              Log.d(TAG, jwt)
              val response: RegisterUserForBioTokenResponse = helperObject.parseBioTokenJWT(jwt)
              val rId =  response.rId;
              Log.d(TAG, rId)

              resultMap.putString("data", rId)
              promise.resolve(resultMap);
            }
            Activity.RESULT_CANCELED -> {
              resultMap.putString("code", data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString())
              Log.d(TAG, "data?.getStringExtra(Key.ERROR_MESSAGE)!!")
              resultMap.putString("message", "data?.getStringExtra(Key.ERROR_MESSAGE)!!")
              promise.resolve(resultMap)
            }
        }
    }
}
