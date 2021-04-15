package com.example.covid19india.ui.states;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19india.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class stategraph extends AppCompatActivity {
  //  LineChart mChart;
    BarChart chart;
    TextView text;
     covidState covidState;
    ArrayList confirmed = new ArrayList();
    ArrayList date = new ArrayList();
    Intent intent;
    private static final String TAG= stategraph.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stategraph);
        chart = findViewById(R.id.barchart);
        //text=findViewById(R.id.t);
        Bundle extras = getIntent().getExtras();
        chart.getLegend().setTextColor(Color.WHITE);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12);
        xAxis.setAxisMinValue(120f);
        xAxis.setAxisLineColor(Color.WHITE);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setTextSize(12);
        leftAxis.setAxisMinValue(100f);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularity(2);
        leftAxis.setLabelCount(8, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
     /*   mChart = (LineChart) findViewById(R.id.line1);
        mChart.getAxisLeft().setTextColor(Color.WHITE);
        mChart.getAxisRight().setTextColor(Color.WHITE);
        mChart.getXAxis().setTextColor(Color.WHITE);
        mChart.getLegend().setTextColor(Color.WHITE);
        mChart.getXAxis().setTextSize(14);
        mChart.getAxisLeft().setTextSize(14);
        mChart.getAxisRight().setTextSize(14);
        mChart.getLegend().setTextSize(14);
        mChart.getAxisLeft().setAxisMinValue(0f);
        mChart.getAxisRight().setAxisMinValue(0f);
        mChart.getXAxis().setAxisMinValue(10f);*/
     //   if (extras != null) {
            covidState = extras.getParcelable("EXTRA CODE");
      //  }
        String code=covidState.getmCode().trim().toLowerCase();
        getData(code);
    }

    private void getData(final String code) {
        String url ="https://api.covid19india.org/states_daily.json";

        JsonObjectRequest stringRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    String c=code.trim().toLowerCase();
                    JSONArray jsonarray = response.getJSONArray("states_daily");
                    for (int i = 1; i < jsonarray.length(); i++) {

                        JSONObject statewise = jsonarray.getJSONObject(i);
                        if(statewise.getString("status").equalsIgnoreCase("Confirmed")) {
                            int cases = statewise.getInt(c);
                            String dat=statewise.getString("date");
                            confirmed.add(new BarEntry(cases, i));
                            date.add(dat);

                        }
                        BarDataSet bardataset = new BarDataSet(confirmed, "Confirmed");
                        chart.animateY(5000);
                        BarData data = new BarData(date, bardataset);
                        bardataset.setValueTextSize(18f);
                        bardataset.setColor(Color.RED);
                        chart.setData(data);
                        chart.setScrollBarSize(20);
                        chart.setNoDataTextDescription("cases");



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG,"onResponse "+error);
        }
    });

        Volley.newRequestQueue(this).add(stringRequest);
    }


}
