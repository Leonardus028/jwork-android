package com.example.jwork_android;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**Request untuk mengambil Job, dimana request dipanggil dalam ApplyJobActivity
 * @Leonardus Kevin
 * @version 27.06.2021
 */
public class JobFetchRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/invoice/jobseeker/";
    private Map<String, String> params;
    /**
     * Method Request untuk mengambil Job berdasarkan ID Jobseeker
     */
    public JobFetchRequest(String jobseekerId, Response.Listener<String> listener){
        super(Request.Method.GET, URL+jobseekerId, listener, null);
        params = new HashMap<>();
    }
    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
