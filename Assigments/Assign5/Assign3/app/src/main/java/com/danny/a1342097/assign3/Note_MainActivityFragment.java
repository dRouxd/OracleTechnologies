package com.danny.a1342097.assign3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.danny.a1342097.assign3.Models.CircleView;
import com.danny.a1342097.assign3.Models.DatePickerDialogFragment;
import com.danny.a1342097.assign3.Models.Note;
import com.danny.a1342097.assign3.Models.TimePickerDialogFragment;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class Note_MainActivityFragment extends Fragment
{

    final String TIME_DISPLAY_FORMAT = "hh:mm a"; //IE 11:56 AM
    final String DATE_DISPLAY_FORMAT = "MMM d, y"; //IE Jan 6, 2016

    LinearLayout MainLayout;

    //need time and date layout to make them "gone" if reminder is switched to off
    LinearLayout TimeLayout;
    LinearLayout DateLayout;

    EditText TxtTitle_EditText;
    EditText TxtBody_EditText;

    Switch SwtReminder_Switch;
    TextView LblTime_TextView;
    TextView LblDate_TextView;

    CircleView Circle1_CircleView;
    CircleView Circle2_CircleView;
    CircleView Circle3_CircleView;
    CircleView Circle4_CircleView;
    CircleView Circle5_CircleView;
    CircleView Circle6_CircleView;
    CircleView Circle7_CircleView;
    CircleView Circle8_CircleView;

    View ViewHorizontalBar1_CustomView;
    View ViewHorizontalBar2_CustomView;


    public Note note;

    public boolean hasReminder;

    public final Calendar calendar = Calendar.getInstance();



    public Note_MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.note_fragment_main, container, false);

        //set the layouts
        MainLayout = (LinearLayout) root.findViewById(R.id.note_fragment_layout);
        TimeLayout = (LinearLayout) root.findViewById(R.id.TimeLayout);
        DateLayout = (LinearLayout) root.findViewById(R.id.DateLayout);

        //set the EditTexts
        TxtTitle_EditText = (EditText) root.findViewById(R.id.TxtTitle_EditText);
        TxtBody_EditText = (EditText) root.findViewById(R.id.TxtBody_EditText);

        //set the reminder section
        SwtReminder_Switch = (Switch) root.findViewById(R.id.SwtReminder_Switch);
        LblTime_TextView = (TextView) root.findViewById(R.id.LblTime_TextView);
        LblDate_TextView = (TextView) root.findViewById(R.id.LblDate_TextView);

        //set the category section
        Circle1_CircleView = (CircleView) root.findViewById(R.id.Circle1_CircleView);
        Circle2_CircleView = (CircleView) root.findViewById(R.id.Circle2_CircleView);
        Circle3_CircleView = (CircleView) root.findViewById(R.id.Circle3_CircleView);
        Circle4_CircleView = (CircleView) root.findViewById(R.id.Circle4_CircleView);
        Circle5_CircleView = (CircleView) root.findViewById(R.id.Circle5_CircleView);
        Circle6_CircleView = (CircleView) root.findViewById(R.id.Circle6_CircleView);
        Circle7_CircleView = (CircleView) root.findViewById(R.id.Circle7_CircleView);
        Circle8_CircleView = (CircleView) root.findViewById(R.id.Circle8_CircleView);

        //set the horizontal bars view
        ViewHorizontalBar1_CustomView = root.findViewById(R.id.ViewHorizontalBar1_CustomView);
        ViewHorizontalBar2_CustomView = root.findViewById(R.id.ViewHorizontalBar2_CustomView);



        //set up the handler for the colors
        ColorCircleViewOnClick colorHandler = new ColorCircleViewOnClick();

        Circle1_CircleView.setOnClickListener(colorHandler);
        Circle2_CircleView.setOnClickListener(colorHandler);
        Circle3_CircleView.setOnClickListener(colorHandler);
        Circle4_CircleView.setOnClickListener(colorHandler);
        Circle5_CircleView.setOnClickListener(colorHandler);
        Circle6_CircleView.setOnClickListener(colorHandler);
        Circle7_CircleView.setOnClickListener(colorHandler);
        Circle8_CircleView.setOnClickListener(colorHandler);

        //set the label at current time
        SimpleDateFormat s = new SimpleDateFormat(TIME_DISPLAY_FORMAT);
        String timeFormat = s.format(new java.util.Date());
        LblTime_TextView.setText(timeFormat);

        //set the label at current date
        SimpleDateFormat d = new SimpleDateFormat(DATE_DISPLAY_FORMAT);
        String dateFormat = d.format(new java.util.Date());
        LblDate_TextView.setText(dateFormat);



        //create the handler for the switch
        SwtReminder_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    //if reminder is on, make the time, date and the horizontal bars visible
                    TimeLayout.setVisibility(View.VISIBLE);
                    DateLayout.setVisibility(View.VISIBLE);
                    ViewHorizontalBar1_CustomView.setVisibility(View.VISIBLE);
                    ViewHorizontalBar2_CustomView.setVisibility(View.VISIBLE);
                }else
                {
                    //if reminder is off, make the time, date and the horizontal bars gone
                    TimeLayout.setVisibility(View.GONE);
                    DateLayout.setVisibility(View.GONE);
                    ViewHorizontalBar1_CustomView.setVisibility(View.GONE);
                    ViewHorizontalBar2_CustomView.setVisibility(View.GONE);
                }
            }
        });


        //set the calendar time
        Date date = new Date();
        calendar.setTime(date);

        //set the handler for the time
        LblTime_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = TimePickerDialogFragment.create(
                    calendar.getTime(),
                    new TimePickerDialog.OnTimeSetListener(){
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                        {
                            //set the calendar to the modified time
                            calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hourOfDay, minute);

                            //modify the label in the desired format
                            SimpleDateFormat t = new SimpleDateFormat(TIME_DISPLAY_FORMAT);
                            String time = t.format(calendar.getTime());
                            LblTime_TextView.setText(time);
                        }
                    }
                );

                //show the dialog
                dialogFragment.show(getFragmentManager(), "timePicker");

            }
        });

        //set the handler for the date
        LblDate_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = DatePickerDialogFragment.createDatePicker(
                        calendar.getTime(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //set the calendar to the modified date
                                calendar.set(year, month, dayOfMonth, calendar.HOUR_OF_DAY, calendar.MINUTE);

                                //modifiy the label in the desired format
                                SimpleDateFormat d = new SimpleDateFormat(DATE_DISPLAY_FORMAT);
                                String date = d.format(calendar.getTime());
                                LblDate_TextView.setText(date);
                            }
                        }
                );

                //show the dialog
                dialogFragment.show(getFragmentManager(), "datePicker");

            }
        });

        return root;
    }

    //event handler for when one of the circle view is clicked
    private class ColorCircleViewOnClick implements CircleView.OnClickListener
    {

        @Override
        public void onClick(View v) {
            CircleView c = (CircleView) v;

            //set note background to the color of the circle view
            MainLayout.setBackgroundColor(c.getColor());

        }
    }


    public void loadNote(Note note)
    {
        this.note = note;

        hasReminder = note.HasReminder();

        TxtTitle_EditText.setText(note.getTitle());
        TxtBody_EditText.setText(note.getBody());

        SwtReminder_Switch.setChecked(note.HasReminder());

        calendar.setTime(note.getReminder());

        SimpleDateFormat t = new SimpleDateFormat(TIME_DISPLAY_FORMAT);
        String time = t.format(calendar.getTime());
        LblTime_TextView.setText(time);

        t = new SimpleDateFormat(DATE_DISPLAY_FORMAT);
        String date = t.format(calendar.getTime());
        LblDate_TextView.setText(date);

        MainLayout.setBackgroundColor(note.getCategory());

    }


}


























