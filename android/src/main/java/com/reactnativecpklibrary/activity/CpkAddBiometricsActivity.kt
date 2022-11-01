package com.reactnativecpklibrary.activity

import android.os.Bundle
import com.tiv.mastercard.cpkservices.CompassKernelUIController

class CpkAddBiometricsActivity : CompassKernelUIController.CompassKernelActivity() {

  private lateinit var reliantAppGuid : String
  private lateinit var programGuid : String
  private lateinit var passCode : String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    reliantAppGuid = intent.getStringExtra("reliantAppGuid").toString()
    programGuid = intent.getStringExtra("programGuid").toString()
    passCode = intent.getStringExtra("passCode").toString()
  }


}
