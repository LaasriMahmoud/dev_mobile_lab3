package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Screen2Activity extends AppCompatActivity {

    private TextView tvSummary;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        tvSummary = findViewById(R.id.summary);
        btnBack = findViewById(R.id.btnBack);

        displayReceivedData();

        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void displayReceivedData() {
        String fullName = normalize(getIntent().getStringExtra(MainActivity.EXTRA_FULL_NAME));
        String email    = normalize(getIntent().getStringExtra(MainActivity.EXTRA_EMAIL));
        String phone    = normalize(getIntent().getStringExtra(MainActivity.EXTRA_PHONE));
        String address  = normalize(getIntent().getStringExtra(MainActivity.EXTRA_ADDRESS));
        String city     = normalize(getIntent().getStringExtra(MainActivity.EXTRA_CITY));

        String content = buildSummary(fullName, email, phone, address, city);
        tvSummary.setText(content);
    }

    private String buildSummary(String fullName, String email, String phone, String address, String city) {
        return "Récapitulatif\n\n"
                + "Nom complet : " + fullName + "\n"
                + "Email : " + email + "\n"
                + "Téléphone : " + phone + "\n"
                + "Adresse : " + address + "\n"
                + "Ville : " + city;
    }

    private String normalize(String value) {
        if (value == null) return "—";
        value = value.trim();
        return value.isEmpty() ? "—" : value;
    }
}