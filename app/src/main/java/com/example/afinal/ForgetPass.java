package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPass extends AppCompatActivity implements View.OnClickListener {
    private Button btnreset;
    private ProgressBar rsprogres;
    private EditText formreset;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        btnreset = findViewById(R.id.btnreset);
        rsprogres = findViewById(R.id.rsprogres);
        formreset = findViewById(R.id.formreset);

        mAuth = FirebaseAuth.getInstance();
        btnreset.setOnClickListener(this);

}

    @Override
    public void onClick(View view) {
        resetPassword();

    }

    private void resetPassword() {
        String email = formreset.getText().toString().trim();

        if(email.isEmpty()){
            formreset.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            formreset.setError("Masukkan email dengan benar!");
            formreset.requestFocus();
            return;
        }

        rsprogres.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPass.this, "silahkan cek email anda untuk reset password!", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ForgetPass.this, "ada yang salah, silahkan coba lagi", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}