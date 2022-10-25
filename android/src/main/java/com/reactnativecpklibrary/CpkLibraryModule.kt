package com.reactnativecpklibrary
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.facebook.react.bridge.*
import com.reactnativecpklibrary.activity.CpkAuthenticateActivity

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
      var bundle = Bundle()
      bundle.putString("reliantAppGuid", "AAA")
      currentActivity?.startActivityForResult(Intent(reactApplicationContext, CpkAuthenticateActivity::class.java), 2, bundle)
    }

    @ReactMethod
    fun hello(promise: Promise){
      this.promise = promise
      promise.resolve("hello")
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
