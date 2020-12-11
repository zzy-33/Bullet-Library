package com.component

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.example.tool.R

/**
 * Created by Jordan on 2020/12/10.
 */
class AngryDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

    class Builder(private val context: Context) {

        private var title: String = "Tips"
        private var content: String = ""
        private var cancel: String = "Cancel"
        private var confirm: String = "OK"

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
            val dialog = AngryDialog(context, R.style.base_dialog_style)
            val view = LayoutInflater.from(context).inflate(R.layout.angry_dialog, null)
            if (contentView != null) {
                dialog.addContentView(
                    contentView!!,
                    ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                )
            } else {
                dialog.addContentView(
                    view,
                    ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                )
            }
            val attributes = dialog.window?.attributes
            attributes?.width = MATCH_PARENT
            dialog.window?.attributes = attributes
            dialog.setCanceledOnTouchOutside(canceledOnTouch)
            initView(dialog, view)
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
        fun setTitle(
            text: String = "Tips",
            textColor: Int = R.color.black_03,
            textSize: Float = 18F
        ): Builder {
            this.title = text
            this.titleColor = textColor
            this.titleSize = textSize
            return this
        }

        fun setTitle(
            text: Int = R.string.tips,
            textColor: Int = R.color.black_03,
            textSize: Float = 18F
        ): Builder {
            this.title = context.getString(text)
            this.titleColor = textColor
            this.titleSize = textSize
            return this
        }

        /**
         * content TextView By Style
         */
        fun setContent(
            text: String,
            textColor: Int = R.color.black_01,
            textSize: Float = 14F
        ): Builder {
            this.content = text
            this.contentColor = textColor
            this.contentSize = textSize
            return this
        }

        fun setContent(
            text: Int,
            textColor: Int = R.color.black_01,
            textSize: Float = 14F
        ): Builder {
            this.content = context.getString(text)
            this.contentColor = textColor
            this.contentSize = textSize
            return this
        }

        /**
         * cancel Button By TextView Style
         */
        fun setCancelButton(
            text: String = "Cancel",
            textColor: Int = R.color.black_01,
            textSize: Float = 16F
        ): Builder {
            this.cancel = text
            this.cancelColor = textColor
            this.cancelSize = textSize
            return this
        }

        fun setCancelButton(
            text: Int = R.string.cancel,
            textColor: Int = R.color.black_01,
            textSize: Float = 16F
        ): Builder {
            this.cancel = context.getString(text)
            this.cancelColor = textColor
            this.cancelSize = textSize
            return this
        }

        /**
         * confirm Button By TextView Style
         */
        fun setConfirmButton(
            text: String = "OK",
            textColor: Int = R.color.black_01,
            textSize: Float = 16F
        ): Builder {
            this.confirm = text
            this.confirmColor = textColor
            this.confirmSize = textSize
            return this
        }

        fun setConfirmButton(
            text: Int = R.string.ok,
            textColor: Int = R.color.black_01,
            textSize: Float = 16F
        ): Builder {
            this.confirm = context.getString(text)
            this.confirmColor = textColor
            this.confirmSize = textSize
            return this
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
