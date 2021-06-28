package com.example.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**Activity untuk menampilkan Job yang diapply
 * @Leonardus Kevin
 * @version 27.06.2021
 */
public class SelesaiJobActivity extends AppCompatActivity {


        TextView tvInvoiceId, tvJobseekerName, tvInvoiceDate, tvPaymentType, tvInvoiceStatus, tvReferralCode, tvJobName, tvJobFee, tvTotalFee;
        TextView staticReferralCode, staticJobseeker, staticInvoiceDate,staticJob,staticInvoiceStatus,staticPayType,staticTotalFee;
        Button btnCancel, btnFinish;

        String jobseekerName, jobName, invoiceDate, paymentType, invoiceStatus, refCode;
        int jobseekerId, jobFee, totalFee, invoiceId;
         JSONObject bonus;

    /**
     * Method untuk meninisialisasi activity_selesai_job
     */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_selesai_job);

            tvInvoiceId = findViewById(R.id.invoice_id);
            tvJobseekerName = findViewById(R.id.jobseeker_name);
            tvInvoiceDate = findViewById(R.id.invoice_date);
            tvPaymentType = findViewById(R.id.payment_type);
            tvInvoiceStatus = findViewById(R.id.invoice_status);
            tvReferralCode = findViewById(R.id.referral_code);
            tvJobName = findViewById(R.id.job_name);
            tvJobFee = findViewById(R.id.job_fee);
            tvTotalFee = findViewById(R.id.total_fee);
            staticJobseeker = findViewById(R.id.static_jobseeker);
            staticInvoiceDate = findViewById(R.id.st_invoice_date);
            staticPayType = findViewById(R.id.staticPaymentType);
            staticJob = findViewById(R.id.static_job);
            staticInvoiceStatus = findViewById(R.id.static_invoice_status);
            staticReferralCode = findViewById(R.id.static_referral_code);
            staticTotalFee = findViewById(R.id.static_total_fee);

            btnCancel = findViewById(R.id.btnCancel);
            btnFinish = findViewById(R.id.btnFinish);

            tvInvoiceId.setText("There are no ongoing orders");
            tvReferralCode.setVisibility(View.GONE);
            staticReferralCode.setVisibility(View.GONE);
            staticJobseeker.setVisibility(View.GONE);
            staticInvoiceDate.setVisibility(View.GONE);
            staticJob.setVisibility(View.GONE);
            staticInvoiceStatus.setVisibility(View.GONE);
            staticPayType.setVisibility(View.GONE);
            staticTotalFee.setVisibility(View.GONE);
            tvJobseekerName.setVisibility(View.GONE);
            tvInvoiceDate.setVisibility(View.GONE);
            tvPaymentType.setVisibility(View.GONE);
            tvInvoiceStatus.setVisibility(View.GONE);
            tvJobName.setVisibility(View.GONE);
            tvJobFee.setVisibility(View.GONE);
            tvTotalFee.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            btnFinish.setVisibility(View.GONE);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                jobseekerId = extras.getInt("jobseekerId");
            }

            fetchJob();
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Response.Listener<String> cancelListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Intent intent = new Intent(SelesaiJobActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Toast.makeText(SelesaiJobActivity.this, "Apply Job di Batalkan!", Toast.LENGTH_LONG).show();
                    JobBatalRequest batalRequest = new JobBatalRequest(String.valueOf(invoiceId), cancelListener);
                    RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
                    queue.add(batalRequest);
                }
            });


            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Response.Listener<String> doneListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Intent intent = new Intent(SelesaiJobActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Toast.makeText(SelesaiJobActivity.this, "Apply Job Selesai!", Toast.LENGTH_LONG).show();
                    JobSelesaiRequest selesaiRequest = new JobSelesaiRequest(String.valueOf(invoiceId), doneListener);
                    RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
                    queue.add(selesaiRequest);
                }
            });
        }
    /**
     * Method untuk mendapatkan Job yang diapply oleh Jobseeker
     */

    protected void fetchJob() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() == 0) {
                        noAppliedJob();
                    }

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        invoiceStatus = jsonObject.getString("invoiceStatus");

                        if (invoiceStatus.equals("OnGoing")) {
                            staticJobseeker.setVisibility(View.VISIBLE);
                            staticInvoiceDate.setVisibility(View.VISIBLE);
                            staticJob.setVisibility(View.VISIBLE);
                            staticInvoiceStatus.setVisibility(View.VISIBLE);
                            staticPayType.setVisibility(View.VISIBLE);
                            staticTotalFee.setVisibility(View.VISIBLE);
                            tvJobseekerName.setVisibility(View.VISIBLE);
                            tvInvoiceDate.setVisibility(View.VISIBLE);
                            tvPaymentType.setVisibility(View.VISIBLE);
                            tvInvoiceStatus.setVisibility(View.VISIBLE);
                            tvJobName.setVisibility(View.VISIBLE);
                            tvJobFee.setVisibility(View.VISIBLE);
                            tvTotalFee.setVisibility(View.VISIBLE);
                            btnCancel.setVisibility(View.VISIBLE);
                            btnFinish.setVisibility(View.VISIBLE);

                            btnCancel.setVisibility(View.VISIBLE);
                            btnFinish.setVisibility(View.VISIBLE);

                            invoiceId = jsonObject.getInt("id");
                            invoiceDate = jsonObject.getString("date");
                            paymentType = jsonObject.getString("paymentType");
                            totalFee = jsonObject.getInt("totalFee");
                            refCode = "null";

                            try {
                                bonus = jsonObject.getJSONObject("bonus");
                                refCode = bonus.getString("referralCode");
                                jobFee= bonus.getInt("extraFee");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            tvInvoiceId.setText(String.valueOf(invoiceId));
                            tvInvoiceDate.setText(invoiceDate.substring(0, 10));
                            tvInvoiceStatus.setText(invoiceStatus);
                            tvPaymentType.setText(paymentType);
                            if (!refCode.isEmpty()) {
                                tvTotalFee.setText(String.valueOf(totalFee + jobFee));
                            } else {
                                tvTotalFee.setText(String.valueOf(totalFee));
                            }
                            tvReferralCode.setText(refCode);

                            JSONObject jsonJobseeker = jsonObject.getJSONObject("jobseeker");
                            jobseekerName = jsonJobseeker.getString("name");
                            tvJobseekerName.setText(jobseekerName);

                            JSONArray jsonJobList = jsonObject.getJSONArray("jobs");
                            for (int j = 0; j < jsonJobList.length(); j++) {
                                JSONObject jsonJob = jsonJobList.getJSONObject(j);
                                jobName = jsonJob.getString("name");
                                jobFee = jsonJob.getInt("fee");

                                tvJobName.setText(jobName);
                                tvJobFee.setText(String.valueOf(jobFee));
                            }
                        }

                        if (i == (jsonArray.length() - 1) && !invoiceStatus.equals("OnGoing")) {
                            noAppliedJob();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        JobFetchRequest jobFetchRequest = new JobFetchRequest(String.valueOf(jobseekerId), responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
        queue.add(jobFetchRequest);
    }

    protected void noAppliedJob() {
        tvReferralCode.setVisibility(View.GONE);
        staticReferralCode.setVisibility(View.GONE);
        staticJobseeker.setVisibility(View.GONE);
        staticInvoiceDate.setVisibility(View.GONE);
        staticJob.setVisibility(View.GONE);
        staticInvoiceStatus.setVisibility(View.GONE);
        staticPayType.setVisibility(View.GONE);
        staticTotalFee.setVisibility(View.GONE);
        tvJobseekerName.setVisibility(View.GONE);
        tvInvoiceDate.setVisibility(View.GONE);
        tvPaymentType.setVisibility(View.GONE);
        tvInvoiceStatus.setVisibility(View.GONE);
        tvJobName.setVisibility(View.GONE);
        tvJobFee.setVisibility(View.GONE);
        tvTotalFee.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        btnFinish.setVisibility(View.GONE);

        Toast.makeText(SelesaiJobActivity.this,
                "You have not applied to any job", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SelesaiJobActivity.this,
                MainActivity.class);
        intent.putExtra("jobseekerId", jobseekerId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }




}