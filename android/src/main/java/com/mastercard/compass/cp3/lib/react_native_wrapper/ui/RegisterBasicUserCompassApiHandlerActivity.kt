package com.mastercard.compass.cp3.lib.react_native_wrapper.ui

import com.mastercard.compass.model.biometrictoken.FormFactor
import com.mastercard.compass.model.passcode.RegisterBasicUserRequestV2
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key

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
