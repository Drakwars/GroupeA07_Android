package com.example.flori.groupea07_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button _menu_login, _menu_register, _menu_objects, _menu_users, _menu_logout, _menu_sold_objects, _menu_addobjects, _menu_to_go_website;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        _menu_login = (Button) findViewById(R.id.menu_login);
        _menu_login.setOnClickListener(this);

        _menu_register = (Button) findViewById(R.id.menu_register);
        _menu_register.setOnClickListener(this);

        _menu_addobjects = (Button) findViewById(R.id.menu_addObject);
        _menu_addobjects.setOnClickListener(this);
        _menu_addobjects.setVisibility(View.GONE);

        _menu_objects = (Button) findViewById(R.id.menu_objects);
        _menu_objects.setOnClickListener(this);
        _menu_objects.setVisibility(View.GONE);

        _menu_users = (Button) findViewById(R.id.menu_users);
        _menu_users.setOnClickListener(this);
        _menu_users.setVisibility(View.GONE);

        _menu_logout = (Button) findViewById(R.id.menu_logout);
        _menu_logout.setOnClickListener(this);
        _menu_logout.setVisibility(View.GONE);

        _menu_sold_objects = (Button) findViewById(R.id.menu_sold_objects);
        _menu_sold_objects.setOnClickListener(this);

        _menu_to_go_website = (Button) findViewById(R.id.menu_go_to_website);
        _menu_to_go_website.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        toolbar.setTitle("Please login or register");


    }

    // Manage all buttons.onClick
    @Override
    public void onClick(View v) {


        SharedPreferences loginData = getSharedPreferences("memberInfo", Context.MODE_PRIVATE);
        Intent intent;
        switch(v.getId()){

            //Switch to LoginActivity
            case R.id.menu_login :
                intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent,1);
                break;

            //Switch to registerActivity
            case R.id.menu_register :
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

            //Switch to AddObjectActivity
            case R.id.menu_addObject :
                intent = new Intent(this, AddObjectActivity.class);
                startActivity(intent);

                break;

            //Switch to AuctionedObjectActivity
            case R.id.menu_objects :
                intent = new Intent(this, AuctionedObjectActivity.class);
                startActivity(intent);
                break;

            //Switch to SoldObjActivity
            case R.id.menu_sold_objects:
                intent = new Intent(this, SoldObjActivity.class);
                startActivity(intent);
                break;

            //Switch to MembersActivity
            case R.id.menu_users :
                intent = new Intent(this,MemberActivity.class);

                int admin = loginData.getInt("admin",0);

                if (admin == 0) { Toast.makeText(this,"Access denied",Toast.LENGTH_LONG).show();}
                else {startActivity(intent); }
                break;

            case R.id.menu_logout:

                SharedPreferences.Editor editor = loginData.edit();
                editor.clear();
                editor.commit();

                Toast toast = Toast.makeText(getApplicationContext(),"Successfully logged out !",Toast.LENGTH_LONG);//toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

                _menu_logout.setVisibility(View.GONE);
                _menu_login.setVisibility(View.VISIBLE);
                _menu_register.setVisibility(View.VISIBLE);
                _menu_addobjects.setVisibility(View.GONE);
                _menu_objects.setVisibility(View.GONE);
                _menu_users.setVisibility(View.GONE);

                String logUsername = loginData.getString("userName",null);
                toolbar.setTitle("Please login or register");

                break;

            case R.id.menu_go_to_website:
                Uri webpage = Uri.parse("https://groupea07.azurewebsites.net");
                intent = new Intent(Intent.ACTION_VIEW, webpage);

                if (intent.resolveActivity(getPackageManager()) != null) { startActivity(intent);}
                else{Toast.makeText(getApplicationContext(),"Web site doesn't exist", Toast.LENGTH_LONG).show();}

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                _menu_logout.setVisibility(View.VISIBLE);
                _menu_login.setVisibility(View.GONE);
                _menu_register.setVisibility(View.GONE);
                _menu_addobjects.setVisibility(View.VISIBLE);
                _menu_users.setVisibility(View.VISIBLE);
                _menu_objects.setVisibility(View.VISIBLE);

                SharedPreferences loginData = getSharedPreferences("memberInfo", Context.MODE_PRIVATE);
                String logUsername = loginData.getString("userName",null);
                toolbar.setTitle(logUsername);
            }
        }
    }
}
