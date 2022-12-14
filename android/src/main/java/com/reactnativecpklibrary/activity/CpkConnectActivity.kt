package com.reactnativecpklibrary.activity

import android.content.Intent
import android.os.Bundle
import com.reactnativecpklibrary.model.ConnectResponse
import com.reactnativecpklibrary.model.ErrorResponse
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkConnectActivity : CompassKernelUIController.CompassKernelActivity() {

    private lateinit var reliantAppGuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
      connectKernelService(reliantAppGuid) { isSuccess, errorCode, errorMessage ->
        when (isSuccess) {
          true -> {
            var d = Intent()
            d.putExtra("status", "success")
            val connectResponse = ConnectResponse(status = "success")
            d.putExtra("data", connectResponse);
            d.putExtra("message", "Connected")
            setResult(RESULT_OK, d)
            finish()
          }
          false -> {
            var d = Intent()
            d.putExtra("status", "error")
            d.putExtra("data", "");
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
