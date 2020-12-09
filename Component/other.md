# 前言

- [前言](#前言)
  - [匹配本地字符串](#匹配本地字符串)
  - [匹配本地图片](#匹配本地图片)
  - [获取手机AaId](#获取手机aaid)
  - [地区限制](#地区限制)

## 匹配本地字符串

```kotlin
fun getStringName(context: Context, name: String?): String {
    if (name.isNullOrEmpty()) {
        return ""
    }
    val backName = context.resources.getIdentifier(name, "string", context.packageName)
    return if (backName == 0) {
        name
    } else {
        context.getString(backName)
    }
}
```

## 匹配本地图片

```kotlin
fun getFlag(context: Context, name: String?): Int {
    val flagName = "flag_" + name?.toLowerCase(Locale.ROOT)
    val flag = context.resources.getIdentifier(flagName, "drawable", context.packageName)
    return if (flag == 0) {
        0
    } else {
        flag
    }
}
```

## 获取手机AaId

AaId:[PhoneAaId](../src/kotlin/PhoneAaId.kt)

```kotlin
fun showAaId(activity: Activity) {
    Thread(Runnable {
        Log.d(tag, "phoneAaId：${PhoneAaId().getGoogleAdId(activity)}")
    }).start()
}
```

## 地区限制

```kotlin
fun limit(context: Context): Boolean {
    val languageName = TimeZone.getDefault().displayName
    val languageId = TimeZone.getDefault().id
    val telManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val imSi = telManager.simCountryIso
    try {
        if (imSi == "cn" || languageName == "中国标准时间") {
            if (!languageId.contains("Macao", ignoreCase = true) && !languageId.contains(
                    "Macau",
                    ignoreCase = true
                )
            ) {
//                showTitleDialog(context, R.string.not_support) {}
                return false
            }
        }
    } catch (e: Exception) {
        Log.e(tag, "location get failed: ${e.message}")
    }
    return true
}
```
