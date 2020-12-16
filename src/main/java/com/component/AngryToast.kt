package com.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.tool.R

/**
 * Created by Jordan on 2020/12/15.
 */
class AngryToast(context: Context) : Toast(context) {


    class Builder(private val context: Context) {


        private var contentColor: Int = 0
        private var toastGravity: Int = Gravity.TOP
        private var toastGravityX: Int = 0
        private var toastGravityY: Int = 30
        private var toastDuration: Int = LENGTH_SHORT
        private var toastBackground: Int = 0
        private var toastIcon: Int = 0
        private var toastTopIcon: Int = 0
        private var toastIconFontColor: Int = 0

        private var toastContent: String = ""
        private var toastIconFont: String = ""

        private var contentSize: Float = 0F
        private var toastIconFontSize: Float = 0F

        private var contentView: View? = null

        @SuppressLint("InflateParams")
        fun create(): AngryToast {
            val toast = AngryToast(context)
            val view = LayoutInflater.from(context).inflate(R.layout.angry_toast, null)
            if (contentView == null) {
                contentView = view
                initView(toast, contentView!!)
            }
            toast.view = contentView
            return toast
        }

        private fun initView(toast: Toast, view: View) {
            val toastBg = view.findViewById<LinearLayout>(R.id.toast)
            val message = view.findViewById<TextView>(R.id.toastText)
            val icon = view.findViewById<ImageView>(R.id.toastIcon)
            val topIcon = view.findViewById<ImageView>(R.id.toastTopIcon)
            val iconFont = view.findViewById<TextView>(R.id.toastIcon2)

            when (toastIconFont) {
                "warn" -> setIconFont(context, iconFont, R.string.ic_warn)
                "success" -> setIconFont(context, iconFont, R.string.ic_success)
            }

            if (toastGravity != 0) {
                toast.setGravity(toastGravity, toastGravityX, toastGravityY)
            }
            if (toastDuration != 0) {
                toast.duration = toastDuration
            }
            if (toastBackground != 0) {
                toastBg?.setBackgroundResource(toastBackground)
            }
            if (contentColor != 0) {
                message.setTextColor(ContextCompat.getColor(context, contentColor))
            }
            if (toastIconFontColor != 0) {
                iconFont.setTextColor(ContextCompat.getColor(context, toastIconFontColor))
            }
            if (contentSize != 0F) {
                message.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentSize)
            }
            if (toastIconFontSize != 0F) {
                iconFont.setTextSize(TypedValue.COMPLEX_UNIT_SP, toastIconFontSize)
            }
            Glide.with(icon).load(toastIcon).into(icon)
            Glide.with(topIcon).load(toastTopIcon).into(topIcon)
            message?.text = toastContent

            if (toastIcon != 0) {
                icon.visibility = View.VISIBLE
            } else {
                icon.visibility = View.GONE
            }
            if (toastTopIcon != 0) {
                topIcon.visibility = View.VISIBLE
            } else {
                topIcon.visibility = View.GONE
            }
            if (toastIconFont.isNotEmpty()) {
                iconFont.visibility = View.VISIBLE
            } else {
                iconFont.visibility = View.GONE
            }
        }

        /**
         * set Toast View
         */
        fun setView(view: View): Builder {
            this.contentView = view
            return this
        }

        /**
         * show Toast position
         * gravity use by " Gravity.CENTER " ,"  Gravity.TOP ", " Gravity.BOTTOM "
         */
        fun setGravity(gravity: Int, x: Int, y: Int): Builder {
            this.toastGravity = gravity
            this.toastGravityX = x
            this.toastGravityY = y
            return this
        }

        /**
         * show toast time
         * duration use by " Toast.LENGTH_LONG " ," Toast.LENGTH_SHORT "
         */
        fun setDuration(duration: Int): Builder {
            this.toastDuration = duration
            return this
        }

        /**
         * set icon font
         * icon use by "warn","success"
         */
        fun setIconFont(icon: String): Builder {
            this.toastIconFont = icon
            return this
        }

        fun setIconFontStyle(iconColor: Int, iconSize: Float): Builder {
            this.toastIconFontColor = iconColor
            this.toastIconFontSize = iconSize
            return this
        }

        /**
         * set content text
         */
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
         * set content text Style
         */
        fun setContentStyle(textColor: Int, textSize: Float): Builder {
            this.contentColor = textColor
            this.contentSize = textSize
            return this
        }

        /**
         * set Toast Background
         */
        fun setBackground(bg: Int): Builder {
            this.toastBackground = bg
            return this
        }

        /**
         * set the icon to the left of the content
         */
        fun setIcon(icon: Int): Builder {
            this.toastIcon = icon
            return this
        }

        /**
         * set Icon above content
         */
        fun setTopIcon(topIcon: Int): Builder {
            this.toastTopIcon = topIcon
            return this
        }

        fun show(): AngryToast {
            val toast: AngryToast = create()
            toast.show()
            return toast
        }
    }

}