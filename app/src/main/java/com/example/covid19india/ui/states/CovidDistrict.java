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
