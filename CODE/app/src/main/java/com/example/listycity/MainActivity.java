package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    private EditText inputCity;
    private Button addButton;
    private Button deleteButton;

    private Button confirmButton;

    private String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //imp
        setContentView(R.layout.activity_main); //imp
        EdgeToEdge.enable(this); //optional
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; //optional
        });


       cityList = findViewById(R.id.city_list);
       inputCity = findViewById(R.id.input_city);
       addButton = findViewById(R.id.add_button);
       deleteButton = findViewById(R.id.delete_button);
       confirmButton = findViewById(R.id.confirm_button);

       dataList = new ArrayList<>();
       String[] cities = {"Karachi", "Lahore", "Islamabad"};
       dataList.addAll(Arrays.asList(cities));
       cityAdapter = new ArrayAdapter<>( this,R.layout.content, dataList);
       cityList.setAdapter(cityAdapter);

       inputCity.setVisibility(View.GONE);
       confirmButton.setVisibility(View.GONE);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = inputCity.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    inputCity.setText("");

                    inputCity.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputCity.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                inputCity.setText("");
            }
        });


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = dataList.get(position);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCity != null) {
                    dataList.remove(selectedCity);
                    cityAdapter.notifyDataSetChanged();
                    selectedCity = null;
                }
            }
        });

    }
}