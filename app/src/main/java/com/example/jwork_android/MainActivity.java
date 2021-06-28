package com.example.jwork_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
/**Activity main menu untuk aplikasi Jwork
 * @author Leonardus Kevin
 * @version 27.06.2021
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Recruiter> listRecruiter = new ArrayList<>();
    private ArrayList<Job> jobIdList = new ArrayList<>();
    private HashMap<Recruiter, ArrayList<Job>> childMapping = new HashMap<>();



    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    private static int jobseekerId;
    /**
     * Method untuk meninisialisasi activity_main
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnApply = findViewById(R.id.applyBtn);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            jobseekerId  = extras.getInt("jobseekerId");
        }


        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        refreshList();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(MainActivity.this, ApplyJobActivity.class);
                int jobId = childMapping.get(listRecruiter.get(i)).get(i1).getId();
                String jobName = childMapping.get(listRecruiter.get(i)).get(i1).getName();
                String jobCategory = childMapping.get(listRecruiter.get(i)).get(i1).getCategory();
                int jobFee = childMapping.get(listRecruiter.get(i)).get(i1).getFee();

                intent.putExtra("job_id", jobId);
                intent.putExtra("job_name", jobName);
                intent.putExtra("job_category", jobCategory);
                intent.putExtra("job_fee", jobFee);
                intent.putExtra("jobseekerId", jobseekerId);

                startActivity(intent);
                return true;
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelesaiJobActivity.class);
                intent.putExtra("jobseekerId", jobseekerId);
                startActivity(intent);
            }
        });
    }

    /**
     * Method untuk mendapatkan jsonObject Job
     */
    protected void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++){
                            JSONObject job = jsonResponse.getJSONObject(i);
                            JSONObject recruiter = job.getJSONObject("recruiter");
                            JSONObject location = recruiter.getJSONObject("location");

                            String city = location.getString("city");
                            String province = location.getString("province");
                            String description = location.getString("description");

                            Location location1 = new Location(province, city, description);

                            int recruiterId = recruiter.getInt("id");
                            String recruiterName = recruiter.getString("name");
                            String recruiterEmail = recruiter.getString("email");
                            String recruiterPhoneNumber = recruiter.getString("phoneNumber");

                            Recruiter newRecruiter = new Recruiter(recruiterId, recruiterName, recruiterEmail, recruiterPhoneNumber, location1);
                            if (listRecruiter.size() > 0) {
                                boolean success = true;
                                for (Recruiter rec : listRecruiter)
                                    if (rec.getId() == newRecruiter.getId())
                                        success = false;
                                if (success) {
                                    listRecruiter.add(newRecruiter);
                                }
                            } else {
                                listRecruiter.add(newRecruiter);
                            }

                            int jobId = job.getInt("id");
                            int jobFee = job.getInt("fee");
                            String jobName = job.getString("name");
                            String jobCategory = job.getString("category");

                            Job newJob = new Job(jobId, jobName, newRecruiter, jobFee, jobCategory);
                            jobIdList.add(newJob);

                            for (Recruiter rec : listRecruiter) {
                                ArrayList<Job> temp = new ArrayList<>();
                                for (Job job2 : jobIdList) {
                                    if (job2.getRecruiter().getName().equals(rec.getName()) ||
                                            job2.getRecruiter().getEmail().equals(rec.getEmail()) ||
                                            job2.getRecruiter().getPhoneNumber().equals(rec.getPhoneNumber())) {
                                        temp.add(job2);
                                    }
                                }
                                childMapping.put(rec, temp);
                            }
                        }
                        listAdapter = new MainListAdapter(MainActivity.this, listRecruiter, childMapping);
                        expListView.setAdapter(listAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }




}