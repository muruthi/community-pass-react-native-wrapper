package com.reactnativecpklibrary.model

import android.os.Parcelable
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegResponse(
  var rId: String? = null,
  var devicdeId: String? = null,
  var status: String? = null,
) : Parcelable {
  fun toWritaableMap() : WritableMap {
    var map = Arguments.createMap()
    map.putString("status", this.status)
    map.putString("rId", this.rId)
    map.putString("deviceId", this.devicdeId)
    return map
  }
}
