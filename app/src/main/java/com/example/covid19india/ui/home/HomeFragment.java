package com.example.covid19india.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19india.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnChartValueSelectedListener {
    ArrayList<Entry> y= new ArrayList<>();
    ArrayList<Entry> y1= new ArrayList<>();
    ArrayList<Entry> y2= new ArrayList<>();
    ArrayList<String> x= new ArrayList<>();
    LineChart mChart;
    private TextView tvTotalconfirmed,tvTotalDeaths, tvTotalRecovred,updatedate,TvTodayconfirmed,TvTodayDeaths,TvTodayRecovred;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,
                container, false);

        tvTotalconfirmed= root.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths= root.findViewById(R.id.tvTotalDeath);
        tvTotalRecovred=root.findViewById(R.id.tvTotalRecovered);
        TvTodayconfirmed= root.findViewById(R.id.tvtodayConfirmed);
        TvTodayDeaths= root.findViewById(R.id.tvtodayDeath);
        TvTodayRecovred=root.findViewById(R.id.tvtodayRecovered);
        updatedate=root.findViewById(R.id.updatedate);
        progressBar= root.findViewById(R.id.progress_circular);
        mChart = (LineChart) root.findViewById(R.id.line);
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
        mChart.getXAxis().setAxisMinValue(10f);
        getData();
        return root;
    }


    private void getData(){
         RequestQueue queue= Volley.newRequestQueue(getActivity());
        String url ="https://api.covid19india.org/data.json";

        JsonObjectRequest stringRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);
                try {

                    JSONArray jsonarray = response.getJSONArray("statewise");
                    JSONArray jsonarray1 = response.getJSONArray("cases_time_series");
                    JSONObject statewise = jsonarray.getJSONObject(0);
                    tvTotalconfirmed.setText(statewise.getString("confirmed"));
                    tvTotalRecovred.setText(statewise.getString("recovered"));
                    tvTotalDeaths.setText(statewise.getString("deaths"));
                    TvTodayconfirmed.setText(statewise.getString("deltaconfirmed"));
                    TvTodayRecovred.setText(statewise.getString("deltarecovered"));
                    TvTodayDeaths.setText(statewise.getString("deltadeaths"));
                    updatedate.setText("Last Updated"+"\n"+statewise.getString("lastupdatedtime"));

                    for(int i=0;i<jsonarray1.length();i++) {

                    JSONObject casestime=jsonarray1.getJSONObject(i);

                        int value = casestime.getInt("totaldeceased");
                        int a=casestime.getInt("totalrecovered");
                        int m=casestime.getInt("totalconfirmed");
                        String date = casestime.getString("date");
                        y.add(new Entry(m,i));
                        y1.add(new Entry(a,i));
                        y2.add(new Entry(value,i));
                        x.add(i,date);

                    }
                    String[] xaxes=new String[x.size()];
                    for(int i=0;i<x.size();i++){
                        xaxes[i]=x.get(i).toString();
                    }
                    ArrayList<ILineDataSet> lineDataSets =new ArrayList<>();
                    LineDataSet lineDataSet1 = new LineDataSet(y2,"Deaths");
                    lineDataSet1.setDrawCircles(false);
                    lineDataSet1.setColor(Color.LTGRAY);
                    LineDataSet lineDataSet2 =new LineDataSet(y1,"Recovered");
                    lineDataSet2.setDrawCircles(false);
                    lineDataSet2.setColor(Color.GREEN);
                    LineDataSet lineDataSet3 =new LineDataSet(y,"Confirmed");
                    lineDataSet3.setDrawCircles(false);
                    lineDataSet3.setColor(Color.RED);

                    lineDataSets.add(lineDataSet1);
                    lineDataSets.add(lineDataSet2);
                    lineDataSets.add(lineDataSet3);
                    mChart.setDrawGridBackground(false);
                    mChart.setDrawBorders(false);
                    mChart.setData(new LineData(x,lineDataSets));
                    mChart.invalidate();
                    //mChart.setVisibleXRangeMinimum(20f);
                    mChart.setScaleMinima(0f, 1f);
                  // mChart.
                    mChart.setVisibleXRangeMaximum(65f);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response",error.toString());
            }
    });
        queue.add(stringRequest);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
