package com.example.a1342097.assignment1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{

    LinearLayout MainLayout;
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




    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //set up the controls
        MainLayout = (LinearLayout) root.findViewById(R.id.MainLayout);
        TimeLayout = (LinearLayout) root.findViewById(R.id.TimeLayout);
        DateLayout = (LinearLayout) root.findViewById(R.id.DateLayout);

        TxtTitle_EditText = (EditText) root.findViewById(R.id.TxtTitle_EditText);
        TxtBody_EditText = (EditText) root.findViewById(R.id.TxtBody_EditText);
        SwtReminder_Switch = (Switch) root.findViewById(R.id.SwtReminder_Switch);
        LblTime_TextView = (TextView) root.findViewById(R.id.LblTime_TextView);
        LblDate_TextView = (TextView) root.findViewById(R.id.LblDate_TextView);
        Circle1_CircleView = (CircleView) root.findViewById(R.id.Circle1_CircleView);
        Circle2_CircleView = (CircleView) root.findViewById(R.id.Circle2_CircleView);
        Circle3_CircleView = (CircleView) root.findViewById(R.id.Circle3_CircleView);
        Circle4_CircleView = (CircleView) root.findViewById(R.id.Circle4_CircleView);
        Circle5_CircleView = (CircleView) root.findViewById(R.id.Circle5_CircleView);
        Circle6_CircleView = (CircleView) root.findViewById(R.id.Circle6_CircleView);
        Circle7_CircleView = (CircleView) root.findViewById(R.id.Circle7_CircleView);
        Circle8_CircleView = (CircleView) root.findViewById(R.id.Circle8_CircleView);

        ViewHorizontalBar1_CustomView = root.findViewById(R.id.ViewHorizontalBar1_CustomView);
        ViewHorizontalBar2_CustomView = root.findViewById(R.id.ViewHorizontalBar2_CustomView);


        //set the colors of the circles
        Circle1_CircleView.setColor(getResources().getColor(R.color.base08));
        Circle2_CircleView.setColor(getResources().getColor(R.color.base09));
        Circle3_CircleView.setColor(getResources().getColor(R.color.base0A));
        Circle4_CircleView.setColor(getResources().getColor(R.color.base0B));
        Circle5_CircleView.setColor(getResources().getColor(R.color.base0C));
        Circle6_CircleView.setColor(getResources().getColor(R.color.base0D));
        Circle7_CircleView.setColor(getResources().getColor(R.color.base0E));
        Circle8_CircleView.setColor(getResources().getColor(R.color.base0F));


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

        //set the time label
        SimpleDateFormat s = new SimpleDateFormat("hh:mm a");
        String timeFormat = s.format(new java.util.Date());
        LblTime_TextView.setText(timeFormat);

        //setthe date label
        SimpleDateFormat d = new SimpleDateFormat("MMM d, y");
        String dateFormat = d.format(new java.util.Date());
        LblDate_TextView.setText(dateFormat);



        //set the handler for the switch
        SwtReminder_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    TimeLayout.setVisibility(View.VISIBLE);
                    DateLayout.setVisibility(View.VISIBLE);
                    ViewHorizontalBar1_CustomView.setVisibility(View.VISIBLE);
                    ViewHorizontalBar2_CustomView.setVisibility(View.VISIBLE);
                }else
                {
                    TimeLayout.setVisibility(View.GONE);
                    DateLayout.setVisibility(View.GONE);
                    ViewHorizontalBar1_CustomView.setVisibility(View.GONE);
                    ViewHorizontalBar2_CustomView.setVisibility(View.GONE);
                }
            }
        });


        //set handlers of the time and date
        java.util.Date date = new java.util.Date();
        final Calendar calendar = Calendar.getInstance();
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
                            calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hourOfDay, minute);
                            SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
                            String time = t.format(calendar.getTime());
                            LblTime_TextView.setText(time);
                        }
                    }
                );
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
                                calendar.set(year, month, dayOfMonth, calendar.HOUR_OF_DAY, calendar.MINUTE);
                                SimpleDateFormat d = new SimpleDateFormat("MMM d, y");
                                String date = d.format(calendar.getTime());
                                LblDate_TextView.setText(date);
                            }
                        }
                );
                dialogFragment.show(getFragmentManager(), "datePicker");

            }
        });

        return root;
    }

    private class ColorCircleViewOnClick implements CircleView.OnClickListener
    {

        @Override
        public void onClick(View v) {
            CircleView c = (CircleView) v;
            MainLayout.setBackgroundColor(c.getColor());
        }
    }
}


























