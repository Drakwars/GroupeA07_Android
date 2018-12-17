package com.example.flori.groupea07_mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.Service.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button _btn_login, _btn_reset;
    private EditText _et_username, _et_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _btn_login = (Button) findViewById(R.id.bt_login_login);
        _btn_login.setOnClickListener(this);

        _btn_reset = (Button) findViewById(R.id.bt_log_reset);
        _btn_reset.setOnClickListener(this);


        _et_username = (EditText) findViewById(R.id.et_log_username);
        _et_pwd = (EditText) findViewById(R.id.et_log_password);


        // Toolbar
        Toolbar toolBar = (Toolbar) findViewById(R.id.login_tool_bar);
        toolBar.setTitle(getResources().getText(R.string.txt_login));
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.bt_login_login :
                authentification();
                break;

            case R.id.bt_log_reset :
                resetData();
                break;
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        //et_username.setText("test");
    }

    private void resetData(){
        _et_username.setText("");
        _et_pwd.setText("");
    }

    private void authentification(){

        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Member>> call = service.groupList();

        call.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                Boolean found = false;

                for(Member m : response.body()){
                    if(m.getUsername().toLowerCase().equals(_et_username.getText().toString().toLowerCase()) && m.getUserPwd().equals(_et_pwd.getText().toString())){

                        Toast toast = Toast.makeText(getApplicationContext(),"Login in...",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();


                        found = true;

                        SharedPreferences loginData = getSharedPreferences("memberInfo", Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = loginData.edit();


                        editor.putInt("idUser", m.getIdUser());
                        editor.putString("userName", m.getUsername());
                        editor.putInt("admin", m.getUserAdmin());
                        editor.apply();
                        setResult(LoginActivity.RESULT_OK);
                        finish();
                        break;
                    }

                }

                if(found==false){

                    Toast toast = Toast.makeText(getApplicationContext(),"Username and password doesn't match !",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
