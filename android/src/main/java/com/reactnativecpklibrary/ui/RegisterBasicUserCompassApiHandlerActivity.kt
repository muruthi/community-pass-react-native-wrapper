package com.reactnativecpklibrary.ui

import com.mastercard.compass.model.biometrictoken.FormFactor
import com.mastercard.compass.model.passcode.RegisterBasicUserRequestV2
import com.reactnativecpklibrary.util.Key

class RegisterBasicUserCompassApiHandlerActivity: CompassApiHandlerActivity<String>() {
    override suspend fun callCompassApi() {
        val programGUID: String = intent.getStringExtra(Key.PROGRAM_GUID)!!

        val intent = compassKernelServiceInstance.getRegisterBasicUserActivityIntent(
          RegisterBasicUserRequestV2(programGUID, FormFactor.NONE)
        )

        if(intent != null) {
          compassApiActivityResult.launch(intent)
        } else {
          getNonIntentCompassApiResults(null)
        }
    }
}
