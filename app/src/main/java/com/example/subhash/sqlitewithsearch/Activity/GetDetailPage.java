package com.example.subhash.sqlitewithsearch.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.subhash.sqlitewithsearch.Adapter.DetailPageAdapter;
import com.example.subhash.sqlitewithsearch.Database.DatabaseHandler;
import com.example.subhash.sqlitewithsearch.Model.DetailGetSet;
import com.example.subhash.sqlitewithsearch.R;

import java.util.ArrayList;
import java.util.List;

public class GetDetailPage extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static DatabaseHandler databaseHandler;
    public static DetailPageAdapter detailPageAdapter;
    EditText filter_edit_text;
    public static List<DetailGetSet> data_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_detail_page);
        recyclerView = findViewById(R.id.recyclerview);
        filter_edit_text = findViewById(R.id.search_edit_text);
        databaseHandler = new DatabaseHandler(this);
        data_list = databaseHandler.getAllDetail();
        detailPageAdapter = new DetailPageAdapter(this,data_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(detailPageAdapter);




        filter_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                        filter(s.toString().trim());

                }


        });
    }



    public void filter(String text){
        List<DetailGetSet> temp = new ArrayList();
        for(int i = 0; i< data_list.size(); i++){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(data_list.get(i).getName().contains(text.toLowerCase())){
                temp.add(data_list.get(i));
            }
        }
        //update recyclerview
        Toast.makeText(this, ""+temp.size(), Toast.LENGTH_SHORT).show();
        updateList(temp);
    }

    public void updateList(List<DetailGetSet> temp) {


        detailPageAdapter = new DetailPageAdapter(this,temp);
        recyclerView.setAdapter(detailPageAdapter);
        detailPageAdapter.notifyDataSetChanged();


    }


    public static void deleteRowItem(String conatct) {

        databaseHandler.deleteDetail(conatct);
       data_list = databaseHandler.getAllDetail();
        detailPageAdapter.notifyDataSetChanged();

    }
}
