package starter.ca.qc.johnabbott.cs.cs616.starter.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.Note;

public class NoteListActivity extends AppCompatActivity {

    private static final int LAUNCH_CREATE_MODE = 0;
    private static final int LAUNCH_EDIT_MODE = 1;

    private NoteListFragment noteListFragment;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // launch the NoteActivity
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                startActivityForResult(intent, LAUNCH_CREATE_MODE);
            }
        });

        // get a reference to the note list fragment
        noteListFragment = (NoteListFragment) getSupportFragmentManager().findFragmentById(R.id.noteList_Fragment);

        // when a note is chosen, start the Note editor with this note
        noteListFragment.setOnNoteChosenListener(new NoteListFragment.OnNoteChosen() {
            @Override
            public void onNoteChosen(Note note) {
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                intent.putExtra(NoteActivity.params.ID, note.getId());
                startActivityForResult(intent, LAUNCH_EDIT_MODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            noteListFragment.refreshNotes();

            Snackbar snackbar = null;
            if(requestCode == LAUNCH_CREATE_MODE)
                snackbar = Snackbar.make(fab, "Note Created", Snackbar.LENGTH_LONG);
            else if(requestCode == LAUNCH_EDIT_MODE)
                snackbar = Snackbar.make(fab, "Note Updated", Snackbar.LENGTH_LONG);

            if(snackbar != null)
                snackbar.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
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
}
