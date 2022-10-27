package com.reactnativecpklibrary.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkAuthenticateActivity : CompassKernelUIController.CompassKernelActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TIVMC", intent.getStringExtra("reliantAppGuid").toString());
        if(hasActiveKernelConnection){
          var d = Intent()
          d.putExtra("success", true)
          d.putExtra("data", intent.extras?.getString("reliantAppGuid"));
          d.putExtra("message", "Connected")
          setResult(RESULT_OK, d)
          finish()
        } else {
          var d = Intent()
          d.putExtra("success", false)
          d.putExtra("data", intent.extras?.getString("reliantAppGuid"));
          d.putExtra("message", "Not connected")
          setResult(RESULT_OK, d)
          finish()
        }
    }
}
