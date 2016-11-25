package com.danny.a1342097.assign3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.danny.a1342097.assign3.Models.AsyncHttpRequest;
import com.danny.a1342097.assign3.Models.HttpProgress;
import com.danny.a1342097.assign3.Models.HttpResponse;
import com.danny.a1342097.assign3.Models.Note;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MainActivityFragment mainActivityFragment;
    private Note_MainActivityFragment noteMainActivityFragment;
    private boolean isTablet;
    private String currentURL;
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
            public void OnNoteChosen(Note note) {

                Intent intent = new Intent(MainActivity.this, Note_MainActivity.class);
                intent.putExtra(Note_MainActivity.param.url, note.getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


                if(isTablet) {
                    noteMainActivityFragment.loadNote(note);
                    currentURL = note.getUrl();
                    currentCreatedDate = note.getCreated();
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


            Note note = fragment.note;

            AsyncHttpRequest task;
            if(note.getUrl() == "")
                task = new AsyncHttpRequest(NoteApplication.PREFIX + "/note", AsyncHttpRequest.Method.POST, note.format());
            else
                task = new AsyncHttpRequest(note.getUrl(), AsyncHttpRequest.Method.PUT, note.format());

            task.execute();

            /*NoteDatabaseHandler dbh = new NoteDatabaseHandler(this);

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
            }*/

            mainActivityFragment.loadList();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent intent) {

        //update the data of the list
        mainActivityFragment.loadList();

        String url = intent.getStringExtra(Note_MainActivity.results.url);

        AsyncHttpRequest task = new AsyncHttpRequest(url, AsyncHttpRequest.Method.GET, null);
        task.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
            @Override
            public void onResult(HttpResponse response) {
                final Note note = Note.parse(response.getBody());


                //updated note
                if(requestCode == 1)
                {
                    Snackbar.make(findViewById(R.id.fab), "Note Updated", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    /*NoteDatabaseHandler dbh = new NoteDatabaseHandler(MainActivity.this);

                                    try {
                                        dbh.getNoteTable().update(note);
                                    } catch (DatabaseException e) {
                                        e.printStackTrace();
                                    }*/

                                    AsyncHttpRequest innerTask = new AsyncHttpRequest(note.getUrl(), AsyncHttpRequest.Method.PUT, note.format());
                                    innerTask.execute();

                                    //update the data of the list
                                    mainActivityFragment.loadList();
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

                                    /*NoteDatabaseHandler dbh = new NoteDatabaseHandler(MainActivity.this);

                                    try {
                                        dbh.getNoteTable().delete(note);
                                    } catch (DatabaseException e) {
                                        e.printStackTrace();
                                    }*/

                                    AsyncHttpRequest innerTask = new AsyncHttpRequest(note.getUrl(), AsyncHttpRequest.Method.DELETE, null);
                                    innerTask.execute();

                                    //update the data of the list
                                    mainActivityFragment.loadList();
                                }
                            })
                            .setActionTextColor(Color.RED)
                            .show();
                }


            }

            @Override
            public void onProgress(HttpProgress progress) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
        task.execute();

    }
}
