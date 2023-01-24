package com.reactnativecpklibrary.route

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.reactnativecpklibrary.ui.WriteProfileCompassApiHandlerActivity
import com.reactnativecpklibrary.util.ErrorCode
import com.reactnativecpklibrary.util.Key

class ConsumerDeviceAPIRoute(
    private val activity: Activity
) {


    companion object {
        val REQUEST_CODE_RANGE = 200 until 300

        const val WRITE_PROFILE_REQUEST_CODE = 200
    }

    fun startWriteProfileIntent(reliantAppGuid: String, programGuid: String, rId: String, overwriteCard: Boolean){
        val intent = Intent(activity, WriteProfileCompassApiHandlerActivity::class.java).apply {
            putExtra(Key.RELIANT_APP_GUID, reliantAppGuid)
            putExtra(Key.PROGRAM_GUID, programGuid)
            putExtra(Key.RID, rId)
            putExtra(Key.OVERWRITE_CARD, overwriteCard)
        }

        activity.startActivityForResult(intent, WRITE_PROFILE_REQUEST_CODE)
    }

    fun handleWriteProfileIntentResponse(
        resultCode: Int,
        data: Intent?,
        promise: Promise
    ) {
      val resultMap = Arguments.createMap()

      when (resultCode) {
        Activity.RESULT_OK -> {
          resultMap.putString("data", data?.extras?.get(Key.DATA).toString())
          promise.resolve(resultMap);
        }
        Activity.RESULT_CANCELED -> {
          resultMap.putString("code", data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString())
          resultMap.putString("message", data?.getStringExtra(Key.ERROR_MESSAGE)!!)
          promise.resolve(resultMap)
        }
      }
    }
}
