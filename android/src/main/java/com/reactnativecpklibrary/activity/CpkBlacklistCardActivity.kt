package com.reactnativecpklibrary.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.mastercard.compass.base.Constants
import com.mastercard.compass.model.card.BlacklistCardRequest
import com.reactnativecpklibrary.model.BlackListResponse
import com.reactnativecpklibrary.model.ErrorResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkBlacklistCardActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var reliantAppGuid: String
  private lateinit var programGuid: String
  private lateinit var consumerDeviceId: String
  private lateinit var rId: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()
    consumerDeviceId = intent.getStringExtra("consumerDeviceId").toString()
    rId = intent.getStringExtra("rId").toString()

    val request = BlacklistCardRequest(
      programGUID = programGuid,
      rId = rId,
      reliantAppGUID = reliantAppGuid,
      consumerDeviceId = consumerDeviceId
    )
    val blacklistIntent = compassKernelServiceInstance?.getBlockCardActivityIntent(request)
    val blacklistCardStartForResult =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
          Activity.RESULT_OK -> {
            var d = Intent()
            d.putExtra("status", "success")
            val blackListResponse = BlackListResponse()
            blackListResponse.status = "success"
            d.putExtra("data", blackListResponse);
            d.putExtra("message", "The card was successfully blacklisted. You can write to the new card")
            setResult(RESULT_OK, d)
            finish()
          }
          else -> {
            var d = Intent()
            d.putExtra("status", "error")
            val code = result.data?.extras?.getInt(Constants.EXTRA_ERROR_CODE) ?: 0
            val message =
              result.data?.extras?.getString(Constants.EXTRA_ERROR_MESSAGE) ?: ""
            val errorResponse = ErrorResponse()
            errorResponse.errorCode = code
            errorResponse.errorMessage = message
            d.putExtra("data", errorResponse);
            d.putExtra("message", message)
          }
        }
      }

    blacklistCardStartForResult.launch(blacklistIntent)
  }
}
