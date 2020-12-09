# 目录

- 目录
  - [AlertDialog透明背景](#alertdialog透明背景)

## AlertDialog透明背景

将AlertDiaolog的背景色设置成透明色

```kotlin
fun setDialogWindows(dialog: Dialog) {
    val dialogWindow = dialog.window
    dialogWindow?.setBackgroundDrawableResource(R.color.color_transparent) //"#00000000"
    dialogWindow?.setLayout(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
}
```

## 单项Dialog

### SingleDialog

Xml界面：[dialog_single.xml](/src/res/dialog/dialog_single.xml)

```kotlin
fun showSingleDialog(context: Context, title: String, content: String) {
    val builder = android.app.AlertDialog.Builder(context)
    val view = LayoutInflater.from(context).inflate(R.layout.dialog_single, null)
    val dialogTitle = view.findViewById<TextView>(R.id.dialogTitle)
    val dialogContent = view.findViewById<TextView>(R.id.dialogMainText)
    val dialog = builder.setView(view).create()
    setDialogWindows(dialog)
    dialogTitle.text = title
    dialogContent.text = content
    view.findViewById<TextView>(R.id.dialogConfirm).setOnClickListener {
        dialog.dismiss()
    }
    dialog.setCanceledOnTouchOutside(false) //取消外围触控关闭
    dialog.show()
}
```

## 双向Dialog
