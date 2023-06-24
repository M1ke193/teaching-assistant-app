package com.example.teaching_assistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teaching_assistant.Model_DB.LopModel;
import com.example.teaching_assistant.Model_DB.SinhVienModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText ten, email, pass, pass1;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ten = findViewById(R.id.dkname);
        email = findViewById(R.id.dkemail);
        pass = findViewById(R.id.dkpassword);
        pass1 = findViewById(R.id.dkpassword2);

        bt = findViewById(R.id.dk);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.getText().toString().equals(pass1.getText().toString())) {
                    SignUp(ten.getText().toString(), email.getText().toString(), pass.getText().toString());
                }
                else Toast.makeText(v.getContext(), "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void SignUp(String ten, String email, String pass) {
        Log.d("test", "SignUp: ");
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();

                    SinhVienModel sinhVienMoi = new SinhVienModel(ten, email);
                    db.collection("Sinhvien").document(user.getUid()).set(sinhVienMoi).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}