package com.danny.a1342097.assign3;

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
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public interface OnNoteChosen
    {
        void OnNoteChosen(String title, String content, boolean hasReminder, Date reminder, int category);
    }

    private Spinner spinner;
    private ListView notes;
    private ArrayAdapter<Note> adapterList;
    private ArrayAdapter<String> adapterSpinner;

    private OnNoteChosen listener;

    private final String SPINNER_TITLE = "Title";
    private final String SPINNER_CREATION_DATE = "Creation Date";
    private final String SPINNER_CATEGORY = "Category";
    private final String SPINNER_REMINDER = "Reminder";

    public MainActivityFragment() {
        listener = null;
    }

    public void setOnNoteChosen(OnNoteChosen listener)
    {
        this.listener = listener;
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
        spinnerArray.add(SPINNER_TITLE);
        spinnerArray.add(SPINNER_CREATION_DATE);
        spinnerArray.add(SPINNER_CATEGORY);
        spinnerArray.add(SPINNER_REMINDER);
        adapterSpinner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        spinner.setAdapter(adapterSpinner);





        //fill the list view
        adapterList = new NoteDataAdapter(this.getContext());
        try {
            final List<Note> data =  dbh.getNoteTable().readAll();

            adapterList.addAll(data);
            notes.setAdapter(adapterList);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();

                    if(selectedItem == SPINNER_TITLE)
                    {
                        adapterList.sort(new Comparator<Note>() {
                            @Override
                            public int compare(Note o1, Note o2) {
                                return o1.getTitle().compareTo(o2.getTitle());
                            }
                        });
                    }else if(selectedItem == SPINNER_CATEGORY)
                    {
                        adapterList.sort(new Comparator<Note>() {
                            @Override
                            public int compare(Note o1, Note o2) {
                                if(o1.getCategory() > o2.getCategory())
                                    return 1;
                                else if (o1.getCategory() < o2.getCategory())
                                    return -1;
                                return 0;
                            }
                        });
                    }else if(selectedItem == SPINNER_CREATION_DATE)
                    {
                        adapterList.sort(new Comparator<Note>() {
                            @Override
                            public int compare(Note o1, Note o2) {
                                return o1.getCreated().compareTo(o2.getCreated());
                            }
                        });
                    }else if(selectedItem == SPINNER_REMINDER)
                    {
                        adapterList.sort(new Comparator<Note>() {
                            @Override
                            public int compare(Note o1, Note o2) {
                                if(o1.HasReminder() && o2.HasReminder())
                                    return o1.getReminder().compareTo(o2.getReminder());
                                else if (o1.HasReminder())
                                    return -1;
                                return 1;
                            }
                        });
                    }


                    notes.setAdapter(adapterList);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

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

            final Note note = getItem(position);

            ImageView category = (ImageView) root.findViewById(R.id.ImgCategory_ImageView);
            ImageView reminder = (ImageView) root.findViewById(R.id.ImgReminder_ImageView);
            TextView title = (TextView) root.findViewById(R.id.LblTitle_TextView);
            TextView content = (TextView) root.findViewById(R.id.LblContent_TextView);

            title.setText(note.getTitle());
            content.setText(note.getBody());
            category.setBackgroundColor(note.getCategory());








            if(note.HasReminder())
            {
                reminder.setImageResource(R.drawable.ic_alarm_on_black_48dp);
                //reminder.setBackgroundResource(R.drawable.ic_alarm_on_black_48dp);

            }else
            {
                reminder.setImageResource(R.drawable.ic_alarm_off_black_48dp);
                //reminder.setBackgroundResource(R.drawable.ic_alarm_off_black_48dp);
            }

            reminder.setScaleType(ImageView.ScaleType.FIT_START);
            reminder.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

            reminder.getLayoutParams().height = 70;
            reminder.getLayoutParams().width = 70;







            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(listener != null)
                    {
                        listener.OnNoteChosen(note.getTitle(), note.getBody(), note.HasReminder(), note.getReminder(), note.getCategory());
                    }
                }
            });

            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        listener.OnNoteChosen(note.getTitle(), note.getBody(), note.HasReminder(), note.getReminder(), note.getCategory());
                    }
                }
            });

            reminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteDatabaseHandler dbh = new NoteDatabaseHandler(getContext());

                    Note update = note;
                    if(update.HasReminder())
                        update.setHasReminder(false);
                    else
                        update.setHasReminder(true);

                    try {
                        dbh.getNoteTable().update(update);

                        adapterList.clear();
                        adapterList.addAll(dbh.getNoteTable().readAll());

                    } catch (DatabaseException e) {
                        e.printStackTrace();
                    }
                }
            });



            return root;
        }

        public long getItemId(int position) { return getItem(position).getId(); }
    }

}
