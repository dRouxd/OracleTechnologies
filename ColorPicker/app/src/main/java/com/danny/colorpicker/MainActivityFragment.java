package com.danny.colorpicker;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private SeekBar SbarRed_SeekBar;
    private SeekBar SbarGreen_SeekBar;
    private SeekBar SbarBlue_SeekBar;
    private SeekBar SbarAlpha_SeekBar;
    private ImageView ImgViewColors_ImageView;

    private LinearLayout fragment;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        SbarRed_SeekBar = (SeekBar) root.findViewById(R.id.SbarRed_SeekBar);
        SbarGreen_SeekBar = (SeekBar) root.findViewById(R.id.SbarGreen_SeekBar);
        SbarBlue_SeekBar = (SeekBar) root.findViewById(R.id.SbarBlue_SeekBar);
        SbarAlpha_SeekBar = (SeekBar) root.findViewById(R.id.SbarAlpha_SeekBar);

        fragment = (LinearLayout) root.findViewById(R.id.fragment);

        ImgViewColors_ImageView = (ImageView) root.findViewById(R.id.ImgViewColors_ImageVIew);


        //version 2
        /*ColorPreviewUpdateHandlerV2 updateHandler = new ColorPreviewUpdateHandlerV2();

        SbarRed_SeekBar.setOnSeekBarChangeListener(updateHandler);
        SbarGreen_SeekBar.setOnSeekBarChangeListener(updateHandler);
        SbarBlue_SeekBar.setOnSeekBarChangeListener(updateHandler);
        SbarAlpha_SeekBar.setOnSeekBarChangeListener(updateHandler);*/

        //version 3 - anonymous inner class

        SeekBar.OnSeekBarChangeListener handler = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatePreview();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        SbarRed_SeekBar.setOnSeekBarChangeListener(handler);
        SbarGreen_SeekBar.setOnSeekBarChangeListener(handler);
        SbarBlue_SeekBar.setOnSeekBarChangeListener(handler);
        SbarAlpha_SeekBar.setOnSeekBarChangeListener(handler);



        return root;
    }

    private class ColorPreviewUpdateHandlerV2 implements SeekBar.OnSeekBarChangeListener
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b){ updatePreview(); }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar){}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar){}
    }


    private void updatePreview()
    {
        int color = Color.argb(SbarAlpha_SeekBar.getProgress(), SbarRed_SeekBar.getProgress(), SbarGreen_SeekBar.getProgress(), SbarBlue_SeekBar.getProgress());
        ImgViewColors_ImageView.setBackgroundColor(color);
        fragment.setBackgroundColor(color);
    }
}
