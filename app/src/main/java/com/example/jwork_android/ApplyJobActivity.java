package com.example.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**Activity untuk mengapply sebuah Job
 * @author Leonardus Kevin
 * @version 27.06.2021
 */
public class ApplyJobActivity extends AppCompatActivity {
    private int jobseekerId;
    private int jobId;
    private String jobName;
    private String jobCategory;
    private double jobFee;
    private int bonus;
    private  String selectedPayment;
    /**
     * Method untuk meninisialisasi activity_apply_job
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jobId = extras.getInt("job_id");
            jobName = extras.getString("job_name");
            jobCategory = extras.getString("job_category");
            jobFee = extras.getInt("job_fee");
            jobseekerId = extras.getInt("jobseekerId");
        }

        //TextView (tv)
        final TextView tvJobName = findViewById(R.id.job_name);
        final TextView tvJobCategory = findViewById(R.id.job_category);
        final TextView tvJobFee = findViewById(R.id.job_fee);
        final TextView tvTextCode = findViewById(R.id.textCode);
        final TextView tvTotalFee = findViewById(R.id.total_fee);

        //EditText (et)
        final EditText etReferralCode = findViewById(R.id.referral_code);

        //RadioGroup
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //Button
        final Button btnHitung = findViewById(R.id.hitung);
        final Button btnApply = findViewById(R.id.btnApply);


        btnApply.setVisibility(View.INVISIBLE);
        tvTextCode.setVisibility(View.INVISIBLE);
        etReferralCode.setVisibility(View.INVISIBLE);

        tvJobName.setText(jobName);
        tvJobCategory.setText(jobCategory);
        tvJobFee.setText("Rp. " + String.valueOf(jobFee));
        tvTotalFee.setText("Rp. 0");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rbId = findViewById(checkedId);
                String payMethod = rbId.getText().toString().trim();
                switch (payMethod) {
                    case "E-wallet":
                        tvTextCode.setVisibility(View.VISIBLE);
                        etReferralCode.setVisibility(View.VISIBLE);
                        break;

                    case "Bank":
                        tvTextCode.setVisibility(View.INVISIBLE);
                        etReferralCode.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                switch (checkedId) {

                    case R.id.bank:
                        tvTotalFee.setText(String.valueOf(jobFee));
                        break;

                    case R.id.ewallet:
                        String refCode = etReferralCode.getText().toString();
                        final Response.Listener<String> bonusResponse = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (refCode.isEmpty()) {
                                    tvTotalFee.setText(String.valueOf(jobFee));
                                } else {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);

                                        int extraFee = jsonResponse.getInt("extraFee");
                                        int minTotalFee = jsonResponse.getInt("minTotalFee");
                                        boolean bonusStatus = jsonResponse.getBoolean("active");

                                        if (!bonusStatus) {
                                            Toast.makeText(ApplyJobActivity.this, "Bonus Tidak Tersedia!", Toast.LENGTH_LONG).show();
                                        } else if (bonusStatus) {
                                            if (jobFee < extraFee || jobFee < minTotalFee) {
                                                Toast.makeText(ApplyJobActivity.this, "Referral Code Tidak Bisa Digunakan Karena Tidak Memenuhi Persyaratan!", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(ApplyJobActivity.this, "Referral Code Berhasil Digunakan!", Toast.LENGTH_LONG).show();
                                                //Set Total Price
                                                tvTotalFee.setText(String.valueOf(jobFee + extraFee));
                                            }
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(ApplyJobActivity.this, "Referral Code Tidak Ditemukan!", Toast.LENGTH_LONG).show();
                                        tvTotalFee.setText(String.valueOf(jobFee));
                                    }
                                }

                            }
                        };
                        Response.ErrorListener errorPromo = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error", "Error Occured", error);
                            }
                        };

                        BonusRequest bonusRequest = new BonusRequest(refCode, bonusResponse);
                        RequestQueue queue = Volley.newRequestQueue(ApplyJobActivity.this);
                        queue.add(bonusRequest);
                        break;

                }
                btnHitung.setVisibility(View.INVISIBLE);
                btnApply.setVisibility(View.VISIBLE);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                ApplyJobRequest request = null;

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(ApplyJobActivity.this, "Apply Successful!", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            else {
                                Toast.makeText(ApplyJobActivity.this, "Apply Failed!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ApplyJobActivity.this, "Apply Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                if(selectedRadioId == R.id.bank) {
                    request = new ApplyJobRequest(String.valueOf(jobId), String.valueOf(jobseekerId), responseListener);
                    RequestQueue q = Volley.newRequestQueue(ApplyJobActivity.this);
                    q.add(request);
                }

                else if(selectedRadioId == R.id.ewallet) {
                    request = new ApplyJobRequest(String.valueOf(jobId), String.valueOf(jobseekerId), etReferralCode.getText().toString(), responseListener);
                    RequestQueue q = Volley.newRequestQueue(ApplyJobActivity.this);
                    q.add(request);
                }
            }
        });
    }
}