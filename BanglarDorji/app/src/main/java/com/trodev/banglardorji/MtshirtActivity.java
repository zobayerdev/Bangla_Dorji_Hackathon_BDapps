package com.trodev.banglardorji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MtshirtActivity extends AppCompatActivity {

    private AutoCompleteTextView type, length, width, color, fabric, gsm;
    private EditText home, mobile, quan;
    private MaterialButton order, cart;


    DatabaseReference databaseReference, databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtshirt);

        databaseReference = FirebaseDatabase.getInstance().getReference("Order").child("t-shirt");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Cart").child("t-shirt");
        //.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        type = findViewById(R.id.filled_exposed);
        length = findViewById(R.id.filled_exposed1);
        width = findViewById(R.id.filled_exposed2);
        color = findViewById(R.id.filled_exposed3);
        fabric = findViewById(R.id.filled_exposed4);
        gsm = findViewById(R.id.filled_exposed5);
        home = findViewById(R.id.homeEt);
        mobile = findViewById(R.id.phoneEt);
        quan = findViewById(R.id.quantity);
        cart = findViewById(R.id.cart_btn);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartData();
            }
        });

        // ###################################################
        // ##################################################
        String[] type = {"Oversized", "Drop Shoulder", "Slim Fit"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.drop_down_list, type
        );
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.filled_exposed);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MtshirtActivity.this, "you have selected " + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ###################################################
        // ##################################################
        String[] type1 = {" 25", " 26", " 27", " 28", " 29"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this, R.layout.drop_down_list, type1
        );

        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.filled_exposed1);
        autoCompleteTextView1.setAdapter(adapter1);

        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MtshirtActivity.this, "you have selected " + autoCompleteTextView1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ###################################################
        // ##################################################

        String[] type2 = {" 38", " 40", " 42", " 44", " 46"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this, R.layout.drop_down_list, type2
        );

        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.filled_exposed2);
        autoCompleteTextView2.setAdapter(adapter2);

        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MtshirtActivity.this, "you have selected " + autoCompleteTextView2.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ###################################################
        // ##################################################

        String[] type3 = {" Black", " White", " Red", " Deep Blue", " Pink"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(
                this, R.layout.drop_down_list, type3
        );

        AutoCompleteTextView autoCompleteTextView3 = findViewById(R.id.filled_exposed3);
        autoCompleteTextView3.setAdapter(adapter3);

        autoCompleteTextView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MtshirtActivity.this, "you have selected " + autoCompleteTextView3.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ###################################################
        // ##################################################

        String[] type4 = {" Cotton", " Polyester"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(
                this, R.layout.drop_down_list, type4
        );

        AutoCompleteTextView autoCompleteTextView4 = findViewById(R.id.filled_exposed4);
        autoCompleteTextView4.setAdapter(adapter4);

        autoCompleteTextView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MtshirtActivity.this, "you have selected " + autoCompleteTextView4.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ###################################################
        // ##################################################

        String[] type5 = {" 150", " 180", " 200", " 220"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(
                this, R.layout.drop_down_list, type5
        );

        AutoCompleteTextView autoCompleteTextView5 = findViewById(R.id.filled_exposed5);
        autoCompleteTextView5.setAdapter(adapter5);

        autoCompleteTextView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MtshirtActivity.this, "you have selected " + autoCompleteTextView5.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        order = findViewById(R.id.order_btn);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {

        String sizeT = type.getText().toString().trim();
        String lengthT = length.getText().toString().trim();
        String widthT = width.getText().toString().trim();
        String colorT = color.getText().toString().trim();
        String fab = fabric.getText().toString().trim();
        String gsmt = gsm.getText().toString().trim();
        String quant = quan.getText().toString().trim();
        String homeT = home.getText().toString().trim();
        String mobileT = mobile.getText().toString().trim();

        if (homeT.isEmpty()) {
            home.setError("Fill up address");
            home.requestFocus();
        }

        if (mobileT.isEmpty()) {
            mobile.setError("Fill up Mobile Number");
            mobile.requestFocus();
        }

        if (mobileT.isEmpty()) {
            quan.setError("Fill up Quantity");
        } else {

            // Its a time and date code show our user and admin
            // amra date and time init kore database e save korchi
            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            String date = currentDate.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            String time = currentTime.format(calForTime.getTime());

            String Key = databaseReference.push().getKey();
            Mentshirt mentshirt = new Mentshirt(sizeT, lengthT, widthT, colorT, fab, gsmt, quant, homeT, mobileT, date, time, FirebaseAuth.getInstance().getCurrentUser().getUid());
            databaseReference.child(Key).setValue(mentshirt);
            Toast.makeText(this, "Order Info is added! Check your order on Dashboard", Toast.LENGTH_LONG).show();
        }
    }


    private void cartData() {

        String sizeT = type.getText().toString().trim();
        String lengthT = length.getText().toString().trim();
        String widthT = width.getText().toString().trim();
        String colorT = color.getText().toString().trim();
        String fab = fabric.getText().toString().trim();
        String gsmt = gsm.getText().toString().trim();
        String quant = quan.getText().toString().trim();
        String homeT = home.getText().toString().trim();
        String mobileT = mobile.getText().toString().trim();

        if (homeT.isEmpty()) {
            home.setError("Fill up address");
            home.requestFocus();
        }

        if (mobileT.isEmpty()) {
            mobile.setError("Fill up Mobile Number");
            mobile.requestFocus();
        }

        if (mobileT.isEmpty()) {
            quan.setError("Fill up Quantity");
        } else {

            // Its a time and date code show our user and admin
            // amra date and time init kore database e save korchi
            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            String date = currentDate.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            String time = currentTime.format(calForTime.getTime());

            String Key = databaseReference2.push().getKey();
            Mentshirt mentshirt = new Mentshirt(sizeT, lengthT, widthT, colorT, fab, gsmt, quant, homeT, mobileT, date, time, FirebaseAuth.getInstance().getCurrentUser().getUid());
            databaseReference2.child(Key).setValue(mentshirt);
            Toast.makeText(this, "Order Info is added! Check your order on Dashboard", Toast.LENGTH_LONG).show();
        }
    }
}