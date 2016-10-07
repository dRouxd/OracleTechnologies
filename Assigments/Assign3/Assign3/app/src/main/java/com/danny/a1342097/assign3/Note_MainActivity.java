package com.danny.a1342097.assign3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.danny.a1342097.assign3.MainActivityFragment;

import java.util.Calendar;
import java.util.Date;

public class Note_MainActivity extends AppCompatActivity {

    public static class param
    {
        public static final String title = "TITLE";
        public static final String content = "CONTENT";
        public static final String hasReminder = "HAS_REMINDER";
        public static final String reminderDate = "REMINDER_DATE";
        public static final String category = "CATEGORY";
    }

    public static class results
    {
        public static final String title = "TITLE";
        public static final String content = "CONTENT";
        public static final String hasReminder = "HAS_REMINDER";
        public static final String reminderDate = "REMINDER_DATE";
        public static final String category = "CATEGORY";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/




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
        if (id == R.id.action_save) {


            Note_MainActivityFragment fragment = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_note);

            String title = fragment.TxtTitle_EditText.getText().toString();
            String body = fragment.TxtBody_EditText.getText().toString();
            int category = fragment.category;
            boolean hasReminder = fragment.hasReminder;
            Calendar calendar = fragment.calendar;

            Note note = new Note(title, body, category, hasReminder, calendar.getTime(), new Date());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
