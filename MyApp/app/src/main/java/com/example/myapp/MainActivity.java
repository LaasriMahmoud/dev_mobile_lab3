package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Clés Intent (évite les fautes de frappe)
    public static final String EXTRA_FULL_NAME = "extra_full_name";
    public static final String EXTRA_EMAIL     = "extra_email";
    public static final String EXTRA_PHONE     = "extra_phone";
    public static final String EXTRA_ADDRESS   = "extra_address";
    public static final String EXTRA_CITY      = "extra_city";

    private EditText etFullName, etEmail, etPhone, etAddress, etCity;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupActions();
    }

    private void bindViews() {
        etFullName = findViewById(R.id.fullName);
        etEmail    = findViewById(R.id.userEmail);
        etPhone    = findViewById(R.id.telephone);
        etAddress  = findViewById(R.id.address);
        etCity     = findViewById(R.id.city);
        btnSend    = findViewById(R.id.btnSubmit);
    }

    private void setupActions() {
        btnSend.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {
        // Lecture des champs
        String fullName = getText(etFullName);
        String email    = getText(etEmail);
        String phone    = getText(etPhone);
        String address  = getText(etAddress);
        String city     = getText(etCity);

        // Reset erreurs
        clearErrors();

        // Validation
        if (fullName.isEmpty()) {
            etFullName.setError("Champ obligatoire");
            etFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Champ obligatoire");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email invalide");
            etEmail.requestFocus();
            return;
        }

        // Navigation + passage de données
        Intent intent = new Intent(this, Screen2Activity.class);
        intent.putExtra(EXTRA_FULL_NAME, fullName);
        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_PHONE, phone);
        intent.putExtra(EXTRA_ADDRESS, address);
        intent.putExtra(EXTRA_CITY, city);

        startActivity(intent);
        Toast.makeText(this, "Données envoyées ✅", Toast.LENGTH_SHORT).show();
    }

    private String getText(EditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }

    private void clearErrors() {
        etFullName.setError(null);
        etEmail.setError(null);
        etPhone.setError(null);
        etAddress.setError(null);
        etCity.setError(null);
    }
}