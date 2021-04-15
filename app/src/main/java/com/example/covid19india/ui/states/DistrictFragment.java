package com.example.covid19india.ui.states;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19india.R;
import com.github.mikephil.charting.charts.BarChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DistrictFragment extends Fragment {

    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    LinearLayout l1;
    ArrayList noofzone = new ArrayList();
    ArrayList zo = new ArrayList();
    BarChart chart;
    int r=0,g=0,o=0;
    RecyclerView rvCovidDistrict;
    ProgressBar progressBar;
    Context context;

   // ArrayList<String> noidacaseday=new ArrayList<>();
   // static final String FILE1="noidaeachday.txt"; /storage/1716-3D0B/Android/data/com.example.conid19india.com/file.ext
    static final String FILE1="noidaeachday.txt";
    static final String FILE2="agraeachday.txt";
    static final String FILE3="prayagrajeachday.txt";
    static final String FILE4="ghaziabadeachday.txt";
    static final String FILE5="ayodhyaeachday.txt";
    static final String FILE6="westdelhieachday.txt";
    static final String FILE7="southdelhieachday.txt";
    static final String FILE8="centraldelhieachday.txt";
    static final String FILE9="eastdelhieachday.txt";
    static final String FILE10="newdelhieachday.txt";
    static final String FILE11="northdelhieachday.txt";
    static final String FILE12="northeastdelhieachday.txt";
    static final String FILE13="northwestdelhieachday.txt";
    static final String FILE14="southeastdelhieachday.txt";
    static final String FILE15="southwestdelhieachday.txt";
    static final String FILE16="shahdaraeachday.txt";
    static final String FILE17="varanasieachday.txt";
    static final String FILE18="sultanpureachday.txt";
    static final String FILE19="meeruteachday.txt";


    TextView tvDetailStateName,tvDetailTotalCases,tvDetailTodayCases,tvDetailTotalRecovered,tvDetailTodayRecovered,tvDetailTotalDeaths,tvDetailTodayDeaths,tvDetailTotalActive,tvDetailTodayActive;
    private static final String TAG= DistrictFragment.class.getSimpleName();
    Button b;
    ArrayList<CovidDistrict> CovidDistricts;


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_district,container,false);

          calendar= Calendar.getInstance();
          dateFormat=new SimpleDateFormat("dd/MM");
          date=dateFormat.format(calendar.getTime());
      //  int currentday =calendar.get(Calendar.DAY_OF_MONTH);
     //   SharedPreferences settings =getActivity().getSharedPreferences("PREFS",0);

        l1=root.findViewById(R.id.hotspo);
        context=root.getContext();
        //chart = root.findViewById(R.id.barchart1);
        tvDetailStateName=root.findViewById(R.id.detailstatename);
        tvDetailTotalCases=root.findViewById(R.id.detailtotalcases);
        tvDetailTodayCases=root.findViewById(R.id.detailtodaycases);
        tvDetailTotalRecovered=root.findViewById(R.id.detailtotalrecovered);
        tvDetailTodayRecovered=root.findViewById(R.id.detailtodayrecovered);
        tvDetailTotalActive=root.findViewById(R.id.detailtotalactive);
        tvDetailTodayActive=root.findViewById(R.id.detailCode);
        tvDetailTotalDeaths=root.findViewById(R.id.detailtotaldeaths);
        tvDetailTodayDeaths=root.findViewById(R.id.detailtodaydeaths);

        final covidState covidState =getArguments().getParcelable("State");

        tvDetailStateName.setText(covidState.getmCovidState());
        tvDetailTotalCases.setText(covidState.getmCases());
        tvDetailTodayCases.setText(covidState.getmTCases());
        tvDetailTotalRecovered.setText(covidState.getmRecovered());
        tvDetailTodayRecovered.setText(covidState.getmTRecovered());
        tvDetailTotalActive.setText(covidState.getmActive());
        tvDetailTodayActive.setText(covidState.getmCode());
        tvDetailTotalDeaths.setText(covidState.getmDeaths());
        tvDetailTodayDeaths.setText(covidState.getmTDeaths());
      /*  chart.getLegend().setTextColor(Color.WHITE);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12);
       // xAxis.setAxisMinValue(0f);
        xAxis.setAxisLineColor(Color.WHITE);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setTextSize(12);
       // leftAxis.setAxisMinValue(1f);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
       // leftAxis.setGranularity(2);
       // leftAxis.setLabelCount(8, true);
      //  leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        b=root.findViewById(R.id.buttoncharts);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getActivity(), stategraph.class);
                I.putExtra("EXTRA CODE", (Parcelable) covidState);
                getActivity().startActivity(I);
                getActivity().finish();
            }
        });
*/
        rvCovidDistrict=root.findViewById(R.id.rvcoviddistrict);
        progressBar = root.findViewById(R.id.progress_circular_District);
        rvCovidDistrict.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvCovidDistrict.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line_divider));
        rvCovidDistrict.addItemDecoration(dividerItemDecoration);

        //save("300 "+date);

        getDataFromServer();
      //  load();

        return root;
    }


   public void load(String FILENAME,String district,String cases,String deaths,String recoverd,String tdeaths,String trecovered,String todaycases){

       int[] ch =null;
        File myFile;
        l1.setVisibility(View.VISIBLE);
        FileInputStream fis=null;
        int n;
        try {
           // myFile = new File("/sdcard/Covid19India/" + FILENAME);
                myFile = new File("/storage/emulated/0/Android/data/com.example.covid19india.com/Files/" + FILENAME);
                fis = new FileInputStream(myFile);

                // fis = getActivity().openFileInput(FILENAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String t1, text;
                n = Integer.parseInt(br.readLine());
                ch = new int[n];
                int j, coun = 0, count = 0, i = n - 1, count1 = 0, count2 = 0, countcase2 = 0;
                String c;
                while ((text = br.readLine()) != null) {
                    t1 = text.substring(0, text.indexOf(" "));
                    ch[i] = Integer.parseInt(t1.trim());
                    //sb.append(ch[i]).append("\n");
                    //count++;
                    i--;
                }
                if (n > 14) {
                    // textview.setText(sb);
                    for (j = 14; j >= 0; j--) {
                        if (ch[j] == 0) {
                            count1++;

                        }
                    }
                    for (j = 28; j >= 0; j--) {
                        if (ch[j] == 0) {
                            count2++;


                        } else {
                            coun = ch[j];
                            countcase2 += ch[j];
                            count = ch[j];
                        }
                    }
                    if (count2 >= 28 || (count1 < 14 && countcase2 < 3 && coun < 3)) {
                        CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#005600"));
                    } else if ((count1 >= 14 && count1 < 28) || (count < 10 && count1 < 14 && countcase2 < 35 && !district.endsWith("Delhi") && !district.equalsIgnoreCase("shahdara"))) {
                        CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#562e00"));
                    } else if (count1 < 14) {
                        CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#560000"));
                    }
                } else CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, ""));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void incsave( String n,StringBuilder sb,File FILENAME){

        FileOutputStream fOut = null;
        String ch=String.valueOf(sb);
        try {
           // fOut = getActivity().openFileOutput(FILENAME,Context.MODE_PRIVATE);
            fOut=new FileOutputStream(FILENAME,false);
            fOut.write(n.getBytes());
            fOut.write(System.getProperty("line.separator").getBytes());
            fOut.write(ch.getBytes());
         //   Toast.makeText(this.getActivity(),n+" saved to"+ getActivity().getFilesDir()+"/"+ FILENAME, Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void save(String cases,String FILENAME) {
        FileOutputStream fos=null;
        FileInputStream fis1=null;
        File myFile;
        byte flag=0;
        try {
          //  myFile = new File("/sdcard/Covid19India/" + FILENAME);
            myFile = new File("/storage/emulated/0/Android/data/com.example.covid19india.com/Files/" + FILENAME);
            fis1= new FileInputStream(myFile);
            //fis1= getActivity().openFileInput(FILENAME);
          //  fis1= getActivity().openFileInput("/storage/1716-3D0B/Android/data/com.example.conid19india.com/file.ext");
            InputStreamReader isr= new InputStreamReader(fis1);
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String t,t1;
            //int a;
            String text=br.readLine();
            String n=String.valueOf(Integer.parseInt(text)+1);
            while((t= br.readLine())!=null){

                sb.append(t).append("\n");
                if(t.endsWith(date))
                {
                    flag=1;
                }
                //a=t.length();
                //t1=t.substring(t.indexOf(" "),a);

            }
            if(flag==0)
            {
                incsave(n,sb,myFile);
             //   Log.e("abc","error");
              //  fos =getActivity().openFileOutput(FILENAME, Context.MODE_APPEND);
                fos=new FileOutputStream(myFile,true);

                fos.write(cases.getBytes());
                fos.write(System.getProperty("line.separator").getBytes());
                Toast.makeText(this.getActivity()," saved to"+ getActivity().getFilesDir()+"/"+ FILENAME, Toast.LENGTH_LONG).show();

            }
            else if(flag==1)
            {
                compsave(cases,myFile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null){
                try {
                    fos.close();
               } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void compsave(String cases,File FILENAME) {

        FileInputStream fis=null;
        FileOutputStream fos=null;

        try {
          //  fis=getActivity().openFileInput(FILENAME);
            fis=new FileInputStream(FILENAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String t;

            while((t= br.readLine())!=null) {

                sb.append(t).append("\n");
            }
            if(sb.length()>0) {
                int last, prev = sb.length() - 1;
                while ((last = sb.lastIndexOf("\n", prev)) == prev) {
                    prev = last - 1;
                }
                if (last >= 0) {
                    sb.delete(last, sb.length());
                }
            }
            String ch=String.valueOf(sb);
            //fos=getActivity().openFileOutput(FILENAME,Context.MODE_PRIVATE);
            fos=new FileOutputStream(FILENAME,false);
            fos.write(ch.getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
            fos.write(cases.getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
          //  Toast.makeText(this.getActivity()," saved to"+ getActivity().getFilesDir()+"/"+ FILENAME, Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int hotspot(final String district, final String cases, final String deaths, final String recoverd, final String tdeaths, final String trecovered, final String todaycases){

        String url ="https://api.covid19india.org/zones.json";
        l1.setVisibility(View.VISIBLE);
        JsonObjectRequest stringRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String zone;
                    String d=district;
                    JSONArray jsonarray = response.getJSONArray("zones");
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject districtwise = jsonarray.getJSONObject(i);
                        if(d.equalsIgnoreCase(districtwise.getString("district"))) {
                            zone = districtwise.getString("zone");
                            if (zone.equalsIgnoreCase("Red")) {
                                CovidDistrict.r++;
                                CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#560000"));

                            } else if (zone.equalsIgnoreCase("Green")) {
                                CovidDistrict.g++;
                                CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#005600"));

                            } else if (zone.equalsIgnoreCase("Orange")) {
                                CovidDistrict.o++;
                                CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#562e00"));

                            }
                        }



                    }

                    showRecyclerView();
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

        Volley.newRequestQueue(getActivity()).add(stringRequest);

        return 1;
    }


   private void showRecyclerView() {
       CovidDistrictAdapter covidDistrictAdapter = new CovidDistrictAdapter(CovidDistricts);
       rvCovidDistrict.setAdapter(covidDistrictAdapter);

   }
    private void getDataFromServer() {

        String url = "https://api.covid19india.org/v2/state_district_wise.json";
        CovidDistricts = new ArrayList<>();

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            // String a = getIntent().getStringExtra("EXTRA COVID");
            // String a=covidstate.getmCovidState();
            @Override
            public void onResponse(JSONArray response) {
                covidState covidState = getArguments().getParcelable("State");
                String value = covidState.getmCovidState();
                progressBar.setVisibility(View.GONE);

                try {


                    for (int j = 0; j < response.length(); j++) {

                        JSONObject data = response.getJSONObject(j);


                        // state = data.getString("state");
                        if (data.getString("state").equalsIgnoreCase(value)) {


                            JSONArray jsonarray1 = data.getJSONArray("districtData");
                            for (int i = 0; i < jsonarray1.length(); i++) {
                                JSONObject data1 = jsonarray1.getJSONObject(i);
                                String district = data1.getString("district");
                                String cases = data1.getString("confirmed");
                                String recoverd = data1.getString("recovered");
                                String deaths = data1.getString("deceased");

                                JSONObject data2 = data1.getJSONObject("delta");
                                String todaycases = "+" + data2.getString("confirmed");
                                String tdeaths = "+" + data2.getString("deceased");
                                String trecovered = "+" + data2.getString("recovered");
                          /*      if (district.equalsIgnoreCase("Gautam Buddha Nagar"))
                                {
                                    save(data2.getString("confirmed")+" "+date,FILE1);
                                    load(FILE1, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                }
                                else if(district.equalsIgnoreCase("Agra")) {
                                    save(data2.getString("confirmed") + " " + date, FILE2);
                                    load(FILE2, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Prayagraj")) {
                                    save(data2.getString("confirmed") + " " + date, FILE3);
                                    load(FILE3, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Ghaziabad")) {
                                    save(data2.getString("confirmed") + " " + date, FILE4);
                                    load(FILE4, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("New Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE10);
                                    load(FILE10, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("West Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE6);
                                    load(FILE6, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("South Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE7);
                                    load(FILE7, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("East Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE9);
                                    load(FILE9, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("North Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE11);
                                    load(FILE11, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("South East Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE14);
                                    load(FILE14, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("South West Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE15);
                                    load(FILE15, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("North East Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE12);
                                    load(FILE12, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("North West Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE13);
                                    load(FILE13, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Central Delhi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE8);
                                    load(FILE8, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Shahdara")) {
                                    save(data2.getString("confirmed") + " " + date, FILE16);
                                    load(FILE16, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Varanasi")) {
                                    save(data2.getString("confirmed") + " " + date, FILE17);
                                    load(FILE17, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Sultanpur")) {
                                    save(data2.getString("confirmed") + " " + date, FILE18);
                                    load(FILE18, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Meerut")) {
                                    save(data2.getString("confirmed") + " " + date, FILE19);
                                    load(FILE19, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                } else if(district.equalsIgnoreCase("Ayodhya")) {
                                    save(data2.getString("confirmed") + " " + date, FILE5);
                                    load(FILE5, district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);
                                }else CovidDistricts.add(new CovidDistrict(district, cases,deaths,recoverd,tdeaths,trecovered,todaycases,"#08012C"));
*/                          //    String zone=hotspot(district);
                                if (district.equalsIgnoreCase("Unknown") || district.equalsIgnoreCase("Other State")) {
                                    CovidDistricts.add(new CovidDistrict(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases, "#08012C"));

                                } else {
                                    hotspot(district, cases, deaths, recoverd, tdeaths, trecovered, todaycases);

                                }


                                //  CovidDistricts.add(new CovidDistrict(district, cases,deaths,recoverd,tdeaths,trecovered,todaycases,"#08012C"));


                            }
                        }

                    }
                    showRecyclerView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse " + error);
            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
     /*   float re=CovidDistrict.r;
        float ge=CovidDistrict.g;
        float oe=CovidDistrict.o;
        noofzone.add(new BarEntry(re, 0));
        noofzone.add(new BarEntry(ge, 1));
        noofzone.add(new BarEntry(oe, 2));
        zo.add("Red");
        zo.add("Green");
        zo.add("Orange");
        BarDataSet bardataset = new BarDataSet(noofzone, "Red Zone");
        bardataset.setBarSpacePercent(600f);
        bardataset.setValueTextSize(10f);
        bardataset.setValueTextColor(Color.WHITE);
        chart.animateY(2000);
        BarData da = new BarData(zo, bardataset);
        // da.setGroupSpace(0.5f);
        bardataset.setColor(Color.RED);
        chart.setData(da);
*/

    }

}
