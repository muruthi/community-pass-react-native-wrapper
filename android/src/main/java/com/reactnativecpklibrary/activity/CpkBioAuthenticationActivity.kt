package com.reactnativecpklibrary.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mastercard.compass.base.Constants
import com.mastercard.compass.model.biometrictoken.AuthenticationType
import com.mastercard.compass.model.biometrictoken.Modality
import com.mastercard.compass.model.card.RegistrationStatusData
import com.reactnativecpklibrary.model.BioResponse
import com.reactnativecpklibrary.model.ErrorResponse
import com.reactnativecpklibrary.model.PasscodeResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkBioAuthenticationActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var reliantAppGuid : String
  private lateinit var programGuid : String

  private lateinit var verificationIntent : Intent
  private lateinit var registrationDataCheckIntent: Intent
  private lateinit var verifyUserStartForResult: ActivityResultLauncher<Intent>
  private lateinit var registrationDataCheckActivityForResult : ActivityResultLauncher<Intent>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()


    registrationDataCheckActivityForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            val response = result.data?.extras?.get(Constants.EXTRA_DATA) as RegistrationStatusData
            when {
              !response.isRegisteredInProgram -> {
                var d = Intent()
                d.putExtra("status", "error")
                val errorResponse = ErrorResponse()
                errorResponse.errorMessage = "User is not registered in program"
                d.putExtra("data", errorResponse);
                d.putExtra("message", "User is not registered in program")
                setResult(RESULT_OK, d)
                finish()
              }
              response.authMethods.authType.contains(AuthenticationType.BIO) -> {

                    verifyUserStartForResult.launch(verificationIntent)

                //TODO: Authenticate via Biometrics
              }
              response.authMethods.authType.contains(AuthenticationType.PASSCODE) -> {
                var d = Intent()
                d.putExtra("status", "error")
                val errorResponse = ErrorResponse()
                errorResponse.errorMessage = "This user has passcode authentication. You can proceed to authenticate them via biometrics"
                d.putExtra("data", errorResponse);
                d.putExtra("message", "This user has passcode authentication. You can proceed to authenticate them via biometrics")
                setResult(RESULT_OK, d)
                finish()
              }
            }
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            var d = Intent()
            d.putExtra("status", "error")
            val errorResponse = ErrorResponse()
            errorResponse.errorCode = code
            errorResponse.errorMessage = message
            d.putExtra("data", errorResponse);
            d.putExtra("message", message)
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }

   verifyUserStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            var d = Intent()
            d.putExtra("status", "success")
            val bioResponse = BioResponse()
            bioResponse.status = "success"
            d.putExtra("data", bioResponse);
            d.putExtra("message", "User authenticated successfully. Proceed to offer service.")
            setResult(RESULT_OK, d)
            finish()
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            var d = Intent()
            d.putExtra("status", "error")
            val errorResponse = ErrorResponse()
            errorResponse.errorCode = code
            errorResponse.errorMessage = message
            d.putExtra("data", errorResponse);
            d.putExtra("message", message)
            setResult(RESULT_OK, d)
            finish()

          }
        }
      }

    connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
      when (isSuccess) {
        true -> {
          val requestJwt = helper.generateJWT(reliantAppGuid, programGuid, listOf(
           //Modality.FACE,
            Modality.LEFT_PALM,
            Modality.RIGHT_PALM
          ))
          verificationIntent = compassKernelServiceInstance?.getUserVerificationActivityIntent(requestJwt, reliantAppGuid)!!
          registrationDataCheckIntent = compassKernelServiceInstance?.getRegistrationDataActivityIntent(programGuid, reliantAppGuid)!!

        }
        false -> {
          var d = Intent()
          d.putExtra("status", "error")
          val errorResponse = ErrorResponse()
          errorResponse.errorCode = errorCode!!
          errorResponse.errorMessage = errorMessage!!
          d.putExtra("data", errorResponse);
          d.putExtra("message", "$errorMessage")
          setResult(RESULT_OK, d)
          finish()
        }
      }
      }
  }
}
