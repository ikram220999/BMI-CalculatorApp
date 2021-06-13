package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText height;
    EditText weight;
    Button btnCalculate;
    Button btnClear;
    TextView tvOutput,tvCategory,tvRisk;
    double bmi;
    String category="", risk="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnClear = (Button) findViewById(R.id.btnClear);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        tvRisk = (TextView) findViewById(R.id.tvRisk);


        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCalculate:

                if( TextUtils.isEmpty(height.getText()) || TextUtils.isEmpty(weight.getText())){
                    height.setError("Enter data!!");
                    weight.setError("Enter data!!");
                    return;
                }
                else {
                    double Rheight = Double.parseDouble(height.getText().toString());
                    double Rweight = Double.parseDouble(weight.getText().toString());
                    bmi = Math.round(Rweight / ((Rheight/100.0) * (Rheight/100.0) ));
                    tvOutput.setText(bmi + " BMI");
                    Category();
                    break;
                }

            case R.id.btnClear:

                if( TextUtils.isEmpty(height.getText()) && TextUtils.isEmpty(weight.getText())){
                    height.setError("Already clear");
                    weight.setError("Already clear");
                    return;
                }
                else{
                    height.setText("");
                    weight.setText("");
                    tvOutput.setText("");
                    tvCategory.setText("");
                    tvRisk.setText("");
                }

        }
    }

    public void Category() {

        if (bmi <= 18.4){
            category = "Underweight";
            risk = "Malnutrition risk";
        }
        else if (bmi >= 18.5 && bmi <= 24.9){
            category = "Normal Weight";
            risk = "Low risk";
        }
        else if (bmi >= 25.0 && bmi <= 29.9){
            category = "Overweight";
            risk = "Enchanced risk";
        }
        else if (bmi >= 30.0 && bmi <= 34.9){
            category = "Moderately obese";
            risk = "Medium risk";
        }
        else if (bmi >= 35.0 && bmi <= 39.9){
            category = "Severely obese";
            risk = "High risk";
        }
        else if (bmi >= 40.0){
            category = "Very severely obese";
            risk = "Very high risk";
        }

        tvCategory.setText(category);
        tvRisk.setText(risk);

    }
}