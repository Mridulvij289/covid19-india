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

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19india.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StateFragment extends Fragment {
 //   Button d;
    RecyclerView rvCovidState;
    ProgressBar progressBar;
    private static final String TAG= StateFragment.class.getSimpleName();

    ArrayList<covidState> covidStates;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_states, container, false);


        rvCovidState=root.findViewById(R.id.rvcovidstate);
        progressBar=root.findViewById(R.id.progress_circular_state);
       rvCovidState.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvCovidState.getContext(),DividerItemDecoration.VERTICAL);
       dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line_divider));
        rvCovidState.addItemDecoration(dividerItemDecoration);
    //    d.setOnClickListener(new View.OnClickListener(){
        //    @Override
       //     public void onClick(View v){

        //    }
     //   });
        //call volley method
        getDataFromServer();
        return root;
    }
   private void showRecyclerView(){
       CovidStateAdapter  covidStateAdapter=new CovidStateAdapter(covidStates);
        rvCovidState.setAdapter(covidStateAdapter);
        ItemClickSupport.addTo(rvCovidState).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
           public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidState(covidStates.get(position));
           }
  });
    }

    private void showSelectedCovidState(covidState covidState){

        DistrictFragment districtFragment=new DistrictFragment();
        Bundle args =new Bundle();
        args.putParcelable("State",covidState);
        districtFragment.setArguments(args);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.statefragment,districtFragment);
        transaction.commit();
       // d.setVisibility(View.GONE);
           // Intent CovidD = new Intent(this.getActivity(), covidstatesdetails.class);
         //   CovidD.putExtra("EXTRA COVID", (Parcelable) covidState);
         //   startActivity(CovidD);


    }
    private void getDataFromServer() {
        String url ="https://api.covid19india.org/data.json";
      covidStates=new ArrayList<>();
        JsonObjectRequest stringRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    progressBar.setVisibility(View.GONE);


                    try {

                        JSONArray jsonarray = response.getJSONArray("statewise");
                        for (int i = 1; i < jsonarray.length(); i++) {

                            JSONObject statewise=jsonarray.getJSONObject(i);

                            String state=statewise.getString("state");
                            String cases=statewise.getString("confirmed");
                            String deaths=statewise.getString("deaths");
                            String recovered=statewise.getString("recovered");
                            String Tcases="+"+statewise.getString("deltaconfirmed");
                            String Tdeaths="+"+statewise.getString("deltadeaths");
                            String Trecovered="+"+statewise.getString("deltarecovered");
                            String Active=statewise.getString("active");
                            String Code=statewise.getString("statecode");

                            covidStates.add(new covidState(state, cases,recovered,deaths,Tcases,Tdeaths,Trecovered,Active,Code));

                        }
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                     }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG,"onResponse "+error);
            }
        });

        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }

}
