package com.example.prm_final;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    TextView tvSignIn, tvNotification;
    EditText etUsername, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvSignIn = findViewById(R.id.signUp);
        tvSignIn.setPaintFlags(tvSignIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvNotification = findViewById(R.id.notification);
        tvNotification.setVisibility(View.GONE);

        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
    }

    public void signUp(View view) {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String errMsg = "";

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errMsg = "Username, Email and Password are required";
        } else if (username.isEmpty()) {
            errMsg = "Username is empty";
        } else if (email.isEmpty()) {
            errMsg = "Email is empty";
        } else if (password.isEmpty()) {
            errMsg = "Password is empty";
        } else {
            DatabaseFilm db = new DatabaseFilm(view.getContext(), null, null, 1);
            User checkEmailExist = db.checkEmailExist(email);

            if (checkEmailExist == null && password.length() > 5 && validate(email)) {
                long result = db.signUp(username, email, password);

                if (result == 0) {
                    errMsg = "Sign Up failed, please try again later";
                } else {
                    Intent intent = new Intent(view.getContext(), Login.class);
                    intent.putExtra("signUpSuccess", "Sign Up Success !");
                    startActivity(intent);
                    return;
                }
            } else if (!validate(email)) {
                errMsg = "Invalid email format";
            } else if (password.length() < 6) {
                errMsg = "Password must be at least 6 characters long";
            } else if (checkEmailExist != null) {
                errMsg = "Email already exists";
            }
        }
        setNotification(errMsg);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void setNotification(String errMsg) {
        tvNotification.setVisibility(View.VISIBLE);
        tvNotification.setText(errMsg);
    }

    public void signIn(View view) {
        Intent intent = new Intent(view.getContext(), Login.class);
        startActivity(intent);
    }
}
