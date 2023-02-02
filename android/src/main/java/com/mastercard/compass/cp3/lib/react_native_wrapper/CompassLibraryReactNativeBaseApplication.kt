package com.mastercard.compass.cp3.lib.react_native_wrapper

import android.app.Application
import com.facebook.react.BuildConfig
import timber.log.Timber

class CompassLibraryReactNativeBaseApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    if(BuildConfig.DEBUG){
      Timber.plant(Timber.DebugTree())
    }
  }
}
