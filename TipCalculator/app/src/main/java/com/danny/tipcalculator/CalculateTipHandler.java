package com.danny.tipcalculator;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 1342097 on 2016-08-30.
 */
public class CalculateTipHandler implements View.OnClickListener
{
    private EditText TxtBillTotal;
    private TextView LblTip10;
    private TextView LblTip15;
    private TextView LblTip20;
    private TextView LblTotal10;
    private TextView LblTotal15;
    private TextView LblTotal20;

    public CalculateTipHandler(EditText TxtBillTotal, TextView lblTotal20, TextView lblTip10, TextView lblTip15, TextView lblTip20, TextView lblTotal10, TextView lblTotal15) {
        this.TxtBillTotal = TxtBillTotal;
        this.LblTotal20 = lblTotal20;
        this.LblTip10 = lblTip10;
        this.LblTip15 = lblTip15;
        this.LblTip20 = lblTip20;
        this.LblTotal10 = lblTotal10;
        this.LblTotal15 = lblTotal15;
    }

    @Override
    public void onClick(View view)
    {
        /*Toast toast = Toast.makeText(view.getContext(), "test", Toast.LENGTH_SHORT);
        toast.show();*/

        String totalStr = TxtBillTotal.getText().toString();
        try {
            Tip tip = new Tip(totalStr);
            LblTip10.setText(tip.getTip(0.1));
            LblTip15.setText(tip.getTip(0.15));
            LblTip20.setText(tip.getTip(0.2));
            LblTotal10.setText(tip.getTotalWithTip(0.1));
            LblTotal15.setText(tip.getTotalWithTip(0.15));
            LblTotal20.setText(tip.getTotalWithTip(0.2));
        } catch (TipException e) {
            e.printStackTrace();
        }


    }

}
