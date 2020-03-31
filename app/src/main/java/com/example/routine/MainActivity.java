package com.example.routine;

import android.os.Bundle;

import com.example.routine.DbHelper.DatabaseHelper;
import com.example.routine.Dialog.AddDailyReminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.routine.Dialog.AddMonthlyReminder;
import com.example.routine.Dialog.AddWeeklyReminder;
import com.example.routine.Dialog.AddYearlyReminder;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AddDailyReminder.AddDailyReminderListener, AddWeeklyReminder.AddWeeklyReminderListener, AddMonthlyReminder.AddMonthlyReminderListener, AddYearlyReminder.AddYearlyReminderListener {

    FloatingActionMenu floatingActionMenu;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Database Init
        //myDB = new DatabaseHelper(this);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.famMenu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.menu_item4);

        floatingActionButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddDailyReminderDialog();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddWeeklyReminderDialog();
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddMonthlyReminderDialog();
            }
        });

        floatingActionButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddYearlyReminderDialog();
            }
        });
    }

    private void openAddDailyReminderDialog() {
        AddDailyReminder dialog = new AddDailyReminder();
        dialog.show(getSupportFragmentManager(), "Deneme");
    }

    private void openAddWeeklyReminderDialog() {
        AddWeeklyReminder dialog = new AddWeeklyReminder();
        dialog.show(getSupportFragmentManager(), "Deneme");
    }

    private void openAddMonthlyReminderDialog() {
        AddMonthlyReminder dialog = new AddMonthlyReminder();
        dialog.show(getSupportFragmentManager(), "Deneme");
    }

    private void openAddYearlyReminderDialog() {
        AddYearlyReminder dialog = new AddYearlyReminder();
        dialog.show(getSupportFragmentManager(), "Deneme");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getDailyInfo(String eventName, String notifMessage, String currentDate, String endDate, String currentTime, String frequency) {
        String body = "EventName: " + eventName + " NotificationMessage: " + notifMessage + " CurrentDate: " + currentDate + " EndDate: " + endDate + " CurrentTime: " + currentDate + " Frequency: " + frequency;
        Log.d("bediiko", "getDailyInfo: " + body);
        Toast.makeText(this, body, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getWeeklyInfo(String et1, String et2) {
        Toast.makeText(this, "Weekly: ET1: " + et1 + " ET2: " + et2, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getMonthlyInfo(String et1, String et2) {
        Toast.makeText(this, "Monthly: ET1: " + et1 + " ET2: " + et2, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getYearlyInfo(String et1, String et2) {
        Toast.makeText(this, "Yearly: ET1: " + et1 + " ET2: " + et2, Toast.LENGTH_SHORT).show();
    }
}
