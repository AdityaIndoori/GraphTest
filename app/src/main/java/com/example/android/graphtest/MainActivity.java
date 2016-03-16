package com.example.android.graphtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> line_series;
    double l,t=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GraphView linegraph = (GraphView)findViewById(R.id.graph);
        line_series = new LineGraphSeries<DataPoint>(new DataPoint[] {

        });
        linegraph.addSeries(line_series);
        line_series.setDrawDataPoints(true);
        linegraph.getViewport().setScalable(true);
        linegraph.getViewport().setScrollable(true);

        line_series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(MainActivity.this, "Series: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clear(View view)
    {   GraphView linegraph =  (GraphView)findViewById(R.id.graph);
        linegraph.removeAllSeries();
        line_series = new LineGraphSeries<DataPoint>(new DataPoint[] {

        });
        t=0;
    }

    public void writecor(View view)
    {
        GraphView linegraph =  (GraphView)findViewById(R.id.graph);
        EditText xc,yc;
        double xv,yv;
        xc=(EditText)findViewById(R.id.xcor);
        yc=(EditText)findViewById(R.id.ycor);
        xv=Double.parseDouble(xc.getText().toString());
        yv=Double.parseDouble(yc.getText().toString());
        if(t==0)
        {
            l=xv;
            line_series.appendData(new DataPoint(xv, yv), true, 10);
            linegraph.addSeries(line_series);
            t++;
        }
        else if (t>0)
        {
            if(xv<l)
                Toast.makeText(MainActivity.this, "Cannot plot for a value lesser than x=" + l, Toast.LENGTH_SHORT).show();
            else
            {
                l=xv;
                line_series.appendData(new DataPoint(xv, yv), true, 10);
                linegraph.addSeries(line_series);
                t++;
            }
        }

    }


}
