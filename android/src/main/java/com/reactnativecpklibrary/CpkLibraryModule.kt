package com.reactnativecpklibrary
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.*
import com.reactnativecpklibrary.activity.*
import com.reactnativecpklibrary.model.BioResponse
import com.reactnativecpklibrary.model.PasscodeResponse
import com.reactnativecpklibrary.model.RegMethodResponse
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
    currentActivity?.startActivityForResult(connectIntent, 1)
  }

  @ReactMethod
  fun registerWithPasscode(programGuid: String, reliantAppGuid: String, passCode: String, overWrite: Boolean, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkPasscodeRegistrationActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    connectIntent.putExtra("passCode", passCode);
    connectIntent.putExtra("overWrite", overWrite);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }

  @ReactMethod
  fun registerWithBio(programGuid: String, reliantAppGuid: String, overWrite: Boolean, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkPasscodeRegistrationActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    connectIntent.putExtra("overWrite", overWrite);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }


  @ReactMethod
  fun authenticateWithPasscode(programGuid: String, reliantAppGuid: String, passCode: String, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkPasscodeAuthenticationActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    connectIntent.putExtra("passCode", passCode);
    currentActivity?.startActivityForResult(connectIntent, 3)
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
    when(resultCode){
      Activity.RESULT_OK -> {
        when(requestCode){
          3 -> {
            val status = data?.getBooleanExtra("success", false)
            val response = data?.getParcelableExtra<PasscodeResponse>("data")
            val message = data?.getStringExtra("message")
            val map = Arguments.createMap()
            map.putBoolean("status", status!!)
            map.putMap("data", response?.toWritaableMap())
            map.putString("message", message)
            this.promise.resolve(map)
          }
          1 -> {
            val status = data?.getBooleanExtra("success", false)
            val response = data?.getParcelableExtra<RegMethodResponse>("data")
            val message = data?.getStringExtra("message")
            val map = Arguments.createMap()
            map.putBoolean("status", status!!)
            map.putMap("data", response?.toWritaableMap())
            map.putString("message", message)
            this.promise.resolve(map)
          }
          else -> {
            val status = data?.getBooleanExtra("success", false)
            val response = data?.getParcelableExtra<BioResponse>("data")
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
    TODO("Not yet implemented")
  }

  fun pojo2Map(obj: Any): Map<String, Any> {
    val hashMap: MutableMap<String, Any> = HashMap()
    try {
      val c: Class<out Any> = obj.javaClass
      val m = c.methods
      for (i in m.indices) {
        if (m[i].name.indexOf("get") == 0) {
          val name = m[i].name.lowercase(Locale.getDefault()).substring(3, 4) + m[i].name.substring(4)
          hashMap[name] = m[i].invoke(obj, *arrayOfNulls(0))
        }
      }
    } catch (e: Throwable) {
      //log error
    }
    return hashMap
  }

}
