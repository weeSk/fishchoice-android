package com.tecnatox.fishchoice.views;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.fish.Fish;
import com.tecnatox.fishchoice.fish.FishLibrary;
import com.tecnatox.fishchoice.fish.Nutrient;
import com.tecnatox.fishchoice.fish.User;
import com.tecnatox.fishchoice.fish.utils.RecyclerUserSelection;
import com.tecnatox.fishchoice.login.activity.LoginActivity;
import com.tecnatox.fishchoice.login.helper.SQLiteHandler;
import com.tecnatox.fishchoice.login.helper.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class FishToCalculator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView txtName;
    private TextView txtEmail;
    private TextView ins;
    FishLibrary fl;
    private Button calc;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_activity);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

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

        //Sidebar Code
        DrawerLayout drawer = (DrawerLayout)  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton btnAdd = findViewById(R.id.add);
        ins = findViewById(R.id.instructions);

        View headerView = navigationView.getHeaderView(0);
        txtName = (TextView) headerView.findViewById(R.id.sidebar_name);
        txtEmail = (TextView) headerView.findViewById(R.id.sidebar_mail);

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        fl = FishLibrary.getInstance();
        calc = findViewById(R.id.calculate);
        if(fl.userLibrary.isEmpty()){
            ins.setText("Add your weekly intake of fish before calculate");
        }else{
            initRecyclerView();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FishToCalculator.this, FoodSelector.class);
                startActivityForResult(intent, 1);
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishToCalculator.this, CalculatorResults.class));
            }
        });

        //Import sample of fish programmatically
        importFish();
        importNutrientsRequirements();
    }

    private void importNutrientsRequirements() {
        Hashtable<Integer, Float> nr = new Hashtable<>();

        //mg/d unit
        nr.put(0,62000.00f*7 );  //0; Protein
        nr.put(1,250.00f*7);    //1; EPA and DHA acids
        nr.put(2,0.150f*7);     //2; lodine
        nr.put(3,0.070f*7);     //3; Selenium
        nr.put(4,0.00f*7);      //4; Omega-3
        nr.put(5,0.00f*7);      //5; Cholesterol
        nr.put(6,0.015f*7);     //6; Vitamin D
        nr.put(7,8.79f*7);      //7; Vitamin B
        nr.put(8,0.00f*7);      //8; Omega-6/omega-3
        nr.put(9,750.00f*7);    //9; Calcium
        nr.put(10,6.00f*7);     //10; Iron

        fl.setNutrientsRequirements(nr);
    }

    private void importFish() {
        //Create instance of Fish

        //Nutrients of Tuna
        ArrayList<Nutrient> n = new ArrayList<>();
        n.add(new Nutrient(0,"Protein", 23900.0000f));
        n.add(new Nutrient(1,"EPA and DHA acids", 840.0000f));
        n.add(new Nutrient(2,"lodine", 0.0250f));
        n.add(new Nutrient(3,"Selenium", 0.0830f));
        n.add(new Nutrient(4,"Omega-3", 840.0000f));
        n.add(new Nutrient(5,"Cholesterol", 45.0000f));
        n.add(new Nutrient(6,"Vitamin D", 0.00064f));
        n.add(new Nutrient(7,"Vitamin B", 0.54500f));
        n.add(new Nutrient(8,"Omega-6", 160.00000f));
        n.add(new Nutrient(9,"Calcium", 12.00000f));
        n.add(new Nutrient(10,"Iron", 1.40000f));


        new Fish("Tuna", "Tuna description", n);
        new Fish("Shrimps and prawns", "Shrimps and prawns description", n);
        new Fish("Squid", "Squid description", n);
        new Fish("Alaska Pollock", "Alaska Pollock description", n);
        new Fish("Canned Sardine", "Canned Sardine description", n);

        User test = new User(80);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.fish_selection_container);
        RecyclerUserSelection adapter = new RecyclerUserSelection(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                if(!fl.userLibrary.isEmpty()) {
                    ins.setVisibility(View.GONE);
                }else{
                    ins.setVisibility(View.VISIBLE);
                }
                initRecyclerView();

            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_top_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.menu_logout){
            logoutUser();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(FishToCalculator.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
