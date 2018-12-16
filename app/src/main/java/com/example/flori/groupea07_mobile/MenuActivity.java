package com.example.flori.groupea07_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button _menu_login, _menu_register, _menu_objects, _menu_users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        _menu_login = (Button) findViewById(R.id.menu_login);
        _menu_login.setOnClickListener(this);

        _menu_register = (Button) findViewById(R.id.menu_register);
        _menu_register.setOnClickListener(this);

        _menu_objects = (Button) findViewById(R.id.menu_objects);
        _menu_objects.setOnClickListener(this);

        _menu_users = (Button) findViewById(R.id.menu_users);
        _menu_users.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch(v.getId()){
            case R.id.menu_login :
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_register :
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_objects :
                //Log.w("test","Ca marche pas");
                intent = new Intent(this, AuctionedObjectActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_users :
                intent = new Intent(this,MemberActivity.class);
                startActivity(intent);
                break;
        }
    }

}
