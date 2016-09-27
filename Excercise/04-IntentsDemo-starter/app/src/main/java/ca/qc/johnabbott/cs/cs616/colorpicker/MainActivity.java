package ca.qc.johnabbott.cs.cs616.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_test) {



            Intent intent = new Intent(this, ColorPickerActivity.class);
            intent.putExtra(ColorPickerActivity.param.inition_color, Color.TRANSPARENT);

            startActivityForResult(intent,1);   //second param is the request code. DO NOT USE -1



            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK)
        {
            int color = data.getIntExtra(ColorPickerActivity.result.chosen_color, Color.TRANSPARENT);   //transparent should never be set

            MainActivityFragment frag = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.main_Fragment);
            frag.setCircleColor(R.id.circle1, color);
            frag.setCircleColor(R.id.circle2, color);
            frag.setCircleColor(R.id.circle3, color);
            frag.setCircleColor(R.id.circle4, color);
            frag.setCircleColor(R.id.circle5, color);
            frag.setCircleColor(R.id.circle6, color);



        }

    }
}
