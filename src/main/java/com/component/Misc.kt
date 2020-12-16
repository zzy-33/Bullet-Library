package com.component

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

/**
 * Created by Jordan on 2020/12/16.
 */

/**
 * set icon font
 */
fun setIconFont(context: Context, text: TextView, icAvatar: Int) {
    val typeface = Typeface.createFromAsset(context.assets, "angryFont/angryIconFont.ttf")
    text.typeface = typeface
    text.text = text.context.getString(icAvatar)
}
