package ca.qc.johnabbott.cs.cs616.colorpicker;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View root;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return root = inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void setCircleColor(int id, int color) {
        CircleView circleView = (CircleView) root.findViewById(id);
        circleView.setColor(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
    }
}
