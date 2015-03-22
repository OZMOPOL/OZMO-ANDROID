package com.ozu.ozmo.ozmopol;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class LoginActivity extends ActionBarActivity {

    String userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOzmopolLogo();
        setUsernameTextBox();
        setPasswordTextBox();
        setLoginButton();
    }

    public void setOzmopolLogo(){
        ImageView ozmopolLogo = (ImageView)findViewById(R.id.ozmopol_logo);
        ozmopolLogo.setImageResource(R.drawable.ozmopol);
    }

    public void setUsernameTextBox(){
        EditText userNameBox = (EditText)findViewById(R.id.login_info);
    }

    public void setPasswordTextBox(){
        EditText passwordBox = (EditText)findViewById(R.id.password_info);
    }

    public void setLoginButton(){
        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO implement the login func. to here
            }
        });
    }
}
