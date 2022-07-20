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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText rformnama, rformalamat, rformnomor, rformpass , rformemail, rformtype;
    private Button rformbtnregis;
    private TextView rbtnlogin;
    private ProgressBar rproges;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        rformnama = findViewById(R.id.rformnama);
        rformalamat = findViewById(R.id.rformalamat);
        rformpass = findViewById(R.id.rformpass);
        rformbtnregis = findViewById(R.id.rbtnregis);
        rformemail = findViewById(R.id.rformemail);
        rformnomor = findViewById(R.id.rformhp);
        rbtnlogin = findViewById(R.id.rbtnlogin);
        rproges = findViewById(R.id.rprogres);
        rformtype = findViewById(R.id.rformtype);

        rbtnlogin.setOnClickListener(this);
        rformbtnregis.setOnClickListener(this);


}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rbtnlogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.rbtnregis:
                rformbtnregis();
                break;
        }

    }

    private void rformbtnregis() {
        String email = rformemail.getText().toString().trim();
        String nomor = rformnomor.getText().toString().trim();
        String pass = rformpass.getText().toString().trim();
        String alamat = rformalamat.getText().toString().trim();
        String nama = rformnama.getText().toString().trim();
        String type = rformtype.getText().toString().trim();

        if (email.isEmpty()){
            rformemail.setError("Email harus diisi!");
            rformemail.requestFocus();
            return;
        }
        if (nomor.isEmpty()){
            rformnomor.setError("Nomor harus diisi!");
            rformnomor.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            rformpass.setError("Password harus diisi!");
            rformpass.requestFocus();
            return;
        }
        if (alamat.isEmpty()){
            rformalamat.setError("Alamat harus diisi!");
            rformalamat.requestFocus();
            return;
        }

        if (nama.isEmpty()){
            rformnama.setError("Nama harus diisi!");
            rformnama.requestFocus();
            return;
        }
        if (type.isEmpty()){
            rformtype.setError("Ketikkan pelanggan");
            rformtype.requestFocus();
            return;
        }
        if (!type.equals("pelanggan")) {
            rformtype.setError("Inputan salah!");
            rformtype.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            rformemail.setError("Input format email dengan benar");
            rformemail.requestFocus();
            return;
        }
        if (pass.length() < 6){
            rformpass.setError("Password minimal 6 karakter");
            rformpass.requestFocus();
            return;
        }
        rproges.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    User user = new User(nama, email, nomor, alamat, type);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Akun berhasil didaftarkan", Toast.LENGTH_LONG).show();
                                        rproges.setVisibility(View.VISIBLE);


                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Akun gagal didaftarkan, coba lagi", Toast.LENGTH_LONG).show();
                                        rproges.setVisibility(View.GONE);

                                    }
                                }
                            });

                }else {
                    Toast.makeText(RegisterActivity.this, "Akun gagal didaftarkan", Toast.LENGTH_LONG).show();
                    rproges.setVisibility(View.GONE);

                }
            }
        });

    }
}
