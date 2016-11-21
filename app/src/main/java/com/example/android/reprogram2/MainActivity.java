package com.example.android.reprogram2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.reprogram2.data.HabitContract;
import com.example.android.reprogram2.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitDbHelper = new HabitDbHelper(this);
        insertHabitDummy();
        readHabit();
    }

    //Insert Dummy Method
    public void insertHabitDummy() {
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitsEntry.COLUMN_HABIT_NAME, "Walking the Dog");
        values.put(HabitContract.HabitsEntry.COLUMN_MON, HabitContract.HabitsEntry.SKIP);
        values.put(HabitContract.HabitsEntry.COLUMN_TUES, HabitContract.HabitsEntry.DONE);
        values.put(HabitContract.HabitsEntry.COLUMN_WEDS, HabitContract.HabitsEntry.FAIL);
        values.put(HabitContract.HabitsEntry.COLUMN_THUR, HabitContract.HabitsEntry.SKIP);
        values.put(HabitContract.HabitsEntry.COLUMN_FRI, HabitContract.HabitsEntry.DONE);
        values.put(HabitContract.HabitsEntry.COLUMN_SAT, HabitContract.HabitsEntry.FAIL);
        values.put(HabitContract.HabitsEntry.COLUMN_SUN, HabitContract.HabitsEntry.DONE);

        long newRow = db.insert(HabitContract.HabitsEntry.TABLE_NAME, null, values);

        if (newRow == -1) {
            Toast.makeText(this, "Error saving", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "New Saved: " + newRow, Toast.LENGTH_SHORT).show();
        }
    }

    public void readHabit() {

        HabitDbHelper mHabitHelper = new HabitDbHelper(this);
        SQLiteDatabase dbReadable = mHabitHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitsEntry._ID,
                HabitContract.HabitsEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitsEntry.COLUMN_MON,
                HabitContract.HabitsEntry.COLUMN_TUES,
                HabitContract.HabitsEntry.COLUMN_WEDS,
                HabitContract.HabitsEntry.COLUMN_THUR,
                HabitContract.HabitsEntry.COLUMN_FRI,
                HabitContract.HabitsEntry.COLUMN_SAT,
                HabitContract.HabitsEntry.COLUMN_SUN
        };

        Cursor cursor = dbReadable.query(
                HabitContract.HabitsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView tableView = (TextView) findViewById(R.id.tableView);

        try {
            tableView.setText(("The Habit table contain: " + cursor.getCount() + " Habits." + "\n\n"));

            tableView.append(HabitContract.HabitsEntry._ID + " - " +
                    HabitContract.HabitsEntry.COLUMN_HABIT_NAME + " - " +
                    HabitContract.HabitsEntry.COLUMN_MON + " - " +
                    HabitContract.HabitsEntry.COLUMN_TUES + " - " +
                    HabitContract.HabitsEntry.COLUMN_WEDS + " - " +
                    HabitContract.HabitsEntry.COLUMN_THUR + " - " +
                    HabitContract.HabitsEntry.COLUMN_FRI + " - " +
                    HabitContract.HabitsEntry.COLUMN_SAT + " - " +
                    HabitContract.HabitsEntry.COLUMN_SUN + "\n");

            //figure out the index of each columns

            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry._ID);
            int habitNameColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_HABIT_NAME);
            int monColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_MON);
            int tuesColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_TUES);
            int wedColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_WEDS);
            int thursColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_THUR);
            int friColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_FRI);
            int satColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_SAT);
            int sunColumnIndex = cursor.getColumnIndex(HabitContract.HabitsEntry.COLUMN_SUN);

            //While you can move to the next row in the table, create current rows
            // values, append the Textview.
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(habitNameColumnIndex);
                int currentMon = cursor.getInt(monColumnIndex);
                int currentTues = cursor.getInt(tuesColumnIndex);
                int currentWed = cursor.getInt(wedColumnIndex);
                int currentThurs = cursor.getInt(thursColumnIndex);
                int currentFri = cursor.getInt(friColumnIndex);
                int currentSat = cursor.getInt(satColumnIndex);
                int currentSun = cursor.getInt(sunColumnIndex);

                tableView.append("\n" + currentID + " - " +
                        currentName + " - " +
                        currentMon + " - " +
                        currentTues + " - " +
                        currentWed + " - " +
                        currentThurs + " - " +
                        currentFri + " - " +
                        currentSat + " - " +
                        currentSun);
            }
        } finally {
            cursor.close();
        }

    }
}
