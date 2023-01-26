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

      when (resultCode) {
            Activity.RESULT_OK -> {
              val resultMap = Arguments.createMap()
              val jwt = data?.extras?.get(Key.DATA).toString()
              val response: RegisterUserForBioTokenResponse = helperObject.parseBioTokenJWT(jwt)

              resultMap.putString("rId", response.rId)
              resultMap.putString("enrolmentStatus", response.enrolmentStatus.toString())
              resultMap.putString("bioToken", response.bioToken)
              resultMap.putString("programGUID", response.programGUID)
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
