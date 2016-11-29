package com.danny.a1342097.assign3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.danny.a1342097.assign3.Models.AsyncHttpRequest;
import com.danny.a1342097.assign3.Models.HttpProgress;
import com.danny.a1342097.assign3.Models.HttpResponse;
import com.danny.a1342097.assign3.Models.Note;
import com.danny.a1342097.assign3.Models.User;

import java.util.Calendar;
import java.util.Date;

public class Note_MainActivity extends AppCompatActivity {

    public static class param
    {
        public static final String url = "URL";
    }

    public static class results
    {
        public static final String url = "URL";
    }

    private Note_MainActivityFragment frag;

    private String noteUrl;

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

        noteUrl = intent.getStringExtra(param.url);

        frag = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);

        //if a note was passed
        if(noteUrl != null)
        {
            //load the note for editing
            AsyncHttpRequest task = new AsyncHttpRequest(noteUrl, AsyncHttpRequest.Method.GET, null);
            task.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
                @Override
                public void onResult(HttpResponse response) {
                    frag.loadNote(Note.parse(response.getBody()));
                }

                @Override
                public void onProgress(HttpProgress progress) {

                }

                @Override
                public void onError(Exception e) {

                }
            });
            task.execute();
        }else
        {
            Calendar reminder = Calendar.getInstance();
            reminder.add(Calendar.HOUR, 1);
            Note note = new Note("", "", 0, false, reminder.getTime() , new Date());
            frag.loadNote(note);
        }



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

            final String newTitle = fragment.TxtTitle_EditText.getText().toString();
            final String newBody = fragment.TxtBody_EditText.getText().toString();
            final int newCategory =  ((ColorDrawable)fragment.MainLayout.getBackground()).getColor();
            final boolean newHasReminder = fragment.hasReminder;
            final Calendar newCalendar = fragment.calendar;

            String url = "";

            //create not if new
            /*if(noteUrl == "")
            {
                Note note = new Note(newTitle, newBody, newCategory, newHasReminder, newCalendar.getTime(), new Date());

            }*/


            if(noteUrl == null)
            {
                Note note = new Note(newTitle, newBody, newCategory, newHasReminder, newCalendar.getTime(), new Date());
                note.setCreatedBy(NoteApplication.CONNECTED_USER);
                AsyncHttpRequest task = new AsyncHttpRequest(NoteApplication.PREFIX + "/note", AsyncHttpRequest.Method.PUT, note.format());
                task.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
                    @Override
                    public void onResult(HttpResponse response) {

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();

                    }

                    @Override
                    public void onProgress(HttpProgress progress) {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                task.execute();
            }else
            {
                //get the note before update
                AsyncHttpRequest task = new AsyncHttpRequest(noteUrl, AsyncHttpRequest.Method.GET, null);
                task.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
                    @Override
                    public void onResult(HttpResponse response) {

                        Note oldNote = Note.parse(response.getBody());
                        final Note newNote = new Note(newTitle, newBody, newCategory, newHasReminder, newCalendar.getTime(), oldNote.getCreated());
                        newNote.setUrl(oldNote.getUrl());

                        AsyncHttpRequest innerTaskGetUser = new AsyncHttpRequest(oldNote.getCreatedBy(), AsyncHttpRequest.Method.GET, null);
                        innerTaskGetUser.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
                            @Override
                            public void onResult(HttpResponse response) {

                                User user = User.parse(response.getBody());

                                newNote.setCreatedBy(user.getUrl());

                                AsyncHttpRequest innerTaskUpdate = new AsyncHttpRequest(newNote.getUrl(), AsyncHttpRequest.Method.PUT, newNote.format());
                                innerTaskUpdate.execute();

                                Intent intent = new Intent();
                                intent.putExtra(Note_MainActivity.results.url, noteUrl);
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onProgress(HttpProgress progress) {

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
                        innerTaskGetUser.execute();


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

            /*NoteDatabaseHandler dbh = new NoteDatabaseHandler(this);

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
            }*/




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setValues(Note note)
    {
        frag = (Note_MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);
        frag.loadNote(note);
    }
}
