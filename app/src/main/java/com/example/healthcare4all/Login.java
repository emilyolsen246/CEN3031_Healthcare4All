package com.example.healthcare4all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcare4all.API.ApiAppointment;
import com.example.healthcare4all.API.ApiAppointmentWithAuthToken;
import com.example.healthcare4all.API.UserLogin;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import okhttp3.*;


public class Login extends AppCompatActivity {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    EditText username;
    EditText enter_password;
    Button button_signup;
    Button button_login;

    FirebaseAuth mAuth;

    private static String patJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyTmFtZSI6InBhdCIsIlByaXZpbGVnZSI6MSwibmJmIjoxNjcwMjUyMDY1LCJleHAiOjE2NzAyNTU2NjUsImlhdCI6MTY3MDI1MjA2NSwiaXNzIjoiSGVhbHRoY2FyZTRBbGwifQ.DuhyG2pFjglaOsIabtl3fGaqM_IVVbAio_fxVcSlTPA";
    private static String docJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyTmFtZSI6ImRvYyIsIlByaXZpbGVnZSI6MiwibmJmIjoxNjcwMjU2MjU3LCJleHAiOjE2NzAyNTk4NTcsImlhdCI6MTY3MDI1NjI1NywiaXNzIjoiSGVhbHRoY2FyZTRBbGwifQ.NU26YIIUCUs04dYTQsx8GOjZbF8g9QZ4uCIRUYpluK8";

    private static String b64UrlDecode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    private static void loginDemo() {
        // DEMO FOR HOW THIS WORKS
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        UserLogin userLogin = new UserLogin();
        userLogin.userName = "pat";
        userLogin.password = "string";
        userLogin.privilege = 1;

        String json = gson.toJson(userLogin);


//                String json = "{\"Reason\": \"" + "test" + "\"}";

        RequestBody body = RequestBody.create(json, JSON);

        Log.i("##############LOGIN", "TEST");
        Log.i("##############LOGIN", "TEST");
        Log.i("##############LOGIN", "TEST");


        Request request = new Request.Builder()
                .url("http://10.0.2.2:5253/Login")
                .post(body)
                .header("Connection", "close")
//                        .header("Accept-Encoding", "identity")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.i("Error", "Unlucky1", e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        Log.i("Error", "Unlucky2");

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.i("Response", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    // The encoded JWT to store in the app somewhere.
                    String encodedJwt = response.body().string();
                    String[] splitJWT = encodedJwt.split("\\.");

                    // Decoded JWT body (maybe not neeeded).
                    Log.i("encodedJwt", encodedJwt);
                    Log.i("Body", "" + b64UrlDecode(splitJWT[1]));
                }
            }
        });
    }

    private static void AddAppointment() {
        Log.i("##############AddAppointment", "TEST");
        Log.i("##############AddAppointment", "TEST");
        Log.i("##############AddAppointment", "TEST");

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        ApiAppointmentWithAuthToken apiAppointmentWithAuthToken = new ApiAppointmentWithAuthToken();

        apiAppointmentWithAuthToken.patientUserName = "pat";
        apiAppointmentWithAuthToken.city = "TESTTESTTEST";
        apiAppointmentWithAuthToken.encodedJwt = docJWT;


        String json = gson.toJson(apiAppointmentWithAuthToken);

        Log.i("JSON", json);

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url("http://10.0.2.2:5253/AddAppointment")
                .post(body)
                .header("Connection", "close")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.i("Error", "Unlucky1", e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        Log.i("Error", "Unlucky2");

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.i("Response", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Log.i("Body", "" + responseBody.string());
                }
            }
        });
    }

    private static void GetAppointment() {
        Log.i("##############AddAppointment", "TEST");
        Log.i("##############AddAppointment", "TEST");
        Log.i("##############AddAppointment", "TEST");

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        ApiAppointmentWithAuthToken apiAppointmentWithAuthToken = new ApiAppointmentWithAuthToken();

        String json = "";

        Log.i("JSON", json);

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url("http://10.0.2.2:5253/GetAllAppointments?encodedJwt=" + patJWT)
                .post(body)
                .header("Connection", "close")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.i("Error", "Unlucky1", e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        Log.i("Error", "Unlucky2");

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.i("Response", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Log.i("Body", "" + responseBody.string());
                    // Do this strategy to rebuild the ApiAppointment (or other class).
//                    Map jsonJavaRootObject = new Gson().fromJson("{/*whatever your mega complex object*/}", Map.class)
                }
            }
        });
    }

    public static void RemoveAppointment() {
        Log.i("##############AddAppointment", "TEST");
        Log.i("##############AddAppointment", "TEST");
        Log.i("##############AddAppointment", "TEST");

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        ApiAppointmentWithAuthToken apiAppointmentWithAuthToken = new ApiAppointmentWithAuthToken();

        // Change this to the ID that you need
        apiAppointmentWithAuthToken.appointmentId = 5003;
        apiAppointmentWithAuthToken.encodedJwt = docJWT;

        String json = gson.toJson(apiAppointmentWithAuthToken);

        Log.i("JSON", json);

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url("http://10.0.2.2:5253/RemoveAppointment")
                .post(body)
                .header("Connection", "close")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.i("Error", "Unlucky1", e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        Log.i("Error", "Unlucky2");

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.i("Response", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Log.i("Body", "" + responseBody.string());
                }
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        enter_password = findViewById(R.id.password);
        button_signup = findViewById(R.id.signup);
        button_login = findViewById(R.id.login);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String password = enter_password.getText().toString();

//                login(email, password);

//                loginDemo();
//                AddAppointment();
                GetAppointment();
//                RemoveAppointment();
            }
        });

    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}