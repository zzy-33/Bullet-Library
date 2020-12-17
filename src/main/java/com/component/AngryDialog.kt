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
        private var cancel: String = ""
        private var confirm: String = ""

        private var titleColor: Int = 0
        private var contentColor: Int = 0
        private var cancelColor: Int = 0
        private var confirmColor: Int = 0
        private var background: Int = 0
        private var buttonLeft: Int = 0
        private var buttonTop: Int = 0
        private var buttonRight: Int = 0
        private var buttonBottom: Int = 0

        private var titleBackground: Int = 0
        private var contentBackground: Int = 0
        private var cancelBackground: Int = 0
        private var confirmBackground: Int = 0

        private var verticalView: Int = View.VISIBLE
        private var horizontalView: Int = View.VISIBLE

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
            val dialogHorizontal = view.findViewById<View>(R.id.angry_dialog_horizontal_view)
            val dialogButtonView = view.findViewById<LinearLayout>(R.id.angry_dialog_button_view)

            dialogTitle.text = title
            dialogContent.text = content
            dialogCancel.text = cancel
            dialogConfirm.text = confirm
            dialogButtonView.setPadding(buttonLeft, buttonTop, buttonRight, buttonBottom)

            if (verticalView != 0) {
                dialogVertical.visibility = verticalView
            }
            if (horizontalView != 0) {
                dialogHorizontal.visibility = horizontalView
            }
            if (background != 0) {
                dialogView.setBackgroundResource(background)
            }
            if (titleBackground != 0) {
                dialogTitle.setBackgroundResource(titleBackground)
            }
            if (contentBackground != 0) {
                dialogContent.setBackgroundResource(contentBackground)
            }
            if (cancelBackground != 0) {
                dialogCancel.setBackgroundResource(cancelBackground)
            }
            if (confirmBackground != 0) {
                dialogConfirm.setBackgroundResource(confirmBackground)
            }

            if (titleColor != 0) {
                dialogTitle.setTextColor(ContextCompat.getColor(context, titleColor))
            }
            if (contentColor != 0) {
                dialogContent.setTextColor(ContextCompat.getColor(context, contentColor))
            }
            if (cancelColor != 0) {
                dialogCancel.setTextColor(ContextCompat.getColor(context, cancelColor))
            }
            if (confirmColor != 0) {
                dialogConfirm.setTextColor(ContextCompat.getColor(context, confirmColor))
            }
            if (contentSize != 0F) {
                dialogContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentSize)
            }
            if (titleSize != 0F) {
                dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
            }
            if (cancelSize != 0F) {
                dialogCancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, cancelSize)
            }
            if (confirmSize != 0F) {
                dialogConfirm.setTextSize(TypedValue.COMPLEX_UNIT_SP, confirmSize)
            }

            dialogCancel.setOnClickListener {
                dialogCancelListener?.onClick(it)
                dialog.dismiss()
            }
            dialogConfirm.setOnClickListener {
                dialogConfirmListener?.onClick(it)
                dialog.dismiss()
            }

            if (cancel.isEmpty()) {
                dialogCancel.visibility = View.GONE
            } else {
                dialogCancel.visibility = View.VISIBLE
            }
            if (confirm.isEmpty()) {
                dialogConfirm.visibility = View.GONE
            } else {
                dialogConfirm.visibility = View.VISIBLE
            }
            if (title.isEmpty()) {
                dialogTitle.visibility = View.GONE
            } else {
                dialogTitle.visibility = View.VISIBLE
            }
            if (content.isEmpty()) {
                dialogContent.visibility = View.GONE
            } else {
                dialogContent.visibility = View.VISIBLE
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
         * set line  vertical && horizontal
         */
        fun setLineView(vertical: Int, horizontal: Int): Builder {
            this.verticalView = vertical
            this.horizontalView = horizontal
            return this
        }

        fun setButtonPadding(left: Int, top: Int, right: Int, bottom: Int): Builder {
            this.buttonLeft = left
            this.buttonTop = top
            this.buttonRight = right
            this.buttonBottom = bottom

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

        fun setTitleStyle(textColor: Int = 0, textSize: Float = 0F, background: Int = 0): Builder {
            this.titleColor = textColor
            this.titleSize = textSize
            this.titleBackground - background
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

        fun setContentStyle(
            textColor: Int = 0,
            textSize: Float = 0F,
            background: Int = 0
        ): Builder {
            this.contentColor = textColor
            this.contentSize = textSize
            this.contentBackground = background
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

        fun setCancelStyle(textColor: Int = 0, textSize: Float = 0F, background: Int = 0): Builder {
            this.cancelColor = textColor
            this.cancelSize = textSize
            this.cancelBackground = background
            return this
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

        fun setConfirmStyle(
            textColor: Int = 0,
            textSize: Float = 0F,
            background: Int = 0
        ): Builder {
            this.confirmColor = textColor
            this.confirmSize = textSize
            this.confirmBackground = background
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

