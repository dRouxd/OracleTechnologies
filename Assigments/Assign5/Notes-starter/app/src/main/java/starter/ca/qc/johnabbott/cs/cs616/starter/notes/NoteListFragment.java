package starter.ca.qc.johnabbott.cs.cs616.starter.notes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.DatabaseException;
import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.DatabaseHandler;
import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.Note;

/**
 * A placeholder fragment containing a simple view.
 */
public class NoteListFragment extends Fragment {

    public interface OnNoteChosen {
        void onNoteChosen(Note note);
    }

    private ListView notes;
    private OnNoteChosen listener;

    public NoteListFragment() {
    }

    public void setOnNoteChosenListener(OnNoteChosen listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note_list, container, false);

        notes = (ListView) root.findViewById(R.id.notes_ListView);

        refreshNotes();

        return root;

    }

    public void refreshNotes() {

        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        DatabaseHandler dbh = new DatabaseHandler(getContext());
        final List<Note> data;
        try {
            data = dbh.getNoteTable().readAll();
            noteAdapter.addAll(data);
            notes.setAdapter(noteAdapter);

            notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    //Toast.makeText(getContext(), data.get(i).toString(), Toast.LENGTH_LONG).show();

                    // an Event!
                    if(listener != null)
                        listener.onNoteChosen(data.get(i));

                }
            });

        } catch (DatabaseException e) {
            e.printStackTrace();
        }




    }
}
