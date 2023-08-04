package com.simplemobiletools.clock.extensions

import com.simplemobiletools.clock.R
import com.simplemobiletools.commons.activities.BaseSimpleActivity
import com.simplemobiletools.commons.dialogs.PermissionRequiredDialog
import com.simplemobiletools.commons.extensions.canUseFullScreenIntent

fun BaseSimpleActivity.handleFullScreenNotificationsPermission(callback: (granted: Boolean) -> Unit) {
    handleNotificationPermission { granted ->
        if (granted) {
            if (canUseFullScreenIntent()) {
                callback(true)
            } else {
                PermissionRequiredDialog(
                    activity = this,
                    textId = R.string.allow_full_screen_notifications_reminders,
                    positiveActionCallback = {
                        callback(true)
                    },
                    negativeActionCallback = {
                        // It is not critical to have full screen intents, so we should allow users to continue using the app
                        callback(true)
                    }
                )
            }
        } else {
            callback(false)
        }
    }
}
