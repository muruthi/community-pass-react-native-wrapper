package com.reactnativecpklibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenericResponse(
  var message: String? = null,
  var status: String? = null,
) : Parcelable
