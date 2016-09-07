package com.thanhn1436664.loancalcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;

    private double loanAmount;
    private int termLoan;
    private double yearlyInterestRate;

    private double monthlyInterestRate;
    private int numberOfPayments;

    private double monthlyPayment;
    private double totalPayment;
    private double totalInterest;

    private boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCalculate(View view){

        tv = (TextView) findViewById(R.id.error_msg);
        tv.setVisibility(TextView.INVISIBLE);

        et = (EditText) findViewById(R.id.etxtLoanAmount);
        loanAmount = Double.parseDouble(et.getText().toString());
        if(loanAmount <= 0)
            valid = false;

        et = (EditText)findViewById(R.id.etxtTermLoans);
        termLoan = Integer.parseInt(et.getText().toString());
        if(termLoan < 1 || termLoan > 25)
            valid = false;

        et = (EditText)findViewById(R.id.etxtYearlyInterest);
        yearlyInterestRate = Double.parseDouble(et.getText().toString());
        if(yearlyInterestRate < 0 || yearlyInterestRate > 100)
            valid = false;

        if(valid) {
            if (termLoan != 0 && yearlyInterestRate != 0) {

                //calculate the monthly payment
                monthlyInterestRate = yearlyInterestRate / 1200;
                numberOfPayments = termLoan * 12;

                monthlyPayment =
                        (loanAmount * monthlyInterestRate) /
                                (1 - (1 / Math.pow((1 + monthlyInterestRate), numberOfPayments)));

                monthlyPayment = Math.round(monthlyPayment * 100) / 100.0;
            } else
                monthlyPayment = 0;

            totalPayment = monthlyPayment * termLoan * 12;

            totalInterest = totalPayment - loanAmount;

            tv = (TextView) findViewById(R.id.txtMonthlyPaymentResult);
            tv.setText(Double.toString(monthlyPayment));
            tv.setVisibility(TextView.VISIBLE);

            tv = (TextView) findViewById(R.id.txtTotalPaymentResult);
            tv.setText(Double.toString(totalPayment));
            tv.setVisibility(TextView.VISIBLE);

            tv = (TextView) findViewById(R.id.txtTotalInterestResult);
            tv.setText(Double.toString(totalInterest));
            tv.setVisibility(TextView.VISIBLE);
        }
        else {
            tv = (TextView) findViewById(R.id.error_msg);
            tv.setVisibility(TextView.VISIBLE);
        }
    }

    public void onClear(View view) {

        et = (EditText)findViewById(R.id.etxtLoanAmount);
        et.setText("0.0");
        et = (EditText)findViewById(R.id.etxtTermLoans);
        et.setText("0");
        et = (EditText)findViewById(R.id.etxtYearlyInterest);
        et.setText("0.0");

        tv = (TextView) findViewById(R.id.txtMonthlyPaymentResult);
        tv.setVisibility(TextView.INVISIBLE);
        tv = (TextView) findViewById(R.id.txtTotalPaymentResult);
        tv.setVisibility(TextView.INVISIBLE);
        tv = (TextView) findViewById(R.id.txtTotalInterestResult);
        tv.setVisibility(TextView.INVISIBLE);

        tv.setVisibility(TextView.INVISIBLE);
    }

}
