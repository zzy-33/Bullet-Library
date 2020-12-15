package com.component

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tool.R

/**
 * Created by Jordan on 2020/12/15.
 */
class AngryToast(context: Context) : Toast(context) {


    class Builder(private val context: Context) {

        private var toastGravity: Int = Gravity.TOP
        private var toastGravityX: Int = 0
        private var toastGravityY: Int = 30
        private var toastDuration: Int = LENGTH_SHORT
        private var toastBackground: Int = 0
        private var toastIcon: Int = 0

        private var toastContent: String = ""

        @SuppressLint("InflateParams")
        fun create(): AngryToast {
            val toast = AngryToast(context)
            val contentView = LayoutInflater.from(context).inflate(R.layout.angry_toast, null)
            toast.view = contentView
            initView(toast, contentView)
            return toast
        }

        private fun initView(toast: Toast, view: View) {
            val toastBg = view.findViewById<LinearLayout>(R.id.toast)
            val message = view.findViewById<TextView>(R.id.toastText)
            val icon = view.findViewById<ImageView>(R.id.toastIcon)
            when {
                toastBackground != 0 -> toastBg?.setBackgroundResource(toastBackground)
                toastIcon != 0 -> Glide.with(icon).load(toastIcon).into(icon)
                toastGravity != 0 -> toast.setGravity(toastGravity, toastGravityX, toastGravityY)
                toastDuration != 0 -> toast.duration = toastDuration
            }
            message?.text = toastContent
            if (toastIcon != 0) {
                icon.visibility = View.VISIBLE
            } else {
                icon.visibility = View.GONE
            }
        }

        fun setGravity(gravity: Int, x: Int, y: Int): Builder {
            this.toastGravity = gravity
            this.toastGravityX = x
            this.toastGravityY = y
            return this
        }

        fun setDuration(duration: Int): Builder {
            this.toastDuration = duration
            return this
        }

        fun setContent(content: String): Builder {
            this.toastContent = content
            return this
        }

        fun setContent(content: Int): Builder {
            if (content != 0) {
                this.toastContent = context.getString(content)
            }
            return this
        }

        /**
         * set Toast Background
         */
        fun setBackground(bg: Int): Builder {
            this.toastBackground = bg
            return this
        }

        fun setIcon(icon: Int): Builder {
            this.toastIcon = icon
            return this
        }
    }

}