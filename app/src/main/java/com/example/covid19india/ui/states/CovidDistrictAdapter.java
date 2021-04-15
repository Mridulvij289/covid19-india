package com.example.covid19india.ui.states;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19india.R;

import java.util.ArrayList;

public class CovidDistrictAdapter extends RecyclerView.Adapter<CovidDistrictAdapter.ViewHolder>{

    ArrayList<CovidDistrict> covidDistrics;
    public CovidDistrictAdapter(ArrayList<CovidDistrict> covidDistrics){
        this.covidDistrics=covidDistrics;
    }

    @NonNull
    @Override
    public CovidDistrictAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_district,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CovidDistrictAdapter.ViewHolder holder, int position) {

        CovidDistrict covidDistrict= covidDistrics.get(position);

            holder.tvDistrictName.setText(covidDistrict.getmCovidDistrict());
            holder.TotalCases.setText(covidDistrict.getmCase());
            holder.TotalDeaths.setText(covidDistrict.getmDeaths());
            holder.TotalRecovered.setText(covidDistrict.getmRecovered());
            holder.TodayCases.setText(covidDistrict.getmtodayCases());
            holder.TodayRecovered.setText(covidDistrict.getmTodayRecovered());
            holder.TodayDeaths.setText(covidDistrict.getmTodayDeaths());
            holder.r.setBackgroundColor(Color.parseColor(covidDistrict.getmnoida()));

    }


    @Override
    public int getItemCount() {
        return covidDistrics.size();
    }


public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TotalCases,tvDistrictName,TotalDeaths,TotalRecovered,TodayCases,TodayDeaths,TodayRecovered;
        RelativeLayout r;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            r=itemView.findViewById(R.id.relativelayoutdistrict);
            tvDistrictName=itemView.findViewById(R.id.tvDistrictName);
            TotalCases=itemView.findViewById(R.id.TotalCase);
            TotalDeaths=itemView.findViewById(R.id.TotalDeath);
            TotalRecovered=itemView.findViewById(R.id.TotalRecovere);
            TodayCases=itemView.findViewById(R.id.TodayCase);
            TodayRecovered=itemView.findViewById(R.id.TodayRecovere);
            TodayDeaths=itemView.findViewById(R.id.TodayDeath);

        }
    }
}
