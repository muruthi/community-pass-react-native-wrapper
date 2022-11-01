package com.reactnativecpklibrary.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mastercard.compass.base.Constants
import com.mastercard.compass.model.card.VerifyPasscodeRequest
import com.mastercard.compass.model.card.VerifyPasscodeResponse
import com.reactnativecpklibrary.model.BioResponse
import com.reactnativecpklibrary.model.PasscodeResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkPasscodeAuthenticationActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var reliantAppGuid : String
  private lateinit var programGuid : String
  private lateinit var passCode : String

  private lateinit var verifyPasscodeIntent : Intent
  private lateinit var verifyPasscodeStartForResult: ActivityResultLauncher<Intent>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()
    passCode = intent.getStringExtra("passCode").toString()

    val verifyPasscodeRequest = VerifyPasscodeRequest(passCode, programGuid)

    verifyPasscodeStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            val response = result.data?.extras?.getParcelable<VerifyPasscodeResponse>(Constants.EXTRA_DATA)
            when(response?.status == true){
              true -> {
                var d = Intent()
                d.putExtra("status", "success")
                val passcodeResponse = PasscodeResponse()
                passcodeResponse.status = "success"
                passcodeResponse.rId = response?.rid
                d.putExtra("data", passcodeResponse);
                d.putExtra("message", "User authenticated successfully. Proceed to offer service.")
                setResult(RESULT_OK, d)
                finish()
              }
              false -> {
                var d = Intent()
                d.putExtra("status", "error")
                val passcodeResponse = PasscodeResponse()
                passcodeResponse.status = "fail"
                passcodeResponse.rId = response?.rid
                d.putExtra("data", passcodeResponse);
                d.putExtra("message", "Authentication failed. Passcode retry attempts remaining ${response?.counter?.retryCount}")
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
            val passcodeResponse = PasscodeResponse()
            passcodeResponse.status = "fail"
            d.putExtra("data", passcodeResponse);
            d.putExtra("message", "$message")
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }

    connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
      when (isSuccess) {
        true -> {
          verifyPasscodeIntent = compassKernelServiceInstance?.getVerifyPasscodeActivityIntent(verifyPasscodeRequest)!!
          verifyPasscodeStartForResult.launch(verifyPasscodeIntent)
        }

        false -> {
          var d = Intent()
          d.putExtra("success", false)
          val passcodeResponse = PasscodeResponse()
          passcodeResponse.status = "fail"
          d.putExtra("data", passcodeResponse);
          d.putExtra("message", "$errorCode: $errorMessage")
          setResult(RESULT_OK, d)
          finish()
        }
        }
      }
  }
}
