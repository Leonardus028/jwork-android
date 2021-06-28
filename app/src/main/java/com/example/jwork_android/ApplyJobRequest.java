package com.example.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**Request untuk mengapply Job, dimana request dipanggil dalam ApplyJobActivity
 * @author Leonardus Kevin
 * @version 27.06.2021
 */
public class ApplyJobRequest extends StringRequest {
    private static final String URL_EWALLET = "http://10.0.2.2:8080/invoice/createEWalletPayment";
    private static final String URL_BANK = "http://10.0.2.2:8080/invoice/createBankPayment";
    private Map<String, String> params;

    /**
     * Method ApplyJobRequest menggunakan E-Wallet Payment
     */
    public ApplyJobRequest(String jobIdList, String jobseekerID, String referralCode, Response.Listener<String> listener) {
        super(Method.POST, URL_EWALLET, listener, null);
        params = new HashMap<>();
        params.put("jobIdList", jobIdList);
        params.put("jobseekerId", jobseekerID);
        params.put("referralCode", referralCode);
    }
    /**
     * Method ApplyJobRequest menggunakan Bank Payment
     */
    public ApplyJobRequest(String jobIdList, String jobseekerID, Response.Listener<String> listener) {
        super(Method.POST, URL_BANK, listener, null);
        params = new HashMap<>();
        params.put("jobIdList", jobIdList);
        params.put("jobseekerId", jobseekerID);
        params.put("adminFee", "1000");
    }
    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     */
    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return params;
    }
}
