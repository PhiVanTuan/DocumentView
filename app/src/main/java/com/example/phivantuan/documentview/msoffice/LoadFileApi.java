package com.example.phivantuan.documentview.msoffice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Phi Van Tuan on 25/07/2019
 */


public interface LoadFileApi {
    @GET
    Call<ResponseBody> loadPdfFile(@Url String str);

}
