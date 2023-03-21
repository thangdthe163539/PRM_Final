package com.example.prm_final;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView signUpLink, notification;
    EditText email, password;
    DatabaseFilm dbf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpLink = findViewById(R.id.signUp);
        signUpLink.setPaintFlags(signUpLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        notification = findViewById(R.id.notification);
        notification.setVisibility(View.GONE);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        dbf = new DatabaseFilm(this, null , null , 1);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            notification.setVisibility(View.GONE);
        } else {
            notification.setVisibility(View.VISIBLE);
            String signUpSuccess = (String) bundle.get("signUpSuccess");
            notification.setText(signUpSuccess);
        }

    }
    public void signIn(View view) {
        if ((email.getText().toString().isEmpty() || email.getText().toString() == null) && (password.getText().toString().isEmpty() || password.getText().toString() == null)) {
            notification.setVisibility(View.VISIBLE);
            notification.setText("Email and Password is required");
        }
        else if (email.getText().toString().isEmpty() || email.getText().toString() == null) {
            notification.setVisibility(View.VISIBLE);
            notification.setText("Email is empty");
        }
        else if (password.getText().toString().isEmpty() || password.getText().toString() == null) {
            notification.setVisibility(View.VISIBLE);
            notification.setText("Password is empty");
        }
        else {
            User user = dbf.signIn(email.getText().toString(), password.getText().toString());
            //User user = new User(1, "thangdt", "thangdt@gmail.com", "123456");

            if (user == null) {
                notification.setVisibility(View.VISIBLE);
                notification.setText("Username or Password was wrong");
            } else {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
    public void signUp(View view) {
        Intent intent = new Intent(view.getContext(), SignUp.class);
        startActivity(intent);
    }
}