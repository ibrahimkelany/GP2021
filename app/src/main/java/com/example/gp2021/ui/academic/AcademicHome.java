package com.example.gp2021.ui.academic;
//Test Kelly2
//Test Kelly2
//Test Kelly2
//Test Kelly2
//Test Ahmed
//Test Ibrahim
//Test Islam
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gp2021.R;
import com.example.gp2021.ui.academic.Fragment_Academic_Home;

import com.example.gp2021.ui.instructor.FragmentProfile;
import com.example.gp2021.ui.instructor.fragmentMail;
import com.example.gp2021.ui.login.LoginViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AcademicHome extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener {
    private ConstraintLayout constraintLayout;
    private LoginViewModel loginViewModel;
    GoogleSignInClient mGoogleSignInClient;
    ChipNavigationBar navView;
    public static GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_home);




        constraintLayout = findViewById(R.id.academicHome);

        navView = findViewById(R.id.bottom_navigation2);
        navView.setOnItemSelectedListener(this);

        //  navView.setItemSelected(0,true);


        LoadFragment(new Fragment_Academic_Home());




    }

    private void LoadFragment(Fragment frag)
    {
        if(frag!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag).commit();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Fragment error",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.academic_bottom_menu,menu);
        return true;
    }

    @Override
    public void onItemSelected(int i) {
        Fragment fragment=null;
        switch (i) {


            case R.id.academic_page_1: //Home
                //      navView.setSelectedItemId(R.id.shop);
                Bundle extras = getIntent().getExtras();

//                GoogleSignInAccount account = (GoogleSignInAccount) extras.get(EXTRA_MESSAGE);
                       /*  Intent i3 = new Intent(getApplicationContext(), Instructor_Home.class);
                         i3.putExtra(EXTRA_MESSAGE, account);

                         startActivity(i3);
                         overridePendingTransition(0, 0);

                         finish();

*/
                fragment=new Fragment_Academic_Home();
                break;

            case R.id.academic_page_2: //Mail
                

            //  fragment=new fragmentMail();
                break;
            case R.id.academic_page_3: //Export
                // navView.setSelectedItemId(R.id.Cart);

                fragment=new ExportGrades();

               // fragment=new fragmentEx();
                break;


            case R.id.academic_page_4: //profile
             fragment=new FragmentProfile();

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
