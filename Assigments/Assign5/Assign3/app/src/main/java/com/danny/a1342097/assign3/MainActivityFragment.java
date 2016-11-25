package com.danny.a1342097.assign3;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.danny.a1342097.assign3.Models.AsyncHttpRequest;
import com.danny.a1342097.assign3.Models.HttpProgress;
import com.danny.a1342097.assign3.Models.HttpResponse;
import com.danny.a1342097.assign3.Models.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public interface OnNoteChosen
    {
        void OnNoteChosen(Note note);
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
        loadList();




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



            /*title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(listener != null)
                    {
                        listener.OnNoteChosen(note);
                    }
                }
            });

            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        listener.OnNoteChosen(note);
                    }
                }
            });*/


            reminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String url = note.getUrl();
                    if(note.HasReminder())
                        note.setHasReminder(false);
                    else
                        note.setHasReminder(true);

                    AsyncHttpRequest task = new AsyncHttpRequest(url, AsyncHttpRequest.Method.PUT, note.format());
                    task.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
                        @Override
                        public void onResult(HttpResponse response) {
                            loadList();
                        }

                        @Override
                        public void onProgress(HttpProgress progress) {

                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });

                }
            });



            return root;
        }

    }


    private void sortAdapter(String spinnerSelectedItem)
    {
        if(spinnerSelectedItem == SPINNER_TITLE)
        {
            adapterList.sort(new Comparator<Note>() {
                @Override
                public int compare(Note o1, Note o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }else if(spinnerSelectedItem == SPINNER_CATEGORY)
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
        }else if(spinnerSelectedItem == SPINNER_CREATION_DATE)
        {
            adapterList.sort(new Comparator<Note>() {
                @Override
                public int compare(Note o1, Note o2) {
                    return o1.getCreated().compareTo(o2.getCreated());
                }
            });
        }else if(spinnerSelectedItem == SPINNER_REMINDER)
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
    }


    public void loadList()
    {

        String url = NoteApplication.PREFIX + "/note";
        AsyncHttpRequest task = new AsyncHttpRequest(url, AsyncHttpRequest.Method.GET, null);
        task.setOnResponseListener(new AsyncHttpRequest.OnResponse() {
            @Override
            public void onResult(HttpResponse response) {
                List<Note> data = Arrays.asList(Note.parseArray(response.getBody()));
                adapterList.clear();
                adapterList.addAll(data);
                sortAdapter(spinner.getSelectedItem().toString());
                notes.setAdapter(adapterList);

                notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Note n = (Note) notes.getItemAtPosition(position);
                        listener.OnNoteChosen(n);
                    }
                });
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
