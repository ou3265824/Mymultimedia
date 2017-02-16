package com.example.test.replace;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.replace.fragment.LoginFragment;
import com.example.test.replace.fragment.RegisterFragment;

import java.util.List;

public class ReplaceActivity extends AppCompatActivity {


    private LoginFragment loginfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);
        if (savedInstanceState==null){
            loginfragment = new LoginFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_replace, loginfragment).commit();
        }
        loginfragment.setOnclickListener(new LoginFragment.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"as",Toast.LENGTH_LONG).show();
                RegisterFragment registerFragment=new RegisterFragment();

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_replace, registerFragment).addToBackStack(null).commit();

            }
        });



    }
}
