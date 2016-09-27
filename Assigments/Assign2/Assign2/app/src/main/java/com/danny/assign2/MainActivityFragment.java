package com.danny.assign2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Spinner spinner;
    ListView notes;
    private ArrayAdapter<Note> adapter;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //get the spinner and list view
        spinner = (Spinner) root.findViewById(R.id.SpinSort_Spinner);
        notes = (ListView) root.findViewById(R.id.ListNotes_ListView);

        adapter = new NoteDataAdapter(this.getContext());



        //fill the spinner with options





        //fill the list view

        return root;
    }


    private class NoteDataAdapter extends ArrayAdapter<Note>
    {
        public NoteDataAdapter(Context context) { super(context, -1); }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View root;
            if(convertView != null)
                root = convertView;
            else
            {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                root = inflater.inflate(R.layout.listitem_note, parent, false);
            }

            Note note = getItem(position);

            ImageView category = (ImageView) root.findViewById(R.id.ImgCategory_ImageView);
            ImageView reminder = (ImageView) root.findViewById(R.id.ImgReminder_ImageView);
            TextView title = (TextView) root.findViewById(R.id.LblTitle_TextView);
            TextView content = (TextView) root.findViewById(R.id.LblContent_TextView);

            title.setText(note.getTitle());
            content.setText(note.getBody());
            category.setBackgroundColor(note.getCategory());

            if(note.HasReminder())
            {
                reminder.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_alarm_on_black_48dp, null));
            }


            return root;
        }

        public long getItemId(int position) { return getItem(position).getId(); }
    }

}
