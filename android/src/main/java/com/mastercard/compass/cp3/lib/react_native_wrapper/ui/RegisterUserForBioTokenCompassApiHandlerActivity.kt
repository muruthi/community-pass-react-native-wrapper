package com.mastercard.compass.cp3.lib.react_native_wrapper.ui

import com.mastercard.compass.base.OperationMode
import com.mastercard.compass.model.biometrictoken.Modality
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key

class RegisterUserForBioTokenCompassApiHandlerActivity: CompassApiHandlerActivity<String>() {
    override suspend fun callCompassApi() {
        val reliantGUID: String = intent.getStringExtra(Key.RELIANT_APP_GUID)!!
        val programGUID: String = intent.getStringExtra(Key.PROGRAM_GUID)!!
        val consentID: String = intent.getStringExtra(Key.CONSENT_ID)!!

        val jwt = helper.generateBioTokenJWT(
            reliantGUID, programGUID, consentID, listOf(
                Modality.FACE,
                Modality.LEFT_PALM,
                Modality.RIGHT_PALM
            ))

        val intent = compassKernelServiceInstance.getRegisterUserForBioTokenActivityIntent(
            jwt,
            reliantGUID,
            OperationMode.BEST_AVAILABLE
        )

        compassApiActivityResult.launch(intent)
    }

}
