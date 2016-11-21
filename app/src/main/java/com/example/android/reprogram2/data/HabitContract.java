package com.example.android.reprogram2.data;

/**
 * Created by Michael on 10/13/16.
 */
import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract(){}

    public static class HabitsEntry implements BaseColumns {

        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "habits";

        public static final String COLUMN_HABIT_NAME = "Habit";
        public static final String COLUMN_MON = "Mon";
        public static final String COLUMN_TUES = "Tues";
        public static final String COLUMN_WEDS = "Wed";
        public static final String COLUMN_THUR = "Thurs";
        public static final String COLUMN_FRI = "Fri";
        public static final String COLUMN_SAT = "Sat";
        public static final String COLUMN_SUN = "Sun";

        public static int DONE = 0;
        public static int SKIP = 1;
        public static int FAIL = 2;

    }
}
