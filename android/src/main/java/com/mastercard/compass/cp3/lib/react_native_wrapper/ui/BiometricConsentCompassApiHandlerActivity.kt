package com.mastercard.compass.cp3.lib.react_native_wrapper.ui

import com.mastercard.compass.base.ConsentValue
import com.mastercard.compass.model.consent.Consent
import com.mastercard.compass.model.consent.ConsentResponse
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key

class BiometricConsentCompassApiHandlerActivity: CompassApiHandlerActivity<ConsentResponse>() {
    override suspend fun callCompassApi() {
      val programGUID: String = intent.getStringExtra(Key.PROGRAM_GUID)!!
      val consumerConsentValue = intent.getBooleanExtra((Key.CONSUMER_CONSENT_VALUE), false)

      val consent: Consent = if(consumerConsentValue){
        Consent(ConsentValue.ACCEPT, programGUID)
      } else {
        Consent(ConsentValue.DECLINE, programGUID)
      }

      val response = compassKernelServiceInstance.saveBiometricConsent(consent)

      getNonIntentCompassApiResults(response)
    }
}
