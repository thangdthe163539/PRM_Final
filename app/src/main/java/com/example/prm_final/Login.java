package com.example.prm_final;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView signUpTextView, notificationTextView;
    EditText emailEditText, passwordEditText;
    DatabaseFilm databaseFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        notificationTextView = findViewById(R.id.notification);
        notificationTextView.setVisibility(View.GONE);

        signUpTextView = findViewById(R.id.signUp);
        signUpTextView.setPaintFlags(signUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        databaseFilm = new DatabaseFilm(this, null , null , 1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String signUpSuccess = (String) bundle.get("signUpSuccess");
            if (signUpSuccess != null) {
                notificationTextView.setVisibility(View.VISIBLE);
                notificationTextView.setText(signUpSuccess);
            }
        }
    }

    public void signIn(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            notificationTextView.setVisibility(View.VISIBLE);
            notificationTextView.setText("Email and Password are required");
        } else if (TextUtils.isEmpty(email)) {
            notificationTextView.setVisibility(View.VISIBLE);
            notificationTextView.setText("Email is empty");
        } else if (TextUtils.isEmpty(password)) {
            notificationTextView.setVisibility(View.VISIBLE);
            notificationTextView.setText("Password is empty");
        } else {
            User user = databaseFilm.signIn(email, password);
            if (user == null) {
                notificationTextView.setVisibility(View.VISIBLE);
                notificationTextView.setText("Username or Password is wrong");
            } else {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUp.class);
        startActivity(intent);
    }
}

