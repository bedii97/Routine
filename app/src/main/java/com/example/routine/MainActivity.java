package com.example.routine;

import android.os.Bundle;

import com.example.routine.Dialog.AddReminderDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AddReminderDialog.AddReminderDialogListener {

    FloatingActionMenu floatingActionMenu;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.famMenu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.menu_item4);

        floatingActionButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddReminderDialog();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        floatingActionButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openAddReminderDialog() {
        AddReminderDialog dialog = new AddReminderDialog();
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
    public void applyTexts(String et1, String et2) {
        Toast.makeText(this, "ET1: " + et1 + " ET2: " + et2, Toast.LENGTH_SHORT).show();
    }
}
