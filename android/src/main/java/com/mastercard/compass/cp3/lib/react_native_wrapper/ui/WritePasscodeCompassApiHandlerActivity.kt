package com.mastercard.compass.cp3.lib.react_native_wrapper.ui

import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key

class WritePasscodeCompassApiHandlerActivity: CompassApiHandlerActivity<String>() {
    override suspend fun callCompassApi() {
        val passcode = intent.getStringExtra(Key.PASSCODE)!!
        val programGUID: String = intent.getStringExtra(Key.PROGRAM_GUID)!!
        val rID: String? = intent.getStringExtra(Key.RID)

        val intent = compassKernelServiceInstance.getWritePasscodeActivityIntent(passcode, rID, programGUID)

        compassApiActivityResult.launch(intent)
    }
}
