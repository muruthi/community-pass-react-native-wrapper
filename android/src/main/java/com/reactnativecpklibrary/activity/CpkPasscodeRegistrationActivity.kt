package com.reactnativecpklibrary.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mastercard.compass.base.Constants
import com.mastercard.compass.model.biometrictoken.FormFactor
import com.mastercard.compass.model.passcode.RegisterBasicUserRequest
import com.reactnativecpklibrary.model.PasscodeResponse
import com.reactnativecpklibrary.model.RegResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkPasscodeRegistrationActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var reliantAppGuid : String
  private lateinit var programGuid : String
  private lateinit var passCode : String
  private lateinit var deviceId: String
  private lateinit var rId: String
  private lateinit var cardPassCodeIntent: Intent
  private lateinit var cardWriteIntent: Intent
  private lateinit var passCodeIntent: Intent
  private lateinit var cardPasscodeStartForResult: ActivityResultLauncher<Intent>
  private lateinit var cardWriteStartForResult: ActivityResultLauncher<Intent>

  private var overWrite : Boolean = true


  companion object {
    private const val TAG = "TIVMASTERCARD"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()
    passCode = intent.getStringExtra("passCode").toString();
    overWrite = intent.getBooleanExtra("overWrite", true)

    cardPasscodeStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          RESULT_OK -> {
            var d = Intent()
            d.putExtra("success", true)
            val regResponse = RegResponse()
            regResponse.status = "success"
            regResponse.rId = rId
            regResponse.devicdeId = deviceId
            d.putExtra("data", regResponse);
            d.putExtra("message", "Successfully Issued Card via Passcode")
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
            regResponse.devicdeId = deviceId
            d.putExtra("data", regResponse);
            d.putExtra("message", "$code: $message")
            setResult(RESULT_OK, d)
            finish()
            }
          }
      }

    cardWriteStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          RESULT_OK -> {
            val consumerDeviceId = result.data?.getStringExtra(Constants.EXTRA_DATA)!!
            deviceId = consumerDeviceId
            cardPassCodeIntent = compassKernelServiceInstance?.getWritePasscodeActivityIntent(
              passCode, //Should be 6 digits
              rId,
              programGuid
            )!!
            cardPasscodeStartForResult.launch(cardPassCodeIntent)
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            var d = Intent()
            d.putExtra("success", false)
            val regResponse = RegResponse()
            regResponse.status = "fail"
            regResponse.rId = rId
            regResponse.devicdeId = deviceId
            d.putExtra("data", regResponse);
            d.putExtra("message", "$code: $message")
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }

    val basicRegistrationStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          RESULT_OK -> {
            val rid = result.data?.getStringExtra(Constants.EXTRA_DATA)!!
            rId = rId
            cardWriteIntent = compassKernelServiceInstance?.getWriteProfileActivityIntent(
              programGuid, rId)!!
            cardWriteIntent?.putExtra(Constants.EXTRA_OVERWRITE_CARD, overWrite)
            cardWriteStartForResult.launch(cardWriteIntent)
          }
          else -> {
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message = result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            var d = Intent()
            val regResponse = RegResponse()
            regResponse.status = "fail"
            d.putExtra("data", regResponse);
            d.putExtra("message", "$code: $message")
            setResult(RESULT_OK, d)
            finish()
          }
        }
      }



    connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
      when (isSuccess) {
        true -> {
          passCodeIntent = compassKernelServiceInstance?.getRegisterBasicUserActivityIntent(
            RegisterBasicUserRequest(programGuid, FormFactor.NONE)
          )!!
          basicRegistrationStartForResult.launch(passCodeIntent)
        }

        false -> {
          var d = Intent()
          d.putExtra("success", false)
          val regResponse = RegResponse()
          regResponse.status = "fail"
          d.putExtra("data", regResponse);
          d.putExtra("message", "$errorCode: $errorMessage")
          setResult(RESULT_OK, d)
          finish()
        }
      }
    }

  }
}
