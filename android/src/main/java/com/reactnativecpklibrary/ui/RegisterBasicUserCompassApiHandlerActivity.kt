package com.reactnativecpklibrary.ui

import com.mastercard.compass.model.biometrictoken.FormFactor
import com.mastercard.compass.model.passcode.RegisterBasicUserRequestV2
import com.reactnativecpklibrary.util.Key

class RegisterBasicUserCompassApiHandlerActivity: CompassApiHandlerActivity<String>() {
    override suspend fun callCompassApi() {
        val programGuid: String = intent.getStringExtra(Key.PROGRAM_GUID)!!

        val intent = compassKernelServiceInstance.getRegisterBasicUserActivityIntent(
            RegisterBasicUserRequestV2(programGuid, FormFactor.NONE)
        )

        compassApiActivityResult.launch(intent)
    }
}
