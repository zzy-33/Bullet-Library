# Directory

- [Directory](#directory)
  - [Introduction](#introduction)
  - [How to use](#how-to-use)
- [Property](#property)
  - [setDialogTheme](#setdialogtheme)
  - [setView](#setview)
  - [setCanceledOnTouchOutside](#setcanceledontouchoutside)
  - [setDialogBackground](#setdialogbackground)
  - [setTitle](#settitle)
  - [setTitleStyle](#settitlestyle)
  - [setContent](#setcontent)
  - [setContentStyle](#setcontentstyle)
  - [setCancel](#setcancel)
  - [setCancelStyle](#setcancelstyle)
  - [setConfirm](#setconfirm)
  - [setConfirmStyle](#setconfirmstyle)
  - [setCancelListener](#setcancellistener)
  - [setConfirmListener](#setconfirmlistener)
  
## Introduction

Use AngryDialog’s well-designed dialog, such as: upgrade prompt, select prompt and other prompt methods. It contains many more styles, which helps to adapt to different scenarios.

![white](../Image/dialog1.png) ![white2](/Image/dialog.png)![white3](../Image/dialog3.png)![white4](../Image/dialog4.png)

## How to use

It is very simple to use. Initialize AngryDialog. You can also modify and customize the style of the dialog through existing properties. At the same time, you can also use an existing theme and select the corresponding dialog theme.

```kotlin 
AngryDialog.Builder(this)
    .setDialogTheme("white")
    .setTitle("Tips")
    .setContent("white Dialog with tips")
    .setConfirmListener(View.OnClickListener {
        Log.e("AngryDialog","confirm is onclick")
    })
    .create()
    .show()
```

# Property

## setDialogTheme("white")

This is to set the theme of the dialog. Select the theme of the dialog by passing in the corresponding String. Default theme: "white", the existing themes: "purple", "red", "white".

## setView(view)

In the existing properties or themes, you can’t find the style you want, reset the Dialog layout.

## setCanceledOnTouchOutside(false)

When enabled, it can control the touch mask outside the dialog to close the dialog.

## setDialogBackground(R.drawable.step_white)

Redesign the external layout of Dialog, you can pass in color or drawable

## setTitle("Tips")

Set the dialog title, if the value of the incoming title is  null or empty, the title will not be displayed

## setTitleStyle(R.color.black,14F)

Set the style of the title, including: the color of the title font, the size of the title font

## setContent("White Dialog , Hellow World !")

Set the content, if the value of the set content is empty or null, the content will not be displayed

## setContentStyle(R.color.black,14F)

Set the content, if the value of the set content is empty, the content will not be displayed

## setCancel("Cancel")

After enabling, you can modify the font of the "OK" button, set it to empty, dialog will not display the button. Default value: confirm

## setCancelStyle(R.color.black,14F)

Modify the font style of the cancel button, including: font color, font size

## setConfirm("OK")

After enabling, you can modify the font of the OK button, set it to empty, dialog will not display the button.Default value: Confirm

## setConfirmStyle(R.color.black,14F)

Modify the font style of the confirmation button, including: font color, font size

## setCancelListener(View.OnClickListener{})

Add the listener for the click event of the cancel button, the Dialog will be closed by default

## setConfirmListener(View.OnClickListener{})

Add a listener for the click event of the determined button, the Dialog will be closed by default