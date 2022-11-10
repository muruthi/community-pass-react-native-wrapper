package com.reactnativecpklibrary.model

import android.os.Parcelable
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegMethodResponse(
  var method: String? = null,
  var status: String? = null,
) : Parcelable {
  fun toWritaableMap() : WritableMap {
    var map = Arguments.createMap()
    map.putString("status", this.status)
    map.putString("method", this.method)
    return map
  }
}
