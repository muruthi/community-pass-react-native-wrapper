package com.reactnativecpklibrary.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mastercard.compass.base.Constants
import com.mastercard.compass.model.biometrictoken.AuthenticationType
import com.mastercard.compass.model.biometrictoken.Modality
import com.mastercard.compass.model.card.RegistrationStatusData
import com.reactnativecpklibrary.model.PasscodeResponse
import com.reactnativecpklibrary.model.RegMethodResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkRegistrationMethodActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var registrationDataCheckIntent: Intent
  private lateinit var registrationDataCheckActivityForResult: ActivityResultLauncher<Intent>

  private lateinit var reliantAppGuid : String
  private lateinit var programGuid : String

  companion object {
    private const val TAG = "TIVMASTERCARD"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()
    Log.d("TIV", reliantAppGuid)
    Log.d("TIV", programGuid)

    registrationDataCheckActivityForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            val response = result.data?.extras?.get(Constants.EXTRA_DATA) as RegistrationStatusData
            when {
              !response.isRegisteredInProgram -> {
                var d = Intent()
                d.putExtra("success", true)
                val regMethodResponse = RegMethodResponse()
                regMethodResponse.status = "fail"
                regMethodResponse.method = "N/A"
                d.putExtra("data", regMethodResponse);
                d.putExtra("message", "User not registered in program. Register user first.")
                setResult(RESULT_OK, d)
                finish()
              }
              response.authMethods.authType.contains(AuthenticationType.BIO) -> {
                Log.d(TAG, "User has biometric authentication. Authenticating via biometrics")
                var d = Intent()
                d.putExtra("success", true)
                val regMethodResponse = RegMethodResponse()
                regMethodResponse.status = "success"
                regMethodResponse.method = "BIO"
                d.putExtra("data", regMethodResponse);
                d.putExtra("message", "User has Biometric Authentication. Proceed to authenticate via Biometrics")
                setResult(RESULT_OK, d)
                finish()
              }
              response.authMethods.authType.contains(AuthenticationType.PASSCODE) -> {
                Log.d(TAG, "User has passcode authentication. Authenticating via passcode")
                var d = Intent()
                d.putExtra("success", true)
                val regMethodResponse = RegMethodResponse()
                regMethodResponse.status = "success"
                regMethodResponse.method = "PASSCODE"
                d.putExtra("data", regMethodResponse);
                d.putExtra("message", "User has passcode authentication. Proceed to authenticate via passcode")
                setResult(RESULT_OK, d)
                finish()
              }
            }
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            Log.e(TAG, "Error calling Kernel API. Code: $code Message: $message")
            var d = Intent()
            d.putExtra("success", false)
            val regMethodResponse = RegMethodResponse()
            regMethodResponse.status = "fail"
            regMethodResponse.method = "N/A"
            d.putExtra("message", "$message")
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }

    if(hasActiveKernelConnection){
      registrationDataCheckIntent = compassKernelServiceInstance?.getRegistrationDataActivityIntent(programGuid, reliantAppGuid)!!
      registrationDataCheckActivityForResult.launch(registrationDataCheckIntent)
    } else {
      connect()
    }
  }


  private fun connect() {
    connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
      when (isSuccess) {
        true -> {
          registrationDataCheckIntent = compassKernelServiceInstance?.getRegistrationDataActivityIntent(programGuid, reliantAppGuid)!!
          registrationDataCheckActivityForResult.launch(registrationDataCheckIntent)
        }
        false -> {
          var d = Intent()
          d.putExtra("success", false)
          val regMethodResponse = RegMethodResponse()
          regMethodResponse.status = "fail"
          regMethodResponse.method = "N/A"
          d.putExtra("message", "$errorMessage")
          setResult(RESULT_OK, d)
          finish()
        }
      }
    }
  }


}
