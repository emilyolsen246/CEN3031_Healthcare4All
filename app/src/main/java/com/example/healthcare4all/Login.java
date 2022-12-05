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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import com.google.gson.*;



import okhttp3.*;


public class Login extends AppCompatActivity {

    EditText username;
    EditText enter_password;
    Button button_signup;
    Button button_login;

    FirebaseAuth mAuth;

    private static String b64UrlDecode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
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

                // DEMO FOR HOW THIS WORKS
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                Log.i("##############1111", "TEST");

                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
//                        .retryOnConnectionFailure(true)
                        .build();

                String json = "{\n" +
                        "  \"userName\": \"pat\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"privilege\": 1\n" +
                        "}";

//                String json = "{\"Reason\": \"" + "test" + "\"}";

                RequestBody body = RequestBody.create(json, JSON);

                Log.i("##############1111", "TEST");

                Request request = new Request.Builder()
                        .url("http://10.0.2.2:5253/Login")
                        .post(body)
                        .header("Connection", "close")
//                        .header("Accept-Encoding", "identity")
                        .build();

                Log.i("##############1111", "TEST");

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

                            String encodedJwt = response.body().string();
                            String[] splitJWT = encodedJwt.split("\\.");

                            Log.i("encodedJwt", encodedJwt);
                            Log.i("Body", "" + b64UrlDecode(splitJWT[1]));
                        }
                    }
                });

                Log.i("##############2222", "TEST2");


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