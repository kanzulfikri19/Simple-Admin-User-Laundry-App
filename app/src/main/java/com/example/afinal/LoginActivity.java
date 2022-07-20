package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText formemail, formpass;
    private Button btnlogin;
    private TextView btnviwregist, btnlupa;
    private ProgressBar lprogres;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        formemail = findViewById(R.id.formEmail);
        formpass = findViewById(R.id.formPass);
        btnlogin = findViewById(R.id.btnLogin);
        btnlupa = findViewById(R.id.btnlupa);
        btnviwregist = findViewById(R.id.btnVRegist);
        lprogres = findViewById(R.id.lprogres);

        mAuth = FirebaseAuth.getInstance();

        btnviwregist.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        btnlupa.setOnClickListener(this);



}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnVRegist:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.btnlupa:
                startActivity(new Intent(this, ForgetPass.class));
                break;
        }
    }

    private void userLogin() {
        String email = formemail.getText().toString().trim();
        String pass = formpass.getText().toString().trim();

        if(email.isEmpty()){
            formemail.setError("Masukkan email!");
            formemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            formemail.setError("Masukkan email dengan banner!");
            formemail.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            formpass.setError("Password harus diisi!");
            formpass.requestFocus();
            return;
        }
        if (pass.length() < 6){
            formpass.setError("Password minimal 6 karakter");
            formpass.requestFocus();
            return;
        }

        lprogres.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "cek email untuk verifikasi akun", Toast.LENGTH_LONG).show();

                    }


                }else {
                    Toast.makeText(LoginActivity.this, "gagal login, cek akun anda!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}