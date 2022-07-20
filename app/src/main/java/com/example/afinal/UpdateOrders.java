package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateOrders extends AppCompatActivity {
    private EditText dnama, dalamat, dharga, dpaket, dstatus, dberat;
    private String dNAMA, dALAMAT, dPAKET, dHARGA, dSTATUS, dBERAT;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_orders);
        Order order = (Order) getIntent().getSerializableExtra("Order");

        // getting our instance from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        // initializing our edittext and buttons
        dnama = findViewById(R.id.dNama);
        dalamat = findViewById(R.id.dAlamat);
        dharga = findViewById(R.id.dharga);
        dberat = findViewById(R.id.dberat);
        dstatus = findViewById(R.id.dstatus);
       dpaket = findViewById(R.id.dpaket);
        // creating variable for button
        Button updateCOurseBtn = findViewById(R.id.idBtnUpdateCourse);
        Button deleteBtn = findViewById(R.id.idBtnDeleteCourse);

        dnama.setText(order.getNama());
        dalamat.setText(order.getAlamat());
        dstatus.setText(order.getStatus());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to delete the course.
                deleteCourse(order);
            }
        });

        updateCOurseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dNAMA = dnama.getText().toString();
                dALAMAT = dalamat.getText().toString();
                dBERAT = dberat.getText().toString();
                dHARGA = dharga.getText().toString();
                dSTATUS = dstatus.getText().toString();
                dPAKET = dpaket.getText().toString();


                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(dNAMA)) {
                    dnama.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(dALAMAT)) {
                    dalamat.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(dBERAT)) {
                    dberat.setError("Please enter Course Duration");
                } else if (TextUtils.isEmpty(dHARGA)) {
                    dharga.setError("Please enter Course Duration");
                } else if (TextUtils.isEmpty(dSTATUS)) {
                    dstatus.setError("Please enter Course Duration");
                } else if (TextUtils.isEmpty(dPAKET)) {
                    dpaket.setError("Please enter Course Duration");
                } else {
                    // calling a method to update our course.
                    // we are passing our object class, course name,
                    // course description and course duration from our edittext field.
                    updateCourses(order, dNAMA, dALAMAT, dBERAT, dHARGA, dSTATUS, dPAKET);
                }
            }
        });
    }
    private void deleteCourse(Order order) {
        // below line is for getting the collection
        // where we are storing our courses.
        db.collection("Order").
                // after that we are getting the document
                // which we have to delete.
                        document(order.getId()).

                // after passing the document id we are calling
                // delete method to delete this document.
                        delete().
                // after deleting call on complete listener
                // method to delete this data.
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // inside on complete method we are checking
                        // if the task is success or not.
                        if (task.isSuccessful()) {
                            // this method is called when the task is success
                            // after deleting we are starting our MainActivity.
                            Toast.makeText(UpdateOrders.this, "Order telah dihapus", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UpdateOrders.this, OrderProses.class);
                            startActivity(i);
                        } else {
                            // if the delete operation is failed
                            // we are displaying a toast message.
                            Toast.makeText(UpdateOrders.this, "Gagal hapus order", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void updateCourses(Order order, String NAMA, String PAKET, String ALAMAT, String HARGA, String STATUS, String BERAT) {
        // inside this method we are passing our updated values
        // inside our object class and later on we
        // will pass our whole object to firebase Firestore.
        Order updatedCourse = new Order(dNAMA, dPAKET, dALAMAT, dHARGA, dSTATUS, dBERAT);

        // after passing data to object class we are
        // sending it to firebase with specific document id.
        // below line is use to get the collection of our Firebase Firestore.
        db.collection("Order").
                // below line is use toset the id of
                // document where we have to perform
                // update operation.
                        document(order.getId()).

                // after setting our document id we are
                // passing our whole object class to it.
                        set(updatedCourse).

                // after passing our object class we are
                // calling a method for on success listener.
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // on successful completion of this process
                        // we are displaying the toast message.
                        Toast.makeText(UpdateOrders.this, "Data berhasil diupdate.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            // inside on failure method we are
            // displaying a failure message.
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateOrders.this, "gagal update data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}