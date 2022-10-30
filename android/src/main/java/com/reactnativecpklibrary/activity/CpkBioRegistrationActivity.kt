package com.reactnativecpklibrary.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.facebook.react.bridge.Arguments
import com.mastercard.compass.base.ConsentValue
import com.mastercard.compass.base.Constants
import com.mastercard.compass.base.EnrolmentStatus
import com.mastercard.compass.base.OperationMode
import com.mastercard.compass.jwt.RegisterUserForBioTokenResponse
import com.mastercard.compass.model.biometrictoken.Modality
import com.mastercard.compass.model.consent.Consent
import com.reactnativecpklibrary.model.BioResponse
import com.reactnativecpklibrary.model.RegResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkBioRegistrationActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var registerUserForBioIntent : Intent
  private lateinit var bioCaptureStartForResult: ActivityResultLauncher<Intent>
  private lateinit var cardWriteStartForResult: ActivityResultLauncher<Intent>
  private lateinit var cardWriteIntent: Intent

  private lateinit var reliantAppGuid: String
  private lateinit var programGuid: String
  private var overWrite: Boolean = false
  private lateinit var rId : String

  companion object {
    private const val TAG = "TIVMASTERCARD"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()

    cardWriteStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            val consumerDeviceId = result.data?.getStringExtra(Constants.EXTRA_DATA)!!
            var d = Intent()
            d.putExtra("success", true)
            val regResponse = RegResponse()
            regResponse.status = "success"
            regResponse.rId = rId
            regResponse.devicdeId = consumerDeviceId
            d.putExtra("data", regResponse);
            d.putExtra("message", "Successfully Issued Card via Biometrics")
            setResult(RESULT_OK, d)
            finish()
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            var d = Intent()
            d.putExtra("success", true)
            val regResponse = RegResponse()
            regResponse.status = "fail"
            regResponse.rId = rId
            d.putExtra("data", regResponse);
            d.putExtra("message", "$code: $message")
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }


    bioCaptureStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            val jwt = result.data?.getStringExtra(Constants.EXTRA_DATA)!!
            val response: RegisterUserForBioTokenResponse = helper.parseBioTokenJWT(jwt)
            when(response.enrolmentStatus){
              EnrolmentStatus.NEW -> {
                rId = response.rId
                cardWriteIntent = compassKernelServiceInstance?.getWriteProfileActivityIntent(
                  programGuid, rId)!!
                cardWriteIntent?.putExtra(Constants.EXTRA_OVERWRITE_CARD, true)
                cardWriteStartForResult.launch(cardWriteIntent)
              }
              EnrolmentStatus.EXISTING -> {
                rId = response.rId
                val bioResponse = BioResponse()
                bioResponse.status = "existing"
                bioResponse.rId = rId
                var d = Intent()
                d.putExtra("success", true)
                d.putExtra("data", bioResponse);
                d.putExtra("message", "Existing User")
                setResult(RESULT_OK, d)
                finish()
              }
              else -> {
                var d = Intent()
                d.putExtra("success", true)
                d.putExtra("data", "");
                d.putExtra("message", "Something unknown happened")
                setResult(RESULT_OK, d)
                finish()
              }
            }
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            var d = Intent()
            d.putExtra("success", false)
            d.putExtra("data", "");
            d.putExtra("message", "$code: $message")
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }

    connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
      when (isSuccess) {
        true -> {
          val consent = Consent(ConsentValue.ACCEPT, programGuid)
          val response = compassKernelServiceInstance.saveBiometricConsent(consent)
          val jwt = helper.generateBioTokenJWT(
            reliantAppGuid, programGuid, response.consentId, listOf(
            //Modality.FACE,
            Modality.LEFT_PALM,
            Modality.RIGHT_PALM
          ))
          registerUserForBioIntent = compassKernelServiceInstance?.getRegisterUserForBioTokenActivityIntent(jwt,
            reliantAppGuid, OperationMode.BEST_AVAILABLE)!!
          bioCaptureStartForResult.launch(registerUserForBioIntent)
        }
        false -> {
          var d = Intent()
          d.putExtra("success", false)
          d.putExtra("data", "");
          d.putExtra("message", "$errorMessage")
          setResult(RESULT_OK, d)
          finish()
        }
      }
    }
  }
}
