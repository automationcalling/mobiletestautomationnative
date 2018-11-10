package com.automationcalling.commonutils;

public class Constant {

    public enum SyncTime {
        WAITIN_1SECS(1),
        WAITIN_2SECS(2),
        WAITIN_5SECS(5),
        WAITIN_10SECS(10),
        WAITIN_15SECS(2),
        WAITIN_20SECS(2),
        WAITIN_25SECS(2),
        WAITIN_30SECS(2),
        WAITIN_45SECS(2),
        WAITIN_60SECS(2);

        private final int value;

        SyncTime(final int newValue) {
            value = newValue;
        }

        public int getValue()

        {
            return value;
        }

    }
}
