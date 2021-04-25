/*
 * Copyright 2021 MRIDUL VIJ. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.covid19india.ui.states;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19india.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Location extends Fragment implements LocationListener {
    Button button;
    TextView textview,hotspot,hotspotcolor, text1,text2,districtcases,texta,textdistrict,districtdeaths,districtrecovered,districtactive,districttcases,districttdeaths,districttrecovered,districttactive;
    LocationManager locationManager;
    LinearLayout l1,l2;
    ProgressBar progressbar;
    String myCity;
    private static final String TAG= Location.class.getSimpleName();
    ArrayList<CovidDistrict> CovidDistricts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.location, container, false);
        textview = root.findViewById(R.id.TextLatLog);
        text1 = root.findViewById(R.id.textAddress1);
        text2 = root.findViewById(R.id.textAddress);
        hotspot=root.findViewById(R.id.hotspot);
        hotspotcolor=root.findViewById(R.id.hotspotcolor);
        button = root.findViewById(R.id.button_location);
        districtcases=root.findViewById(R.id.districtcases);
        districtdeaths=root.findViewById(R.id.districtdeaths);
        districtrecovered=root.findViewById(R.id.districtrecovered);
        districtactive=root.findViewById(R.id.districtactive);
        districttcases=root.findViewById(R.id.districttcases);
        districttdeaths=root.findViewById(R.id.districttdeaths);
        districttrecovered=root.findViewById(R.id.districttrecovered);
        progressbar=root.findViewById(R.id.progress_circular_location);
        texta=root.findViewById(R.id.textA);
        texta.setVisibility(View.GONE);
        textdistrict=root.findViewById(R.id.districtname);
        textdistrict.setVisibility(View.GONE);
        l1=root.findViewById(R.id.linearcases);
        l1.setVisibility(View.GONE);
        l2=root.findViewById(R.id.lineartoday);
        l2.setVisibility(View.GONE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT > 23) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    }
                    android.location.Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(location!=null) {
                        progressbar.setVisibility(View.GONE);
                        onLocationChanged(location);


                    }


                }else textview.setText("Location Unavailable");


            }
        });


    return root;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        double longitude=location.getLongitude();
        double latitude=location.getLatitude();
        textview.setText("longitude: "+longitude+"  Latitide: "+latitude);
        getCityName(longitude,latitude);
        if(myCity.equalsIgnoreCase("Gautam Buddh Nagar"))
        {
            myCity="Gautam Buddha Nagar";
        }
        text1.setText("city name: "+myCity);
        getDataFromServer();

    }

    private void getCityName(double longitude, double latitude) {

        texta.setVisibility(View.VISIBLE);
        Geocoder geocoder=new Geocoder(this.getActivity(), Locale.getDefault());
        try {
            List<Address> addresses=geocoder.getFromLocation(latitude,longitude,1);
            String address =addresses.get(0).getAddressLine(0);
            myCity= addresses.get(0).getSubAdminArea();
            Log.d("myLog","Complete Address "+addresses.toString());
            Log.d("myLog","Address: "+address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hotspot(final String district){
        hotspotcolor.setVisibility(View.VISIBLE);
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

                        if (districtwise.getString("district").equalsIgnoreCase(d)) {
                            zone = districtwise.getString("zone");
                            if (zone.equalsIgnoreCase("Red")) {
                                hotspot.setText("Red Zone");
                                hotspotcolor.setBackgroundColor(Color.parseColor("#560000"));
                                textdistrict.setTextColor(Color.parseColor("#560000"));
                            } else if (zone.equalsIgnoreCase("Green")) {
                                hotspot.setText("Green Zone");
                                hotspotcolor.setBackgroundColor(Color.parseColor("#005600"));
                                textdistrict.setTextColor(Color.parseColor("#005600"));
                            } else if (zone.equalsIgnoreCase("Orange")) {
                                hotspot.setText("Orange Zone");
                                hotspotcolor.setBackgroundColor(Color.parseColor("#562e00"));
                                textdistrict.setTextColor(Color.parseColor("#562e00"));
                            }
                        }
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

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void getDataFromServer() {
        CovidDistricts = new ArrayList<>();
        String url = "https://api.covid19india.org/v2/state_district_wise.json";
            progressbar.setVisibility(View.GONE);

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                try {
                    String district, cases, recovered, deaths, todaycases, tdeaths, trecovered, active, state;
                    for (int j = 0; j < response.length(); j++) {

                        JSONObject data = response.getJSONObject(j);
                        state=data.getString("state");
                        JSONArray jsonarray1 = data.getJSONArray("districtData");
                        for (int i = 0; i < jsonarray1.length(); i++) {
                            JSONObject data1 = jsonarray1.getJSONObject(i);
                            district = data1.getString("district");

                            if (myCity.equalsIgnoreCase(district)) {
                                hotspot(district);
                                cases = data1.getString("confirmed");
                                recovered = data1.getString("recovered");
                                deaths = data1.getString("deceased");
                                active = data1.getString("active");

                                JSONObject data2 = data1.getJSONObject("delta");
                                todaycases =  data2.getString("confirmed");
                                tdeaths =  data2.getString("deceased");
                                trecovered = data2.getString("recovered");

                                textdistrict.setVisibility(View.VISIBLE);
                                l1.setVisibility(View.VISIBLE);
                                l2.setVisibility(View.VISIBLE);
                                text2.setText("Belongs to: "+state);
                                textdistrict.setText(district);
                                districtcases.setText(cases);
                                districtdeaths.setText(deaths);
                                districtactive.setText(active);
                                districtrecovered.setText(recovered);
                                districttcases.setText(todaycases);
                                districttdeaths.setText(tdeaths);
                                districttrecovered.setText(trecovered);


                            } else {
                                textdistrict.setVisibility(View.VISIBLE);
                                l1.setVisibility(View.VISIBLE);
                                l1.setVisibility(View.VISIBLE);
                            }

                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse " + error);
            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
