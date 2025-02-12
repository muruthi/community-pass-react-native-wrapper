package com.mastercard.compass.cp3.lib.react_native_wrapper

import android.app.Activity
import android.content.Intent
import com.facebook.react.BuildConfig
import com.facebook.react.bridge.*
import com.mastercard.compass.cp3.lib.react_native_wrapper.route.*
import timber.log.Timber

class CompassLibraryReactNativeWrapperModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext),
  ActivityEventListener {
  private lateinit var promise: Promise;
  private var helperObject = CompassKernelUIController.CompassHelper(reactApplicationContext);

  private val consumerDeviceApiRoute: ConsumerDeviceAPIRoute by lazy {
    ConsumerDeviceAPIRoute(reactContext, currentActivity)
  }
  private val registerUserWithBiometricsAPIRoute: RegisterUserWithBiometricsAPIRoute by lazy {
    RegisterUserWithBiometricsAPIRoute(reactContext, currentActivity, helperObject)
  }
  private val registerBasicUserAPIRoute: RegisterBasicUserAPIRoute by lazy {
    RegisterBasicUserAPIRoute(reactContext, currentActivity)
  }
  private val consumerDevicePasscodeAPIRoute: ConsumerDevicePasscodeAPIRoute by lazy {
    ConsumerDevicePasscodeAPIRoute(reactContext, currentActivity)
  }
  private val biometricConsentAPIRoute: BiometricConsentAPIRoute by lazy {
    BiometricConsentAPIRoute(reactContext, currentActivity)
  }
  private val getRegistrationDataAPIRoute by lazy {
    GetRegistrationDataAPIRoute(reactContext, currentActivity)
  }
  private val getVerifyPasscodeAPIRoute by lazy {
    GetVerifyPasscodeAPIRoute(reactContext, currentActivity)
  }
  private val getUserVerificationAPIRoute by lazy {
    GetUserVerificationAPIRoute(reactContext, currentActivity)
  }
  override fun getName(): String {
      return "CompassLibraryReactNativeWrapper"
  }

  init {
    super.initialize()
    if(BuildConfig.DEBUG){
        Timber.plant(Timber.DebugTree())
    }
    reactApplicationContext.addActivityEventListener(this)
  }

  @ReactMethod
  fun saveBiometricConsent(SaveBiometricConsentParams: ReadableMap, promise: Promise){
    this.promise = promise

    biometricConsentAPIRoute.startBiometricConsentIntent(SaveBiometricConsentParams)
  }

  @ReactMethod
  fun getWritePasscode(WritePasscodeParams: ReadableMap, promise: Promise){
    this.promise = promise

    consumerDevicePasscodeAPIRoute.startWritePasscodeIntent(WritePasscodeParams)
  }

  @ReactMethod
  fun getWriteProfile(WriteProfileParams: ReadableMap, promise: Promise){
    this.promise = promise

    consumerDeviceApiRoute.startWriteProfileIntent(WriteProfileParams)
  }

  @ReactMethod
  fun getRegisterBasicUser(RegisterBasicUserParams: ReadableMap, promise: Promise){
    this.promise = promise

  registerBasicUserAPIRoute.startRegisterBasicUserIntent(RegisterBasicUserParams);
  }

  @ReactMethod
  fun getRegisterUserWithBiometrics(RegisterUserWithBiometricsParams: ReadableMap, promise: Promise) {
    this.promise = promise

    registerUserWithBiometricsAPIRoute.startRegisterUserWithBiometricsIntent(
      RegisterUserWithBiometricsParams
    );
  }

  @ReactMethod
  fun getRegistrationData(getRegistrationDataParams: ReadableMap, promise: Promise){
    this.promise = promise
    getRegistrationDataAPIRoute.startGetRegistrationIntent(getRegistrationDataParams)
  }

  @ReactMethod
  fun getVerifyPasscode(getVerifyPasscodeParams: ReadableMap, promise: Promise){
    this.promise = promise
    getVerifyPasscodeAPIRoute.startGetVerifyPasscodeIntent(getVerifyPasscodeParams)
  }

  @ReactMethod
  fun getUserVerification(getUserVerificationParams: ReadableMap, promise: Promise){
    this.promise = promise
    getUserVerificationAPIRoute.startGetUserVerificationIntent(getUserVerificationParams)
  }

  override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
    when(requestCode){
      in BiometricConsentAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in ConsumerDeviceAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in ConsumerDevicePasscodeAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in RegisterUserWithBiometricsAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in RegisterBasicUserAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in GetRegistrationDataAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in GetVerifyPasscodeAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
      in GetUserVerificationAPIRoute.REQUEST_CODE_RANGE -> handleApiRouteResponse(requestCode, resultCode, data)
    }
  }

  override fun onNewIntent(p0: Intent?) {

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
      GetRegistrationDataAPIRoute.GET_REGISTRATION_DATA_REQUEST_CODE -> getRegistrationDataAPIRoute.handleGetRegistrationDataIntentResponse(resultCode, data, this.promise)
      GetVerifyPasscodeAPIRoute.GET_VERIFY_PASSCODE_REQUEST_CODE -> getVerifyPasscodeAPIRoute.handleGetVerifyPasscodeIntentResponse(resultCode, data, this.promise)
      GetUserVerificationAPIRoute.GET_USER_VERIFICATION_REQUEST_CODE -> getUserVerificationAPIRoute.handleGetUserVerificationIntentResponse(resultCode, data, promise)
    }
  }
}
