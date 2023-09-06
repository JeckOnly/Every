package com.sjy.accessibility

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

/**
 * Created by JeckOnly on 2023/9/6
 * Describe: 如何重载各个方法参考：https://developer.android.com/guide/topics/ui/accessibility/service?hl=zh-cn#methods
 */
class MyAccessibilityService: AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        TODO("Not yet implemented")
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)

    }
}