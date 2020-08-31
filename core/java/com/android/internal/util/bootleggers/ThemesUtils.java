/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util.bootleggers;

import static android.os.UserHandle.USER_SYSTEM;

import android.app.UiModeManager;
import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.os.RemoteException;
import android.util.Log;

public class ThemesUtils {

    public static final String TAG = "ThemesUtils";

    private static final String[] SWITCH_THEMES = {
        "com.android.system.switch.stock", // 0
        "com.android.system.switch.oneplus", // 1
	"com.android.system.switch.narrow", // 2
        "com.android.system.switch.contained", // 3
	"com.android.system.switch.telegram", // 4
    };

    public static final String[] QS_TILE_THEMES = {
            "default_qstile",
            "com.bootleggers.qstile.trim",
            "com.bootleggers.qstile.dualtone",
            "com.bootleggers.qstile.dualtonetrim",
            "com.bootleggers.qstile.cookie",
            "com.bootleggers.qstile.deletround",
            "com.bootleggers.qstile.inktober",
            "com.bootleggers.qstile.shishunights",
            "com.bootleggers.qstile.monogradient",
            "com.bootleggers.qstile.wavey",
            "com.bootleggers.qstile.squaremedo",
            "com.bootleggers.qstile.ninja",
            "com.bootleggers.qstile.dottedcircle",
            "com.bootleggers.qstile.shishuink",
            "com.bootleggers.qstile.attemptmountain",
            "com.bootleggers.qstile.neonlike",
            "com.bootleggers.qstile.oos",
            "com.bootleggers.qstile.triangles",
            "com.bootleggers.qstile.divided",
            "com.bootleggers.qstile.cosmos",
            "com.bootleggers.qstile.gradient",
            "com.bootleggers.qstile.pureaccent"
    };

    public static final String[] QS_HEADER_THEMES = {
        "com.android.systemui.qsheader.black", // 0
        "com.android.systemui.qsheader.grey", // 1
        "com.android.systemui.qsheader.lightgrey", // 2
        "com.android.systemui.qsheader.accent", // 3
        "com.android.systemui.qsheader.transparent", // 4
    };

    public static void updateSwitchStyle(IOverlayManager om, int userId, int switchStyle) {
        if (switchStyle == 0) {
            stockSwitchStyle(om, userId);
        } else {
            try {
                om.setEnabled(SWITCH_THEMES[switchStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change switch theme", e);
            }
        }
    }

    public static void stockSwitchStyle(IOverlayManager om, int userId) {
        for (int i = 0; i < SWITCH_THEMES.length; i++) {
            String switchtheme = SWITCH_THEMES[i];
            try {
                om.setEnabled(switchtheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
