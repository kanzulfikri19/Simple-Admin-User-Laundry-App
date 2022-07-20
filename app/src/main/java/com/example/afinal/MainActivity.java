package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton logoutbtn;
    private TextView namauser, nomoruser;
    private Button btnreguler, btnekspres, btntakehome;
    private String namaus, alamatus, typeus, nomorus;

    private FirebaseAuth mAuth;
    private FirebaseUser user ;
    private DatabaseReference reference ;

    private String userID ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutbtn = findViewById(R.id.btnlogout);

        btnreguler = findViewById(R.id.btnreguler);


        btnreguler.setOnClickListener(this);




        mAuth = FirebaseAuth.getInstance();

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();;
                startActivity(new Intent( MainActivity.this, LoginActivity.class));
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        final TextView namaTextview = findViewById(R.id.MnamaUser);
        final TextView nomorTextview = findViewById(R.id.mNomorUser);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                namaus = userProfile.nama;
                nomorus = userProfile.nomor;
                typeus = userProfile.type;
                alamatus = userProfile.alamat;

                if(userProfile != null){
                    String nama = userProfile.nama;
                    String nomor = userProfile.nomor;

                    namaTextview.setText(nama);
                    nomorTextview.setText(nomor);
                }
                if (typeus.equals("pelanggan")){
                    btnreguler.setOnClickListener(view ->{
                        if(!namaus.equals("admin")){
                            Intent intent = new Intent(MainActivity.this, OrderProses.class);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(MainActivity.this, OrderProsesAdmin.class);
                            startActivity(intent);
                        }
                    } );



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "ada yang salah, silahkan coba lagi", Toast.LENGTH_LONG).show();


            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}

    //private void reguleractivity() {



    //}
    //}