package com.example.phivantuan.documentview.msoffice;

import android.text.TextUtils;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Phi Van Tuan on 25/07/2019
 */

public class LoadFileModel {
    public static void loadPdfFile(String url, Callback<ResponseBody> callback) {
        LoadFileApi mLoadFileApi = (LoadFileApi) new Retrofit.Builder().baseUrl("https://www.baidu.com/").addConverterFactory(GsonConverterFactory.create()).build().create(LoadFileApi.class);
        if (!TextUtils.isEmpty(url)) {
            mLoadFileApi.loadPdfFile(url).enqueue(callback);
        }
    }

}
