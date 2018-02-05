package com.cogoport.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogoport.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsyncTaskMultipleProcesses extends Fragment {


    public AsyncTaskMultipleProcesses() {
        // Required empty public constructor
    }

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_async_task_multiple_processes, container, false);
        AsyncTaskRunner task = new AsyncTaskRunner();
        task.execute(new String[]{"http://www.vogella.com/index.html"});


        return v;
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(params[0])
                            .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "Download failed";
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
//            finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
//            progressDialog = ProgressDialog.show(getContext(),
//                    "ProgressDialog",
//                    "Wait for "+time.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
//            finalResult.setText(text[0]);

        }
    }
}

