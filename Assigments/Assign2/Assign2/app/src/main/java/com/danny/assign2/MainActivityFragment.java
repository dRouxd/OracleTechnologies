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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Spinner spinner;
    ListView notes;
    private ArrayAdapter<Note> adapterList;
    private ArrayAdapter<String> adapterSpinner;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        NoteDatabaseHandler dbh = new NoteDatabaseHandler(getContext());


        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //get the spinner and list view
        spinner = (Spinner) root.findViewById(R.id.SpinSort_Spinner);
        notes = (ListView) root.findViewById(R.id.ListNotes_ListView);



        //fill the spinner with options
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Title");
        spinnerArray.add("Creation Date");
        spinnerArray.add("Category");
        spinnerArray.add("Reminder");
        adapterSpinner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        spinner.setAdapter(adapterSpinner);

        //fill the list view
        adapterList = new NoteDataAdapter(this.getContext());
        try {
            final List<Note> data =  dbh.getNoteTable().readAll();

            adapterList.addAll(data);
            notes.setAdapter(adapterList);

            notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), NoteData.getNoteById(id, data).toString(), Toast.LENGTH_SHORT).show();
                }
            });


        } catch (DatabaseException e) {
            e.printStackTrace();
        }




        return root;
    }


    private class NoteDataAdapter extends ArrayAdapter<Note>
    {
        public NoteDataAdapter(Context context) { super(context, -1); }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
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
            }else
            {
                reminder.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_alarm_off_black_48dp, null));
            }
            reminder.setScaleType(ImageView.ScaleType.FIT_START);
            reminder.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

            return root;
        }

        public long getItemId(int position) { return getItem(position).getId(); }
    }

}
