package com.example.jwork_android;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**Request untuk mendapatkan Bonus, dimana request dipanggil dalam ApplyJobActivity
 * @Leonardus Kevin
 * @version 27.06.2021
 */
public class BonusRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/bonus";
    private Map<String,String> params;


    /**
     * Method BonusRequest menggunakan Referral Code
     */
    public BonusRequest(String referralCode, Response.Listener<String> listener){
        super(Method.GET, URL+referralCode, listener, null);
        params = new HashMap<>();
    }

    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     */
    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return params;
    }
}