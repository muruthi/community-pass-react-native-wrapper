package com.reactnativecpklibrary.model

import android.os.Parcelable
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BlackListResponse(
  var status: String? = null,
) : Parcelable {
  fun toWritableMap() : WritableMap {
    var map = Arguments.createMap()
    map.putString("status", this.status)
    return map
  }
}
