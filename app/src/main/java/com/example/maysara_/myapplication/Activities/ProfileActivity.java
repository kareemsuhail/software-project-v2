package com.example.maysara_.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maysara_.myapplication.Controllers.ProfileController;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.R;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orm.SugarContext;

import okhttp3.OkHttpClient;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ProfileController controller = new ProfileController(getApplicationContext());
        if (!controller.isNewUser()) {
            moveToBudgetActivity();
            finish();
        }
        setContentView(R.layout.profile_activity);
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        SugarContext.init(this);
        final EditText name = findViewById(R.id.name);
        final EditText phone = findViewById(R.id.phone);
        Button save = findViewById(R.id.profile_data);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!DialogHelper.validateInput(name, phone)) {
                    DialogHelper.displayError("Please enter valid data", ProfileActivity.this);
                    return;
                }
                controller.CreateProfile(name.getText().toString(), 1, phone.getText().toString());
                moveToBudgetActivity();
            }
        });

    }

    public void moveToBudgetActivity() {
        Intent goToNextActivity = new Intent(getApplicationContext(), BudgetActivity.class);
        startActivity(goToNextActivity);
        finish();
    }
}
