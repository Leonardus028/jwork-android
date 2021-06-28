package com.example.jwork_android;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
/**Request untuk Register, dimana request dipanggil dalam LoginActivity
 * @Leonardus Kevin
 * @version 27.06.2021
 */
public class RegisterRequest extends StringRequest {

    private static final String URL = "http://10.0.2.2:8080/jobseeker/register";
    private Map<String, String> params;
    /**
     * Method Request menggunakan nama, email dan password
     */
    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
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
