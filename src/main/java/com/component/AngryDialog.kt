package com.component

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.example.tool.R

/**
 * Created by Jordan on 2020/12/10.
 */
class AngryDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

    class Builder(private val context: Context) {

        private var title: String = ""
        private var content: String = ""
        private var cancel: String = "Cancel"
        private var confirm: String = "OK"
        private var dialogTheme: String = ""

        private var titleColor: Int = 0
        private var contentColor: Int = 0
        private var cancelColor: Int = 0
        private var confirmColor: Int = 0
        private var background: Int = 0

        private var titleSize: Float = 18F
        private var contentSize: Float = 14F
        private var cancelSize: Float = 16F
        private var confirmSize: Float = 16F

        private var canceledOnTouch: Boolean = false
        private var contentView: View? = null

        private var dialogCancelListener: View.OnClickListener? = null
        private var dialogConfirmListener: View.OnClickListener? = null

        /**
         * The basic layout must set the width and height, otherwise the layout will be displayed
         */
        @SuppressLint("InflateParams")
        fun create(): AngryDialog {
            val dialog = AngryDialog(context, R.style.Theme_dialog)
            when (dialogTheme) {
                "white" -> context.setTheme(R.style.Theme_Dialog_white)
                "purple" -> context.setTheme(R.style.Theme_Dialog_purple)
                "red" -> context.setTheme(R.style.Theme_Dialog_red)
                else -> context.setTheme(R.style.Theme_Dialog_white)
            }

            if (contentView != null) {
                dialog.addContentView(
                    contentView!!, ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                )
            } else {
                contentView = LayoutInflater.from(context).inflate(R.layout.angry_dialog, null)
                dialog.addContentView(
                    contentView!!, ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                )
                initView(dialog, contentView!!)
            }
            val attributes = dialog.window?.attributes
            attributes?.width = MATCH_PARENT
            dialog.window?.attributes = attributes
            dialog.setCanceledOnTouchOutside(canceledOnTouch)
            return dialog
        }

        private fun initView(dialog: AngryDialog, view: View) {
            val dialogView = view.findViewById<LinearLayout>(R.id.angry_dialog_view)
            val dialogTitle = view.findViewById<TextView>(R.id.angry_dialog_title)
            val dialogContent = view.findViewById<TextView>(R.id.angry_dialog_content)
            val dialogCancel = view.findViewById<TextView>(R.id.angry_dialog_cancel)
            val dialogConfirm = view.findViewById<TextView>(R.id.angry_dialog_confirm)
            val dialogVertical = view.findViewById<View>(R.id.angry_dialog_vertical_view)

            dialogTitle.text = title
            dialogContent.text = content
            dialogCancel.text = cancel
            dialogConfirm.text = confirm

            when {
                background != 0 -> {
                    dialogView.setBackgroundResource(background)
                }
                titleColor != 0 -> {
                    dialogTitle.setTextColor(ContextCompat.getColor(context, titleColor))
                }
                contentColor != 0 -> {
                    dialogContent.setTextColor(ContextCompat.getColor(context, contentColor))
                }
                cancelColor != 0 -> {
                    dialogCancel.setTextColor(ContextCompat.getColor(context, cancelColor))
                }
                confirmColor != 0 -> {
                    dialogConfirm.setTextColor(ContextCompat.getColor(context, confirmColor))
                }
                contentSize != 0F -> {
                    dialogContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentSize)
                }
                titleSize != 0F -> {
                    dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
                }
                cancelSize != 0F -> {
                    dialogCancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, cancelSize)
                }
                confirmSize != 0F -> {
                    dialogConfirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, confirmSize)
                }
            }

            dialogCancel.setOnClickListener {
                dialogCancelListener?.onClick(it)
                dialog.dismiss()
            }
            dialogConfirm.setOnClickListener {
                dialogConfirmListener?.onClick(it)
                dialog.dismiss()
            }
            //Button状态
            if (cancel.isEmpty()) {
                if (confirm.isNotEmpty()) {
                    dialogConfirm.visibility = View.VISIBLE
                    dialogCancel.visibility = View.GONE
                    dialogVertical.visibility = View.GONE
                }
            } else {
                if (confirm.isEmpty()) {
                    dialogCancel.visibility = View.VISIBLE
                    dialogConfirm.visibility = View.GONE
                    dialogVertical.visibility = View.GONE
                }
            }
            if (title.isEmpty()) {
                dialogTitle.visibility = View.GONE
            } else {
                dialogTitle.visibility = View.VISIBLE
            }
        }

        /**
         * choose dialog theme
         * can choose "purple","white"，"red"
         */
        fun setDialogTheme(theme: String): Builder {
            this.dialogTheme = theme
            return this
        }

        fun setDialogTheme(theme: Int): Builder {
            if (theme != 0) {
                this.dialogTheme = context.getString(theme)
            }
            return this
        }

        /**
         * set dialog View
         */
        fun setView(view: View): Builder {
            this.contentView = view
            return this
        }

        /**
         * touched is true ,click the mask to close dialog
         * touched is false ,click the mask will not cloase dialog
         */
        fun setCanceledOnTouchOutside(touched: Boolean): Builder {
            this.canceledOnTouch = touched
            return this
        }

        /**
         * Dialog Background
         */
        fun setDialogBackground(background: Int): Builder {
            this.background = background
            return this
        }

        /**
         * title TextView By Style
         */
        fun setTitle(text: String): Builder {
            this.title = text
            return this
        }

        fun setTitle(text: Int): Builder {
            if (text != 0) {
                this.title = context.getString(text)
            }
            return this
        }

        fun setTitleStyle(textColor: Int, textSize: Float): Builder {
            this.titleColor = textColor
            this.titleSize = textSize
            return this
        }

        /**
         * content TextView By Style
         */
        fun setContent(text: String): Builder {
            this.content = text
            return this
        }

        fun setContent(text: Int): Builder {
            if (text != 0) {
                this.content = context.getString(text)
            }
            return this
        }

        fun setContentStyle(textColor: Int, textSize: Float): Builder {
            this.contentColor = textColor
            this.contentSize = textSize
            return this
        }

        /**
         * cancel Button By TextView Style
         */
        fun setCancel(text: String): Builder {
            this.cancel = text
            return this
        }

        fun setCancel(text: Int): Builder {
            if (text != 0) {
                this.cancel = context.getString(text)
            }
            return this
        }

        fun setCancelStyle(textColor: Int, textSize: Float) {
            this.cancelColor = textColor
            this.cancelSize = textSize
        }

        /**
         * confirm Button By TextView Style
         */
        fun setConfirm(text: String): Builder {
            this.confirm = text
            return this
        }

        fun setConfirm(text: Int): Builder {
            if (text != 0) {
                this.confirm = context.getString(text)
            }
            return this
        }

        fun setConfirmStyle(textColor: Int, textSize: Float) {
            this.confirmColor = textColor
            this.confirmSize = textSize
        }


        fun setCancelListener(listener: View.OnClickListener? = null): Builder {
            this.dialogCancelListener = listener
            return this
        }

        fun setConfirmListener(listener: View.OnClickListener? = null): Builder {
            this.dialogConfirmListener = listener
            return this
        }
    }
}

