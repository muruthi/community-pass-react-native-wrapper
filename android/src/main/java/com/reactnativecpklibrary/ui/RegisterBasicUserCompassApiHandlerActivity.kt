package com.reactnativecpklibrary.ui

import android.util.Log
import com.mastercard.compass.model.biometrictoken.FormFactor
import com.mastercard.compass.model.passcode.RegisterBasicUserRequest
import com.reactnativecpklibrary.util.Key

class RegisterBasicUserCompassApiHandlerActivity: CompassApiHandlerActivity<String>() {
    override suspend fun callCompassApi() {
        val programGUID: String = intent.getStringExtra(Key.PROGRAM_GUID)!!

        val intent = compassKernelServiceInstance.getRegisterBasicUserActivityIntent(
          RegisterBasicUserRequest(programGUID, FormFactor.CARD)
        )

        if(intent != null) {
          compassApiActivityResult.launch(intent)
        } else {
          getNonIntentCompassApiResults(null)
        }
    }
}
