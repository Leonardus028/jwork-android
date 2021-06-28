package com.example.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**Request untuk Login, dimana request dipanggil dalam LoginActivity
 * @Leonardus Kevin
 * @version 27.06.2021
 */
public class LoginRequest extends StringRequest {

    private static final String URL = "http://10.0.2.2:8080/jobseeker/login";
    private Map<String, String> params;
    /**
     * Method Request menggunakan email dan password
     */
    public LoginRequest(String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }
    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     */
    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
