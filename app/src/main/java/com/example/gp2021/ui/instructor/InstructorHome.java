package com.example.gp2021.ui.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gp2021.BuildConfig;
import com.example.gp2021.R;
import com.example.gp2021.ui.login.LoginViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.lang.reflect.Field;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class InstructorHome extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener {
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    private LoginViewModel loginViewModel;
    GoogleSignInClient mGoogleSignInClient;
    ChipNavigationBar navView;
    public static GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_home);
        getSupportActionBar().hide();



/*
        constraintLayout = findViewById(R.id.instructorHome);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        // setting enter fade animation duration to 3 seconds
        animationDrawable.setEnterFadeDuration(3000);
        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);*/
        navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(this);
        //  navView.setItemSelected(0,true);


        LoadFragment(new fragment1Home());


    }

    private void LoadFragment(Fragment frag) {
        if (frag != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frag).commit();
        } else {
            Toast.makeText(getApplicationContext(), "Fragment error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return true;
    }

    @Override
    public void onItemSelected(int i) {
        Fragment fragment = null;
        switch (i) {


            case R.id.page_1: //Home
                //      navView.setSelectedItemId(R.id.shop);
                Bundle extras = getIntent().getExtras();

//                GoogleSignInAccount account = (GoogleSignInAccount) extras.get(EXTRA_MESSAGE);
                       /*  Intent i3 = new Intent(getApplicationContext(), Instructor_Home.class);
                         i3.putExtra(EXTRA_MESSAGE, account);

                         startActivity(i3);
                         overridePendingTransition(0, 0);

                         finish();

*/
                //  fragment=new fragment1Home();
                fragment = new fragment1Home();
                break;

            case R.id.page_2: //Mail
                // navView.setSelectedItemId(R.id.Cart);
                Bundle extras2 = getIntent().getExtras();
                //  fragment=new InstractorScan();


//                GoogleSignInAccount account2 = (GoogleSignInAccount) extras2.get(EXTRA_MESSAGE);
                fragment = new fragmentMail();
                break;


            case R.id.page_3: //profile
                fragment = new FragmentProfile();

                break;


        }

        LoadFragment(fragment);
    }

    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }
}