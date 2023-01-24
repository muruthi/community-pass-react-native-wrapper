package com.reactnativecpklibrary.ui

import com.mastercard.compass.base.ConsentValue
import com.mastercard.compass.model.consent.Consent
import com.reactnativecpklibrary.util.Key

class BiometricConsentCompassApiHandlerActivity: CompassApiHandlerActivity<String>() {
    override suspend fun callCompassApi() {
        val programGuid: String = intent.getStringExtra(Key.PROGRAM_GUID)!!

        val consent = Consent(ConsentValue.ACCEPT, programGuid)
        val response = compassKernelServiceInstance.saveBiometricConsent(consent)
        val consentId = response.consentId

        getNonIntentCompassApiResults(consentId)
    }
}
