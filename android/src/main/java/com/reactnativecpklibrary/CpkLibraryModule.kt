package com.reactnativecpklibrary
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.*
import com.reactnativecpklibrary.activity.*
import com.reactnativecpklibrary.model.*
import java.util.*


class CpkLibraryModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext), ActivityEventListener {

  lateinit var promise: Promise;

  override fun getName(): String {
      return "CpkLibrary"
  }

  init {
    super.initialize()
    reactApplicationContext.addActivityEventListener(CpkLibraryModule@this)
  }

  @ReactMethod
  fun connectKernelService(reliantAppGuid: String, promise: Promise)
  {
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkConnectActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    currentActivity?.startActivityForResult(connectIntent, 1)
  }

  @ReactMethod
  fun checkRegistrationStatus(programGuid: String, reliantAppGuid: String, promise: Promise){
    this.promise = promise
    val checkIntent = Intent(reactApplicationContext, CpkRegistrationMethodActivity::class.java)
    checkIntent.let {
      it.putExtra("reliantAppGuid", reliantAppGuid)
      it.putExtra("programGuid", programGuid)
    }
    currentActivity?.startActivityForResult(checkIntent, 1)
  }

  @ReactMethod
  fun registerWithPasscode(programGuid: String, reliantAppGuid: String, passCode: String, overWrite: Boolean, promise: Promise){
    this.promise = promise
    val registerPasscodeIntent = Intent(reactApplicationContext, CpkPasscodeRegistrationActivity::class.java)
    registerPasscodeIntent.let {
      it.putExtra("reliantAppGuid", reliantAppGuid);
      it.putExtra("programGuid", programGuid);
      it.putExtra("passCode", passCode);
      it.putExtra("overWrite", overWrite);
    }
    currentActivity?.startActivityForResult(registerPasscodeIntent, 2)
  }

  @ReactMethod
  fun registerWithBio(programGuid: String, reliantAppGuid: String, modalities : Array<String>, overWrite: Boolean, promise: Promise){
    this.promise = promise
    val registerBioIntent = Intent(reactApplicationContext, CpkPasscodeRegistrationActivity::class.java)
    registerBioIntent.let {
      it.putExtra("reliantAppGuid", reliantAppGuid);
      it.putExtra("programGuid", programGuid);
      it.putExtra("overWrite", overWrite);
      it.putExtra("modalities", modalities);
    }
    currentActivity?.startActivityForResult(registerBioIntent, 2)
  }

  @ReactMethod
  fun blackListCard(programGuid: String, reliantAppGuid: String, rId: String, consumerDeviceId: String, promise: Promise){
    this.promise = promise
    val blackListIntent = Intent(reactApplicationContext, CpkBlacklistCardActivity::class.java)
    blackListIntent.let {
      it.putExtra("reliantAppGuid", reliantAppGuid);
      it.putExtra("programGuid", programGuid);
      it.putExtra("rId", rId);
      it.putExtra("consumerDeviceId", consumerDeviceId)
    }
    currentActivity?.startActivityForResult(blackListIntent, 9)
  }


  @ReactMethod
  fun authenticateWithPasscode(programGuid: String, reliantAppGuid: String, passCode: String, promise: Promise){
    this.promise = promise
    val authenticatePasscodeIntent = Intent(reactApplicationContext, CpkPasscodeAuthenticationActivity::class.java)
    authenticatePasscodeIntent.let {
      it.putExtra("reliantAppGuid", reliantAppGuid);
      it.putExtra("programGuid", programGuid);
      it.putExtra("passCode", passCode);
    }
    currentActivity?.startActivityForResult(authenticatePasscodeIntent, 3)
  }


  /**@ReactMethod
  fun initBioRegistration(programGuid: String, reliantAppGuid: String, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkBioRegistrationActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }**/

  override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
    when(resultCode){
      Activity.RESULT_OK -> {
        when(requestCode){
          3 -> {
            val status = data?.getStringExtra("status`")
            if(status.equals("success", ignoreCase = true)){
              val response = data?.getParcelableExtra<PasscodeResponse>("data")
              val message = data?.getStringExtra("message")
              val map = Arguments.createMap()
              map.putString("status", status)
              map.putMap("data", response?.toWritaableMap())
              map.putString("message", message)
              this.promise.resolve(map)
            } else {
              val response = data?.getParcelableExtra<ErrorResponse>("data")
              val message = data?.getStringExtra("message")
              val map = Arguments.createMap()
              map.putString("status", status)
              map.putMap("data", response?.toWritaableMap())
              map.putString("message", message)
              this.promise.resolve(map)
            }
          }
          1 -> {
            val status = data?.getStringExtra("status")
            if(status.equals("success", ignoreCase = true)){
              val response = data?.getParcelableExtra<ConnectResponse>("data")
              val message = data?.getStringExtra("message")
              val map = Arguments.createMap()
              map.putString("status", status)
              map.putMap("data", response?.toWritaableMap())
              map.putString("message", message)
              this.promise.resolve(map)
            } else {
              val response = data?.getParcelableExtra<ErrorResponse>("data")
              val message = data?.getStringExtra("message")
              val map = Arguments.createMap()
              map.putString("status", status)
              map.putMap("data", response?.toWritaableMap())
              map.putString("message", message)
              this.promise.resolve(map)
            }
          }
          else -> {
            val status = data?.getBooleanExtra("success", false)
            val response = data?.getParcelableExtra<ErrorResponse>("data")
            val message = data?.getStringExtra("message")
            val map = Arguments.createMap()
            map.putBoolean("status", status!!)
            map.putMap("data", response?.toWritaableMap())
            map.putString("message", message)
            this.promise.resolve(map)
          }
        }
      }
      else -> {
        val map = Arguments.createMap()
        map.putBoolean("status", false)
        map.putString("data", "")
        map.putString("message", "null")
        this.promise.resolve(map)
      }
    }
  }

  override fun onNewIntent(p0: Intent?) {

  }
}
