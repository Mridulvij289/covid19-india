package com.example.covid19india.ui.states;

import android.os.Parcel;
import android.os.Parcelable;

public class covidState implements Parcelable {
     String mCovidState,mCases,mtodayCases,mDeaths,mTodayDeaths,mRecovered,mCritical,mTodayRecovered,mActive,mCode;

    public covidState(String mCovidState, String mCases,String mRecovered,String mDeaths,String mtodayCases,String mTodayDeaths,String mTodayRecovered,String mActive,String mCode) {
        this.mCovidState = mCovidState;
        this.mCases = mCases;
        this.mtodayCases = mtodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mCritical = mCritical;
        this.mTodayRecovered=mTodayRecovered;
        this.mActive=mActive;
        this.mCode=mCode;
    }



    public String getmCovidState() {
        return mCovidState;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmCases() {
        return mCases;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public String getmTDeaths() {
        return mTodayDeaths;
    }

    public String getmTCases() {
        return mtodayCases;
    }

    public String getmCode() {
        return mCode;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmTRecovered() {
        return mTodayRecovered;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCovidState);
        dest.writeString(this.mCases);
        dest.writeString(this.mtodayCases);
        dest.writeString(this.mActive);
        dest.writeString(this.mCode);
        dest.writeString(this.mDeaths);
        dest.writeString(this.mTodayDeaths);
        dest.writeString(this.mRecovered);
        dest.writeString(this.mTodayRecovered);

    }

    protected covidState(Parcel in){
        this.mCovidState=in.readString();
        this.mCases=in.readString();
        this.mtodayCases=in.readString();
        this.mActive=in.readString();
        this.mCode=in.readString();
        this.mDeaths=in.readString();
        this.mTodayDeaths=in.readString();
        this.mRecovered=in.readString();
        this.mTodayRecovered=in.readString();
    }
    public static final Parcelable.Creator<covidState> CREATOR= new Parcelable.Creator<covidState>(){

        @Override
        public covidState createFromParcel(Parcel source) {
            return new covidState(source);
        }

        @Override
        public covidState[] newArray(int size) {
            return new covidState[size];
        }

    };


}

