package com.reactnativecpklibrary
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.*
import com.reactnativecpklibrary.activity.CpkBioRegistrationActivity
import com.reactnativecpklibrary.activity.CpkConnectActivity
import com.reactnativecpklibrary.activity.CpkRegistrationMethodActivity

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
  fun connectToCpk(reliantAppGuid: String, promise: Promise)
  {
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkConnectActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }

  @ReactMethod
  fun checkRegistrationStatus(programGuid: String, reliantAppGuid: String, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkRegistrationMethodActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }

  @ReactMethod
  fun initBioRegistration(programGuid: String, reliantAppGuid: String, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkBioRegistrationActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }

  override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK) {
      val status = data?.getBooleanExtra("success", false)
      val response = data?.getStringExtra("data")
      val message = data?.getStringExtra("message")
      val map = Arguments.createMap()
      map.putBoolean("status", status!!)
      map.putString("data", response)
      map.putString("message", message)
      this.promise.resolve(map)
    } else {
      val map = Arguments.createMap()
      map.putBoolean("status", false)
      map.putString("data", "")
      map.putString("message", "null")
      this.promise.resolve(map)
    }
  }

  override fun onNewIntent(p0: Intent?) {
    TODO("Not yet implemented")
  }

}
