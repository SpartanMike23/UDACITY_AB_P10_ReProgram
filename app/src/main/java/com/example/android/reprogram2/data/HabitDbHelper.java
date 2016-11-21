package com.example.android.reprogram2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.reprogram2.data.HabitContract.HabitsEntry;

/**
 * Created by Michael on 10/12/16.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habit.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABITS_TABLE = "Create TABLE " + HabitsEntry.TABLE_NAME + " ("
                + HabitsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitsEntry.COLUMN_HABIT_NAME + " TEXT, "
                + HabitsEntry.COLUMN_MON + " INTEGER NOT NULL DEFAULT 1, "
                + HabitsEntry.COLUMN_TUES + " INTEGER NOT NULL DEFAULT 1, "
                + HabitsEntry.COLUMN_WEDS + " INTEGER NOT NULL DEFAULT 1, "
                + HabitsEntry.COLUMN_THUR + " INTEGER NOT NULL DEFAULT 1, "
                + HabitsEntry.COLUMN_FRI + " INTEGER NOT NULL DEFAULT 1, "
                + HabitsEntry.COLUMN_SAT + " INTEGER NOT NULL DEFAULT 1, "
                + HabitsEntry.COLUMN_SUN + " INTEGER NOT NULL DEFAULT 1);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
