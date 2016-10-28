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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MainActivityFragment mainActivityFragment;
    private Note_MainActivityFragment noteMainActivityFragment;
    private boolean isTablet;
    private long currentID;
    private Date currentCreatedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mainActivityFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        noteMainActivityFragment = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);

        isTablet = noteMainActivityFragment != null;


        mainActivityFragment.setOnNoteChosen(new MainActivityFragment.OnNoteChosen() {
            @Override
            public void OnNoteChosen(long id, String title, String content, boolean hasReminder, Date reminder, int category, Date created) {
                Intent intent = new Intent(MainActivity.this, Note_MainActivity.class);
                intent.putExtra(Note_MainActivity.param.id, id);
                intent.putExtra(Note_MainActivity.param.title, title);
                intent.putExtra(Note_MainActivity.param.content, content);
                intent.putExtra(Note_MainActivity.param.hasReminder, hasReminder);
                intent.putExtra(Note_MainActivity.param.reminderDate, reminder);
                intent.putExtra(Note_MainActivity.param.category, category);
                intent.putExtra(Note_MainActivity.param.dateCreated, created);

                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


                if(isTablet) {
                    noteMainActivityFragment.loadNote(title, content, hasReminder, reminder, category);
                    currentID = id;
                    currentCreatedDate = created;
                }
                else
                    startActivityForResult(intent, 1);



            }
        });


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Note_MainActivity.class);
                startActivityForResult(intent, 2);


            }
        });

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

        if (id == R.id.action_save) {

            Note_MainActivityFragment fragment = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);

            String newTitle = fragment.TxtTitle_EditText.getText().toString();
            String newBody = fragment.TxtBody_EditText.getText().toString();
            int newCategory = fragment.category;
            boolean newHasReminder = fragment.hasReminder;
            Calendar newCalendar = fragment.calendar;

            Note note = new Note(newTitle, newBody, newCategory, newHasReminder, newCalendar.getTime(), currentCreatedDate);


            NoteDatabaseHandler dbh = new NoteDatabaseHandler(this);

            try {

                //if new note, ie id = -1, create a new note
                if(currentID == -1)
                {
                    currentID = dbh.getNoteTable().create(note);
                }else   //update the note
                {
                    note.setId(currentID);
                    dbh.getNoteTable().update(note);
                }


            } catch (DatabaseException e) {
                e.printStackTrace();
            }

            mainActivityFragment.updateList();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //update the data of the list
        mainActivityFragment.updateList();

        long id = intent.getLongExtra(Note_MainActivity.results.id, -1);
        String title = intent.getStringExtra(Note_MainActivity.results.title);
        String content = intent.getStringExtra(Note_MainActivity.results.content);
        boolean hasReminder = intent.getBooleanExtra(Note_MainActivity.results.hasReminder, false);
        Date reminderDate = (Date) intent.getSerializableExtra(Note_MainActivity.results.reminderDate);
        int category = intent.getIntExtra(Note_MainActivity.results.category, Color.WHITE);
        Date created = (Date) intent.getSerializableExtra(Note_MainActivity.results.dateCreated);

        final Note note = new Note(title, content, category, hasReminder, reminderDate, created);
        note.setId(id);

        //updated note
        if(requestCode == 1)
        {
            Snackbar.make(findViewById(R.id.fab), "Note Updated", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            NoteDatabaseHandler dbh = new NoteDatabaseHandler(MainActivity.this);

                            try {
                                dbh.getNoteTable().update(note);
                            } catch (DatabaseException e) {
                                e.printStackTrace();
                            }

                            //update the data of the list
                            mainActivityFragment.updateList();
                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();
        }else if(requestCode == 2) //new note
        {
            Snackbar.make(findViewById(R.id.fab), "Note Created", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            NoteDatabaseHandler dbh = new NoteDatabaseHandler(MainActivity.this);

                            try {
                                dbh.getNoteTable().delete(note);
                            } catch (DatabaseException e) {
                                e.printStackTrace();
                            }

                            //update the data of the list
                            mainActivityFragment.updateList();
                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();
        }
    }
}
