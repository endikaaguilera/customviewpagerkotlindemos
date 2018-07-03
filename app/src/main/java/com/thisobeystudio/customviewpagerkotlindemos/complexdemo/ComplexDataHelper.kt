package com.thisobeystudio.customviewpagerkotlindemos.complexdemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable

data class ComplexDataHelper(
        var posY: Int = 0,
        var rating: Float = 0f,
        val color: Int = Color.LTGRAY) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(posY)
        parcel.writeFloat(rating)
        parcel.writeInt(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComplexDataHelper> {
        override fun createFromParcel(parcel: Parcel): ComplexDataHelper {
            return ComplexDataHelper(parcel)
        }

        override fun newArray(size: Int): Array<ComplexDataHelper?> {
            return arrayOfNulls(size)
        }
    }
}
