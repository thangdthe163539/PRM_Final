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
    TextView signIn, notification;
    EditText username, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signIn = findViewById(R.id.signUp);
        signIn.setPaintFlags(signIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        notification = findViewById(R.id.notification);
        notification.setVisibility(View.GONE);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }
    public void signUp(View view) {
        String Username = username.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Email = email.getText().toString().trim();
        if ((Username.isEmpty() || Username == null) && (Email.isEmpty() || Email == null) && (Password.isEmpty() || Password == null)) {
            setNotification("Username, Email and Password is required");
        }
        else if (Username.isEmpty() || Username == null) {
            setNotification("Username is empty");
        }
        else if (Email.isEmpty() || Email == null) {
            setNotification("Email is empty");
        }
        else if (Password.isEmpty() || Password == null) {
            setNotification("Password is empty");
        }
        else {
            DatabaseFilm dba = new DatabaseFilm(view.getContext(), null, null, 1);
            User CheckEmailExist = dba.checkEmailExist(Email);
            if (CheckEmailExist == null && Password.length() > 5 && validate(Email)) {
                long result = dba.signUp(Username, Email, Password);
                if (result == 0) {
                    setNotification("Sign Up fail check later");
                } else {
                    Intent intent = new Intent(view.getContext(), Login.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("signUpSuccess", "Sign Up Success !");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            } else {
                if (!validate(Email)) {
                    setNotification("This email was wrong format");
                }
                else if (Password.length() < 6) {
                    setNotification("Password have length higher than 5");
                }
                else if (CheckEmailExist != null) {
                    setNotification("This email has been used");
                }
            }
        }
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    public void setNotification(String noti) {
        notification.setVisibility(View.VISIBLE);
        notification.setText(noti);
    }
    public void signIn(View view) {
        Intent intent = new Intent(view.getContext(), Login.class);
        startActivity(intent);
    }
}