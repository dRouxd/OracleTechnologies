package com.danny.tipcalculator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // fields to store UI component references
    private EditText TxtBillTotal;
    private Button BtnCalculate;
    private TextView LblTip10;
    private TextView LblTip15;
    private TextView LblTip20;
    private TextView LblTotal10;
    private TextView LblTotal15;
    private TextView LblTotal20;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        TxtBillTotal = (EditText) root.findViewById(R.id.TxtBillTotal_EditText);
        BtnCalculate = (Button) root.findViewById(R.id.BtnCalculate_Button);

        LblTip10 = (TextView) root.findViewById(R.id.LblTip10_TextView);
        LblTip15 = (TextView) root.findViewById(R.id.LblTip15_TextView);
        LblTip20 = (TextView) root.findViewById(R.id.LblTip20_TextView);
        LblTotal10 = (TextView) root.findViewById(R.id.LblTotal10_TextView);
        LblTotal15 = (TextView) root.findViewById(R.id.LblTotal15_TextView);
        LblTotal20 = (TextView) root.findViewById(R.id.LblTotal20_TextView);

        //set event handler manually for the button press
        BtnCalculate.setOnClickListener(new CalculateTipHandler(TxtBillTotal, LblTip10, LblTip15, LblTip20, LblTotal10, LblTotal15, LblTotal20));

        return root;
    }




}