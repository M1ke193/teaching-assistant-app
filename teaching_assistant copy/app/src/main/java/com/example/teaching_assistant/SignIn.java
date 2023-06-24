package com.example.teaching_assistant;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teaching_assistant.GiaoVien_Activity.GiaoVienXemDanhSachLop;
import com.example.teaching_assistant.SinhVien_Activity.SinhVienXemDanhSachLop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends AppCompatActivity {
    EditText emailBox;
    EditText passBox;
    Button s_login, t_login;
    TextView s_signup;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailBox = findViewById(R.id.email);
        passBox = findViewById(R.id.password);
        s_login = findViewById(R.id.loginsd);
        s_signup = findViewById(R.id.signup);
        t_login = findViewById(R.id.logintc);

        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_SignIn(emailBox.getText().toString(), passBox.getText().toString());
            }
        });

        s_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), SignUp.class), 4000);
            }
        });

        t_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_SignIn(emailBox.getText().toString(), passBox.getText().toString());
            }
        });
    }

    private void t_SignIn(String email, String password) {
        auth.signInWithEmailAndPassword("giaovien1@gmail.com", "password")
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        user = auth.getCurrentUser();
                        startActivity(new Intent(this, GiaoVienXemDanhSachLop.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void s_SignIn(String email, String password) {
        auth.signInWithEmailAndPassword("sinhvien4@gmail.com", "password")
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        user = auth.getCurrentUser();
                        startActivity(new Intent(this, SinhVienXemDanhSachLop.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                    }
                });
    }
}