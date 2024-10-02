package com.example.assignment_3;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    // Regular expressions
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d).{8,}$";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        signupButton = findViewById(R.id.signup);

        signupButton.setOnClickListener(v -> validateFields());
    }

    private void validateFields() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate first name
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("First name is required");
            return;
        }

        // Validate last name
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Last name is required");
            return;
        }

        // Validate email using regex
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            emailEditText.setError("Invalid email format");
            return;
        }

        // Validate password using regex
        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            passwordEditText.setError("Password must be at least 8 characters, include an uppercase letter, and a number");
            return;
        }

        // Validate that passwords match
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return;
        }

        // All validations passed
        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
    }
}
