package com.terabyte.servicehomework;

public class Const {
    public static final int MODE_SLEEP = 0;
    public static final int MODE_RUN = 1;

    public static final String SH_PREFERENCES_NAME = "sharedPreferences";
    public static final String SH_PREFERENCES_KEY_MODE = "shPreferencesKeyMode";
    public static final String SH_PREFERENCES_KEY_HOURS = "shPreferencesKeyHours";
    public static final String SH_PREFERENCES_KEY_MINUTES = "shPreferencesMinutes";

    public static final String INTENT_KEY_HOURS = "intentKeyHours";
    public static final String INTENT_KEY_MINUTES = "intentKeyMinutes";

    public static final String NOTIFICATION_CHANNEL_ID = "notificationChannelId";
    public static final String NOTIFICATION_CHANNEL_NAME = "notificationChannelName";
    //notification id can not be 0
    public static final int NOTIFICATION_FOREGROUND_SERVICE_ID = 1;
    public static final int NOTIFICATION_RESULT_ID = 2;
}
