package com.example.subhash.sqlitewithsearch.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.subhash.sqlitewithsearch.Database.DatabaseHandler;
import com.example.subhash.sqlitewithsearch.Model.DetailGetSet;
import com.example.subhash.sqlitewithsearch.R;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;

    EditText et_name, et_age, et_contact,et_email;
    TextView submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler=new DatabaseHandler(MainActivity.this);
        init();

    }



    public void saveData(View view) {

        String emailmatcher ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(et_name.getText().toString().equals("")){
            et_name.requestFocus();
            et_name.setError("Please Enter name");
        } else if(et_age.getText().toString().equals("")){
            et_age.requestFocus();
            et_age.setError("Please Enter age");
        } else if(et_contact.getText().toString().equals("")){
            et_contact.requestFocus();
            et_contact.setError("Please Enter Contact");
        } else if(et_email.getText().toString().equals("")){
            et_email.requestFocus();
            et_email.setError("Please Enter Email");
        } else {
            if(!et_email.getText().toString().matches(emailmatcher)){
                et_email.requestFocus();
                et_email.setError("Please Enter Valid Email");
            } else {
                submit();
            }

        }
}

    private void init() {
        et_name = findViewById(R.id.name);
        et_age = findViewById(R.id.age);
        et_contact = findViewById(R.id.contact_no);
        et_email = findViewById(R.id.email);
//        submit = findViewById(R.id.submit);

    }

    private void submit() {

        DetailGetSet detailGetSet = new DetailGetSet();
        detailGetSet.setName(et_name.getText().toString());
        detailGetSet.setAge(et_age.getText().toString());
        detailGetSet.setConatct(et_contact.getText().toString());
        detailGetSet.setEmail(et_email.getText().toString());
        databaseHandler.addDetail(detailGetSet);

        et_name.setText("");
        et_age.setText("");
        et_contact.setText("");
        et_email.setText("");

    }


    public void showData(View view) {
        startActivity(new Intent(getApplicationContext(),GetDetailPage.class));
    }
}

