package com.example.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**Request untuk menyelesaikan pengambilan Job, dimana request dipanggil dalam ApplyJobActivity
 * @Leonardus Kevin
 * @version 27.06.2021
 */
public class JobSelesaiRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/invoice/invoiceStatus/";
    private Map<String, String> params;
    private String invoiceStatus = "Finished";
    /**
     * Method Request untuk menyelesaikan pengambilan Job
     */
    public JobSelesaiRequest (String invoiceId, Response.Listener<String> listener) {
        super(Method.PUT, URL+invoiceId, listener, null);
        params = new HashMap<>();
        params.put("id", invoiceId);
        params.put("status", invoiceStatus);
    }
    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}