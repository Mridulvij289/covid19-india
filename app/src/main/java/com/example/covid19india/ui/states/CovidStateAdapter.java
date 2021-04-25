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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19india.R;

import java.util.ArrayList;

public class CovidStateAdapter extends RecyclerView.Adapter<CovidStateAdapter.ViewHolder>{

    ArrayList<covidState> covidstates;

    public CovidStateAdapter(ArrayList<covidState> covidstates){
        this.covidstates=covidstates;
    }

    @NonNull
    @Override
    public CovidStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_state,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidStateAdapter.ViewHolder holder, int position) {
    covidState covidstate=covidstates.get(position);
    holder.tvStateName.setText(covidstate.getmCovidState());
    holder.TotalCases.setText(covidstate.getmCases());
    holder.TotalDeaths.setText(covidstate.getmDeaths());
    holder.TotalRecovered.setText(covidstate.getmRecovered());
    holder.TodayCases.setText(covidstate.getmTCases());
    holder.TodayRecovered.setText(covidstate.getmTRecovered());
    holder.TodayDeaths.setText(covidstate.getmTDeaths());


    }

    @Override
    public int getItemCount() {
        return covidstates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TotalCases,tvStateName,TotalDeaths,TotalRecovered,TodayCases,TodayDeaths,TodayRecovered;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStateName=itemView.findViewById(R.id.tvTotalName);
            TotalCases=itemView.findViewById(R.id.TotalCases);
            TotalDeaths=itemView.findViewById(R.id.TotalDeaths);
            TotalRecovered=itemView.findViewById(R.id.TotalRecovered);
            TodayCases=itemView.findViewById(R.id.TodayCases);
            TodayRecovered=itemView.findViewById(R.id.TodayRecovered);
            TodayDeaths=itemView.findViewById(R.id.TodayDeaths);


        }
    }
}
