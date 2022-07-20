package com.example.afinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderProses extends AppCompatActivity implements View.OnClickListener {
    // creating variables for our edit text
    private EditText nama, alamat, harga, paket, status, berat;

    // creating variable for button
    private Button submitCourseBtn,viewCoursesBtn;

    // creating a strings for storing
    // our values from edittext fields.
    private String NAMA, ALAMAT, PAKET, HARGA, STATUS, BERAT;

    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_proses);





            // getting our instance
            // from Firebase Firestore.
            db = FirebaseFirestore.getInstance();

            // initializing our edittext and buttons
            nama = findViewById(R.id.Nama);
            alamat = findViewById(R.id.Alamat);
            harga = findViewById(R.id.harga);
            berat = findViewById(R.id.berat);
            status = findViewById(R.id.status);
            paket = findViewById(R.id.paket);
        viewCoursesBtn = findViewById(R.id.idBtnViewCourses);
        viewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity on button click
                Intent i = new Intent(OrderProses.this,OrderDetailsU.class);
                startActivity(i);
            }
        });


            submitCourseBtn = findViewById(R.id.idBtnSubmitCourse);

            // adding on click listener for button
            submitCourseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // getting data from edittext fields.
                    NAMA = nama.getText().toString();
                    ALAMAT = alamat.getText().toString();
                    BERAT = berat.getText().toString();
                    HARGA = harga.getText().toString();
                    STATUS = status.getText().toString();
                    PAKET = paket.getText().toString();


                    // validating the text fields if empty or not.
                    if (TextUtils.isEmpty(NAMA)) {
                        nama.setError("Inputkan nama");
                    } else if (TextUtils.isEmpty(ALAMAT)) {
                        alamat.setError("Inputkan detail alamat");
                    } else if (TextUtils.isEmpty(BERAT)) {
                        berat.setError("Inputkan berat cucian");
                    } else if (TextUtils.isEmpty(HARGA)) {
                        harga.setError("Inputkan penghitungan harga");
                    } else if (TextUtils.isEmpty(STATUS)) {
                        status.setError("Inputkan OnOrder");
                    } else if (TextUtils.isEmpty(PAKET)) {
                        paket.setError("Inputkan jenis paket");
                    } else {
                        // calling method to add data to Firebase Firestore.
                        addDataToFirestore(NAMA, ALAMAT, BERAT, HARGA, STATUS, PAKET);
                    }
                }
            });
        }

        private void addDataToFirestore(String NAMA, String PAKET, String ALAMAT, String HARGA, String STATUS, String BERAT) {

            // creating a collection reference
            // for our Firebase Firetore database.
            CollectionReference dbOrders = db.collection("Order");

            // adding our data to our courses object class.
            Order order = new Order(NAMA, PAKET, ALAMAT, HARGA, STATUS, BERAT);

            // below method is use to add data to Firebase Firestore.
            dbOrders.add(order).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    // after the data addition is successful
                    // we are displaying a success toast message.
                    Toast.makeText(OrderProses.this, "pesanan telah diperbarui", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // this method is called when the data addition process is failed.
                    // displaying a toast message when data addition is failed.
                    Toast.makeText(OrderProses.this, "pesanan gagal diperbarui \n" + e, Toast.LENGTH_SHORT).show();
                }
            });
        }


    @Override
    public void onClick(View view) {

    }
}



