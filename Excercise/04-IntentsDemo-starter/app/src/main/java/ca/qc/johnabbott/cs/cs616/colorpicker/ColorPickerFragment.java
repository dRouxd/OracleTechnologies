package ca.qc.johnabbott.cs.cs616.colorpicker;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class ColorPickerFragment extends Fragment {

    // UI components
    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private SeekBar alpha;
    private ImageView preview;

    /**
     *
     */
    public ColorPickerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // capture the root of the inflated layout
        View root = inflater.inflate(R.layout.fragment_color_picker, container, false);

        // find UI components
        red = (SeekBar) root.findViewById(R.id.red_SeekBar);
        green = (SeekBar) root.findViewById(R.id.green_SeekBar);
        blue = (SeekBar) root.findViewById(R.id.blue_SeekBar);
        alpha = (SeekBar) root.findViewById(R.id.alpha_SeekBar);
        preview = (ImageView) root.findViewById(R.id.preview_ImageView);


        // common event handler for all seekbars
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

        // all Seekbar's have the same handler
        red.setOnSeekBarChangeListener(handler);
        green.setOnSeekBarChangeListener(handler);
        blue.setOnSeekBarChangeListener(handler);
        alpha.setOnSeekBarChangeListener(handler);

        return root;
    }

    /**
     * Update the preview image with state of the Seekbars
     */
    private void updatePreview() {
        // convert components to Android color integer
        int color = Color.argb(
                alpha.getProgress(),
                red.getProgress(),
                green.getProgress(),
                blue.getProgress()
        );

        // update preview
        preview.setBackgroundColor(color);
    }


    public void loadColor(int color) {
        alpha.setProgress(Color.alpha(color));
        red.setProgress(Color.red(color));
        green.setProgress(Color.green(color));
        blue.setProgress(Color.blue(color));
        updatePreview();
    }

    public int getColor() {
        return Color.argb(
                alpha.getProgress(),
                red.getProgress(),
                green.getProgress(),
                blue.getProgress()
        );
    }
}
