package com.example.toshinomi.firebasetest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button m_btnCreate;
    private Button m_btnSignIn;
    private FirebaseAuth m_Auth;
    private String m_strEmail;
    private String m_strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_Auth = FirebaseAuth.getInstance();

        InitControl();
    }

    void InitControl() {
        m_btnCreate = findViewById(R.id.buttonCreate);
        m_btnCreate.setOnClickListener(new View.OnClickListener() {
            EditText textEmail = findViewById(R.id.editTextEmail);
            EditText textPassword = findViewById(R.id.editTextPassword);
            String strEmail = textEmail.getText().toString();
            String strPassword = textPassword.getText().toString();
            @Override
            public void onClick(View v) {
                createUser(strEmail, strPassword);
            }
        });

        m_btnSignIn = findViewById(R.id.buttonSignIn);
        m_btnSignIn.setOnClickListener(new View.OnClickListener() {
            EditText textEmail = findViewById(R.id.editTextEmail);
            EditText textPassword = findViewById(R.id.editTextPassword);
            String strEmail = textEmail.getText().toString();
            String strPassword = textPassword.getText().toString();
            @Override
            public void onClick(View v) {
                signIn(strEmail, strPassword);
            }
        });
    }

    void createUser(String _strEmail, String _strPassword) {
        m_strEmail = _strEmail;
        m_strPassword = _strPassword;
        m_Auth.createUserWithEmailAndPassword(_strEmail, _strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                TextView textViewLoginEmail = findViewById(R.id.textViewLoginEmail);
                TextView textViewLoginStatus = findViewById(R.id.textViewLoginEmail);
                textViewLoginEmail.setText("");
                textViewLoginStatus.setText("");
                if (task.isSuccessful()) {
                    textViewLoginStatus.setText("Login success");
                    textViewLoginEmail.setText(m_strEmail);
                } else {
                    signIn(m_strEmail, m_strPassword);
                }
            }
        });
    }

    void signIn(String _strEmail, String _strPassword) {
        m_strEmail = _strEmail;
        m_Auth.signInWithEmailAndPassword(_strEmail, _strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                TextView textViewLoginEmail = findViewById(R.id.textViewLoginEmail);
                TextView textViewLoginStatus = findViewById(R.id.textViewLoginEmail);
                textViewLoginEmail.setText("");
                textViewLoginStatus.setText("");
                if (task.isSuccessful()) {
                    textViewLoginStatus.setText("Login success");
                    textViewLoginEmail.setText(m_strEmail);
                } else {
                    textViewLoginStatus.setText("Login fail");
                }
            }
        });
    }
}