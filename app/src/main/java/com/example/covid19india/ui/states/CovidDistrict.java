package com.example.covid19india.ui.states;

public class CovidDistrict {
    String mCovidDistrict,mCase,mstate,mtodayCases,mDeaths,mTodayDeaths,mRecovered,mTodayRecovered,mActive,noida;
   public static int r=0,g=0,o=0;
    public CovidDistrict(String mCovidDistrict, String mCase,String mDeaths,String mRecovered,String mTodayDeaths,String mTodayRecovered,String mtodayCases,String noida) {
        this.mCovidDistrict = mCovidDistrict;
        this.mCase = mCase;
        this.mDeaths=mDeaths;
        this.mRecovered=mRecovered;
        this.mtodayCases=mtodayCases;
        this.mTodayDeaths=mTodayDeaths;
        this.mTodayRecovered=mTodayRecovered;
        this.noida=noida;
    }

    public String getmCovidDistrict() {
        return mCovidDistrict;
    }
    public String getmstate() {
        return mstate;
    }
    public String getmCase(){return mCase;}
    public String getmActive() {
        return mActive;
    }
    public String getmDeaths() {
        return mDeaths;
    }
    public String getmRecovered() {
        return mRecovered;
    }
    public String getmtodayCases() {
        return mtodayCases;
    }
    public String getmTodayDeaths() {
        return mTodayDeaths;
    }
    public String getmTodayRecovered() {
        return mTodayRecovered;
    }
    public String getmnoida(){return noida;}


}
