package com.reactnativecpklibrary.model

import android.os.Parcelable
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BioResponse(
  var rId: String? = null,
  var status: String? = null,
) : Parcelable {
  fun toWritaableMap() : WritableMap {
    var map = Arguments.createMap()
    map.putString("status", this.status)
    map.putString("rId", this.rId)
    return map
  }
}
