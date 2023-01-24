package com.reactnativecpklibrary.ui

import com.mastercard.compass.base.Constants
import com.reactnativecpklibrary.util.Key.PROGRAM_GUID
import com.reactnativecpklibrary.util.Key.RID
import com.reactnativecpklibrary.util.Key.OVERWRITE_CARD

class WriteProfileCompassApiHandlerActivity : CompassApiHandlerActivity<String>() {

    override suspend fun callCompassApi() {
        val programGuid: String = intent.getStringExtra(PROGRAM_GUID)!!
        val rId: String = intent.getStringExtra(RID)!!
        val overwriteCard: Boolean = intent.getBooleanExtra(OVERWRITE_CARD, false)

        val intent = compassKernelServiceInstance.getWriteProfileActivityIntent(
            programGuid, rId
        )

        intent?.putExtra(Constants.EXTRA_OVERWRITE_CARD, overwriteCard)
        compassApiActivityResult.launch(intent)
    }
}
