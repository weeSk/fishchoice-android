package com.tecnatox.fishchoice.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.tecnatox.fishchoice.fish.Fish;

import com.tecnatox.fishchoice.fish.Nutrient;
import com.tecnatox.fishchoice.fish.User;
import com.tecnatox.fishchoice.fish.utils.Units;
import com.tecnatox.fishchoice.views.FishToCalculator;
import com.tecnatox.fishchoice.views.FoodSelector;
import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.login.activity.LoginActivity;
import com.tecnatox.fishchoice.login.helper.SQLiteHandler;
import com.tecnatox.fishchoice.login.helper.SessionManager;


import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout,btnStart;
    private EditText etWeight;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        etWeight = findViewById(R.id.weight);
        btnStart = findViewById(R.id.btnStart);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        //Create user
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new User(Float.parseFloat(etWeight.toString()));
            }
        });

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        //Create instance of Fish
        ArrayList<Nutrient> n = new ArrayList<>();
        n.add(new Nutrient("Protein", 24040000, Units.ugkg ));
        n.add(new Nutrient("EPA and DHA acids", 80000, Units.ugkgbw ));
        new Fish("Tuna", "Es un buen pescao", (float)0.87, n);
        new Fish("Shrimps and prawns", "Es un buenillo pescaillo", (float)0.5, n);
        new Fish("Squid", "Es un buenillo pescaillo", (float)0.5, n);
        new Fish("Alaska Pollock", "Es un buenillo pescaillo", (float)0.5, n);
        new Fish("Canned Sardine", "Es un buenillo pescaillo", (float)0.5, n);


        startActivity(new Intent(this, FishToCalculator.class));
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
