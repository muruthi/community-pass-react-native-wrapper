package com.reactnativecpklibrary.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mastercard.compass.base.Constants
import com.mastercard.compass.model.biometrictoken.Modality
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkBioAuthenticationActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var reliantAppGuid : String
  private lateinit var programGuid : String

  private lateinit var verifyPasscodeIntent : Intent
  private lateinit var verifyUserStartForResult: ActivityResultLauncher<Intent>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()

   verifyUserStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {

          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""

          }
        }
      }

    connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
      when (isSuccess) {
        true -> {
          val requestJwt = helper.generateJWT(reliantAppGuid, programGuid, listOf(
            Modality.FACE,
            Modality.LEFT_PALM,
            Modality.RIGHT_PALM
          ))
          val verificationIntent = compassKernelServiceInstance?.getUserVerificationActivityIntent(requestJwt, reliantAppGuid)
        }
        false -> {

        }
      }
      }
  }
}
