package com.reactnativecpklibrary
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.*
import com.reactnativecpklibrary.route.*

class CpkLibraryModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext), ActivityEventListener {
  lateinit var promise: Promise;
  private lateinit var activity: Activity;
  private var helperObject = CompassKernelUIController.CompassHelper(reactApplicationContext);

  private val consumerDeviceApiRoute: ConsumerDeviceAPIRoute by lazy {
    ConsumerDeviceAPIRoute(activity)
  }
  private val registerUserWithBiometricsAPIRoute: RegisterUserWithBiometricsAPIRoute by lazy {
    RegisterUserWithBiometricsAPIRoute(reactContext, currentActivity, helperObject)
  }
  private val registerBasicUserAPIRoute: RegisterBasicUserAPIRoute by lazy {
    RegisterBasicUserAPIRoute(activity)
  }
  private val consumerDevicePasscodeAPIRoute: ConsumerDevicePasscodeAPIRoute by lazy {
    ConsumerDevicePasscodeAPIRoute(activity)
  }
  private val biometricConsentAPIRoute: BiometricConsentAPIRoute by lazy {
    BiometricConsentAPIRoute(activity)
  }

  override fun getName(): String {
      return "CpkLibrary"
  }

  init {
    super.initialize()
    reactApplicationContext.addActivityEventListener(this)
  }
//
//  @ReactMethod
//  fun connectKernelService(reliantAppGuid: String, promise: Promise)
//  {
//    this.promise = promise
//    val connectIntent = Intent(reactApplicationContext, CpkConnectActivity::class.java)
//    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
//    currentActivity?.startActivityForResult(connectIntent, 1)
//
//  }
//
//  @ReactMethod
//  fun checkRegistrationStatus(programGuid: String, reliantAppGuid: String, promise: Promise){
//    this.promise = promise
//    val checkIntent = Intent(reactApplicationContext, CpkRegistrationMethodActivity::class.java)
//    checkIntent.let {
//      it.putExtra("reliantAppGuid", reliantAppGuid)
//      it.putExtra("programGuid", programGuid)
//    }
//    currentActivity?.startActivityForResult(checkIntent, 1)
//  }
//
//  @ReactMethod
//  fun registerWithPasscode(programGuid: String, reliantAppGuid: String, passCode: String, overWrite: Boolean, promise: Promise){
//    this.promise = promise
//    val registerPasscodeIntent = Intent(reactApplicationContext, CpkPasscodeRegistrationActivity::class.java)
//    registerPasscodeIntent.let {
//      it.putExtra("reliantAppGuid", reliantAppGuid);
//      it.putExtra("programGuid", programGuid);
//      it.putExtra("passCode", passCode);
//      it.putExtra("overWrite", overWrite);
//    }
//    currentActivity?.startActivityForResult(registerPasscodeIntent, 2)
//  }

  @ReactMethod
  fun registerWithBio(programGuid: String, reliantAppGuid: String, consentId: String, promise: Promise){
    this.promise = promise

    registerUserWithBiometricsAPIRoute.startRegisterUserWithBiometricsIntent(programGuid, reliantAppGuid, consentId);
//
//    val registerBioIntent = Intent(reactApplicationContext, CpkBioRegistrationActivity::class.java) // Change from CpkPasscodeRegistrationActivity to CpkBioRegistrationActivity
//
//    registerBioIntent.let {
//      it.putExtra("reliantAppGuid", reliantAppGuid);
//      it.putExtra("programGuid", programGuid);
//      it.putExtra("consentId", consentId);
//      it.putExtra("modalities", modalities);
//    }
//    currentActivity?.startActivityForResult(registerBioIntent, 3)
  }
//
//  @ReactMethod
//  fun blackListCard(programGuid: String, reliantAppGuid: String, rId: String, consumerDeviceId: String, promise: Promise){
//    this.promise = promise
//    val blackListIntent = Intent(reactApplicationContext, CpkBlacklistCardActivity::class.java)
//    blackListIntent.let {
//      it.putExtra("reliantAppGuid", reliantAppGuid);
//      it.putExtra("programGuid", programGuid);
//      it.putExtra("rId", rId);
//      it.putExtra("consumerDeviceId", consumerDeviceId)
//    }
//    currentActivity?.startActivityForResult(blackListIntent, 9)
//  }
//
//
//  @ReactMethod
//  fun authenticateWithPasscode(programGuid: String, reliantAppGuid: String, passCode: String, promise: Promise){
//    this.promise = promise
//    val authenticatePasscodeIntent = Intent(reactApplicationContext, CpkPasscodeAuthenticationActivity::class.java)
//    authenticatePasscodeIntent.let {
//      it.putExtra("reliantAppGuid", reliantAppGuid);
//      it.putExtra("programGuid", programGuid);
//      it.putExtra("passCode", passCode);
//    }
//    currentActivity?.startActivityForResult(authenticatePasscodeIntent, 3)
//  }


  /**@ReactMethod
  fun initBioRegistration(programGuid: String, reliantAppGuid: String, promise: Promise){
    this.promise = promise
    val connectIntent = Intent(reactApplicationContext, CpkBioRegistrationActivity::class.java)
    connectIntent.putExtra("reliantAppGuid", reliantAppGuid);
    connectIntent.putExtra("programGuid", programGuid);
    currentActivity?.startActivityForResult(connectIntent, 2)
  }**/

  override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
    when(requestCode){
      in BiometricConsentAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in ConsumerDeviceAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in ConsumerDevicePasscodeAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in RegisterUserWithBiometricsAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in RegisterBasicUserAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
    }
  }

  override fun onNewIntent(p0: Intent?) {
    TODO("Not yet implemented")
  }


  private fun handleApiRouteResponse(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    when (requestCode) {
      BiometricConsentAPIRoute.BIOMETRIC_CONSENT_REQUEST_CODE -> biometricConsentAPIRoute.handleBiometricConsentIntentResponse(resultCode, data, this.promise)
      ConsumerDeviceAPIRoute.WRITE_PROFILE_REQUEST_CODE -> consumerDeviceApiRoute.handleWriteProfileIntentResponse(resultCode, data, this.promise)
      ConsumerDevicePasscodeAPIRoute.WRITE_PASSCODE_REQUEST_CODE -> consumerDevicePasscodeAPIRoute.handleWritePasscodeIntentResponse(resultCode, data, this.promise)
      RegisterUserWithBiometricsAPIRoute.REGISTER_BIOMETRICS_REQUEST_CODE -> registerUserWithBiometricsAPIRoute.handleRegisterUserWithBiometricsIntentResponse(resultCode, data, this.promise)
      RegisterBasicUserAPIRoute.REGISTER_BASIC_USER_REQUEST_CODE -> registerBasicUserAPIRoute.handleRegisterBasicUserIntentResponse(resultCode, data, this.promise)
    }
  }
}
