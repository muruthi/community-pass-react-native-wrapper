package com.reactnativecpklibrary.route

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.reactnativecpklibrary.ui.WritePasscodeCompassApiHandlerActivity
import com.reactnativecpklibrary.util.ErrorCode
import com.reactnativecpklibrary.util.Key

class ConsumerDevicePasscodeAPIRoute(private val context: ReactApplicationContext, private val currentActivity: Activity?) {
    companion object {
        val REQUEST_CODE_RANGE = 500 until 600

        const val WRITE_PASSCODE_REQUEST_CODE = 500
    }

    fun startWritePasscodeIntent(reliantAppGuid: String, programGuid: String, rId: String, passcode: String){
        val intent = Intent(context, WritePasscodeCompassApiHandlerActivity::class.java).apply {
            putExtra(Key.RELIANT_APP_GUID, reliantAppGuid)
            putExtra(Key.PROGRAM_GUID, programGuid)
            putExtra(Key.RID, rId)
            putExtra(Key.PASSCODE, passcode)
        }

      currentActivity?.startActivityForResult(intent, WRITE_PASSCODE_REQUEST_CODE)
    }

    fun handleWritePasscodeIntentResponse(
        resultCode: Int,
        data: Intent?,
        promise: Promise
    ) {

      when (resultCode) {
        Activity.RESULT_OK -> {
          val resultMap = Arguments.createMap()
          resultMap.putString("data", data?.extras?.get(Key.DATA).toString())
          promise.resolve(resultMap);
        }
        Activity.RESULT_CANCELED -> {
          val code = data?.getIntExtra(Key.ERROR_CODE, ErrorCode.UNKNOWN).toString()
          val message = data?.getStringExtra(Key.ERROR_MESSAGE)!!
          promise.reject(code, Throwable(message))
        }
      }
    }
}
