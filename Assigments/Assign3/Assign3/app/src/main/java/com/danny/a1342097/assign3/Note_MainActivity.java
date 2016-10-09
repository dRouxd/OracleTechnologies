package com.danny.a1342097.assign3;

import android.content.Intent;
import android.graphics.Color;
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
        public static final String id = "ID";
        public static final String title = "TITLE";
        public static final String content = "CONTENT";
        public static final String hasReminder = "HAS_REMINDER";
        public static final String reminderDate = "REMINDER_DATE";
        public static final String category = "CATEGORY";
        public static final String dateCreated = "CREATED";
    }

    public static class results
    {
        public static final String id = "ID";
        public static final String title = "TITLE";
        public static final String content = "CONTENT";
        public static final String hasReminder = "HAS_REMINDER";
        public static final String reminderDate = "REMINDER_DATE";
        public static final String category = "CATEGORY";
        public static final String dateCreated = "CREATED";
    }

    private Note_MainActivityFragment frag;

    private long noteID;
    private String title;
    private String content;
    private boolean hasReminder;
    private Date reminderDate;
    private int category;
    private Date created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.note_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        Intent intent = getIntent();

        noteID = intent.getLongExtra(param.id, -1);
        title = intent.getStringExtra(param.title);
        content = intent.getStringExtra(param.content);
        hasReminder = intent.getBooleanExtra(param.hasReminder, false);
        reminderDate = (Date) intent.getSerializableExtra(param.reminderDate);
        category = intent.getIntExtra(param.category, Color.WHITE);
        created = (Date) intent.getSerializableExtra(param.dateCreated);

        if(title == null)
            title = "";
        if(content == null)
            content = "";
        if(reminderDate == null)
            reminderDate = new Date();
        if(created == null)
            created = new Date();

        frag = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);

        frag.loadNote(title, content, hasReminder, reminderDate, category);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_menu, menu);
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

            Note_MainActivityFragment fragment = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);

            String newTitle = fragment.TxtTitle_EditText.getText().toString();
            String newBody = fragment.TxtBody_EditText.getText().toString();
            int newCategory = fragment.category;
            boolean newHasReminder = fragment.hasReminder;
            Calendar newCalendar = fragment.calendar;

            Note note = new Note(newTitle, newBody, newCategory, newHasReminder, newCalendar.getTime(), created);


            NoteDatabaseHandler dbh = new NoteDatabaseHandler(this);

            try {

                //if new note, ie id = -1, create a new note
                if(noteID == -1)
                {
                    noteID = dbh.getNoteTable().create(note);
                }else   //update the note
                {
                    note.setId(noteID);
                    dbh.getNoteTable().update(note);
                }


            } catch (DatabaseException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent();

            intent.putExtra(Note_MainActivity.results.id, noteID);
            intent.putExtra(Note_MainActivity.results.title, title);
            intent.putExtra(Note_MainActivity.results.content, content);
            intent.putExtra(Note_MainActivity.results.hasReminder, hasReminder);
            intent.putExtra(Note_MainActivity.results.reminderDate, reminderDate);
            intent.putExtra(Note_MainActivity.results.category, category);
            intent.putExtra(Note_MainActivity.results.dateCreated, created);

            setResult(RESULT_OK, intent);

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setValues(long id, String title, String content, boolean hasReminder, Date reminderDate, int category, Date created)
    {
        noteID = id;
        this.title = title;
        this.content = content;
        this.hasReminder = hasReminder;
        this.reminderDate = reminderDate;
        this.category = category;
        this.created = created;

        frag = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);
        frag.loadNote(this.title, this.content, this.hasReminder, this.reminderDate, this.category);
    }
}
