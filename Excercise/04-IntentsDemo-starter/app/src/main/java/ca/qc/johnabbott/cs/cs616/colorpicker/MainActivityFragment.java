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

    public interface OnCircleChosen {
        void OnCircleChosen(int num, int currentColor);
    }

    private View root;
    private int[] circleIds;
    private OnCircleChosen listener;

    public MainActivityFragment() {
        listener = null;
    }

    public void setOnCircleChosen(OnCircleChosen listener)
    {
        this.listener = listener;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        circleIds = new int[] {R.id.circle1, R.id.circle2, R.id.circle3, R.id.circle4, R.id.circle5, R.id.circle6};

        for (int i = 0; i < circleIds.length; i++)
        {
            final CircleView circleView = (CircleView) root.findViewById(circleIds[i]);

            final int j = i;
            circleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null)
                        listener.OnCircleChosen(j, circleView.getColor());
                }
            });
        }

        return root;
    }

    public void setCircleColor(int num, int color) {
        CircleView circleView = (CircleView) root.findViewById(circleIds[num]);
        circleView.setColor(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
    }
}
