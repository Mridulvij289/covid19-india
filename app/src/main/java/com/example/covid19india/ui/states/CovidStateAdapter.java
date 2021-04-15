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
