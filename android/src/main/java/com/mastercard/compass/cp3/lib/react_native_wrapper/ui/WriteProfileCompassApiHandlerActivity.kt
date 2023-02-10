package com.mastercard.compass.cp3.lib.react_native_wrapper.ui

import com.mastercard.compass.base.Constants
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key.PROGRAM_GUID
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key.RID
import com.mastercard.compass.cp3.lib.react_native_wrapper.util.Key.OVERWRITE_CARD

class WriteProfileCompassApiHandlerActivity : CompassApiHandlerActivity<String>() {

    override suspend fun callCompassApi() {
        val programGUID: String = intent.getStringExtra(PROGRAM_GUID)!!
        val rID: String = intent.getStringExtra(RID)!!
        val overwriteCard: Boolean = intent.getBooleanExtra(OVERWRITE_CARD, false)

        val intent = compassKernelServiceInstance.getWriteProfileActivityIntent(
            programGUID, rID
        )

        intent?.putExtra(Constants.EXTRA_OVERWRITE_CARD, overwriteCard)
        compassApiActivityResult.launch(intent)
    }
}
