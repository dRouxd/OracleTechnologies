package com.danny.colorpicker;

import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * Created by 1342097 on 2016-09-02.
 */

//version 1 of handler
public class ColorPreviewUpdateHandlerV1 implements SeekBar.OnSeekBarChangeListener
{

    private SeekBar SbarRed_SeekBar;
    private SeekBar SbarGreen_SeekBar;
    private SeekBar SbarBlue_SeekBar;
    private SeekBar SbarAlpha_SeekBar;
    private ImageView ImgViewColors_ImageView;
    private LinearLayout fragment;

    public ColorPreviewUpdateHandlerV1(SeekBar sbarRed_SeekBar, SeekBar sbarGreen_SeekBar, SeekBar sbarBlue_SeekBar, SeekBar sbarAlpha_SeekBar, ImageView imgViewColors_ImageView, LinearLayout fragment) {
        SbarRed_SeekBar = sbarRed_SeekBar;
        SbarGreen_SeekBar = sbarGreen_SeekBar;
        SbarBlue_SeekBar = sbarBlue_SeekBar;
        SbarAlpha_SeekBar = sbarAlpha_SeekBar;
        ImgViewColors_ImageView = imgViewColors_ImageView;
        this.fragment = fragment;
    }





    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
        int color = Color.argb(SbarAlpha_SeekBar.getProgress(), SbarRed_SeekBar.getProgress(), SbarGreen_SeekBar.getProgress(), SbarBlue_SeekBar.getProgress());
        ImgViewColors_ImageView.setBackgroundColor(color);
        fragment.setBackgroundColor(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
