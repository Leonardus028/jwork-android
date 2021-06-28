package com.example.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**Request untuk membuat Job, dimana request dipanggil dalam MainActivity
 * @author Leonardus Kevin
 * @version 27.06.2021
 */
public class MenuRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/job";
    private Map<String,String> params;

    /**
     * Method Request yang dipanggil dalam MainActivity
     */
    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
        params = new HashMap<>();
    }

    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     */
    @Override
    protected Map<String,String> getParams() throws AuthFailureError{
        return params;
    }
}