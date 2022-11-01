package com.reactnativecpklibrary.model

import android.os.Parcelable
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorResponse(
  var errorCode: Int = 999,
  var errorMessage: String = "Something Unknown Happened",
) : Parcelable {
  fun toWritaableMap() : WritableMap {
    var map = Arguments.createMap()
    map.putInt("errorCode", this.errorCode)
    map.putString("errorMessage", this.errorMessage)
    return map
  }
}
