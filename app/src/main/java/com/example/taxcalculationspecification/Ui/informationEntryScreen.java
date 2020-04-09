    package com.example.taxcalculationspecification.Ui;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.DatePickerDialog;
    import android.content.Intent;
    import android.graphics.Color;
    import android.graphics.Typeface;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.Spinner;
    import android.widget.TextView;

    import com.example.taxcalculationspecification.CRACustomer;
    import com.example.taxcalculationspecification.R;
    import com.example.taxcalculationspecification.detailsDisplay;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Locale;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;


    public class informationEntryScreen extends AppCompatActivity {

        private String[] genderArray;
        private TextView currentDate;
        private TextView sinNO;
        private TextView firstname;
        private TextView lastName;
        private TextView DateTxt;
        private TextView Age;
        private Spinner gender;
        private TextView grossIncome;
        private TextView rrsp;
        private Button submit;
        private Button clear;





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            genderArray = getResources().getStringArray(R.array.gender);
            currentDate = findViewById(R.id.CurrentDate);
            sinNO = findViewById(R.id.Sin);
            firstname = findViewById(R.id.first_name);
            lastName = findViewById(R.id.last_name);
            DateTxt = findViewById(R.id.BirthDate);
            Age = findViewById(R.id.Age);
            gender = findViewById(R.id.spGender);
            grossIncome = findViewById(R.id.Grossincome);
            rrsp = findViewById(R.id.RRsp);
            submit = findViewById(R.id.btnSubmit);
            clear = findViewById(R.id.btnClear);





            final Calendar myCalendar = Calendar.getInstance();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            currentDate.setText(sdf.format(myCalendar.getTime()));
            currentDate.setTextColor(Color.BLACK);
            currentDate.setTypeface(null,Typeface.BOLD);



            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String myFormat = "MM/dd/yy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    DateTxt.setText(sdf.format(myCalendar.getTime()));
                    DateTxt.setTextColor(Color.BLACK);

                    if(calculateAge(myCalendar.getTimeInMillis()) > 18 )
                    {

                        Age.setText( Integer.toString(calculateAge(myCalendar.getTimeInMillis())));
                        Age.setTextColor(Color.BLACK);
                        Age.setTypeface(null,Typeface.NORMAL);
                        submit.setEnabled(true);


                    }else {

                        Age.setText(" Not eligible to file tax for current year!");
                        Age.setTextColor(Color.RED);
                        Age.setTypeface(null, Typeface.BOLD);
                        submit.setEnabled(false);

                    }


                }

            };

            DateTxt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(informationEntryScreen.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }

            });






            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    if((isValidSin(sinNO.getText().toString()))== false){
                        sinNO.setError("not valid");

                    }else if (firstname.getText().toString().matches("")){
                        firstname.setError("enter First name!");

                    }else if (lastName.getText().toString().matches("")){
                        lastName.setError("enter Last name!");

                    }else if (grossIncome.getText().toString().matches("")) {

                        grossIncome.setError("enter gross income!");

                    }else if ( Double.parseDouble(grossIncome.getText().toString()) <= 0){
                        grossIncome.setError("gross income cannot be Zero");

                    }  else if (rrsp.getText().toString().matches("")) {
                        rrsp.setError("enter Last name!");

                    } else {
                        sinNO.setText(sinNO.getText().toString());
                        String value1 = grossIncome.getText().toString();
                        Double grossValue = Double.parseDouble(value1);
                        String value2 = rrsp.getText().toString();
                        Double rrspValue = Double.parseDouble(value2);

                        Intent mIntent = new Intent(informationEntryScreen.this, detailsDisplay.class);
                        CRACustomer C1 = new CRACustomer(currentDate.getText().toString(), sinNO.getText().toString(), firstname.getText().toString(), lastName.getText().toString(), DateTxt.getText().toString(), Age.getText().toString(), gender.getSelectedItem().toString(), grossValue, rrspValue);
                        mIntent.putExtra("object", C1);
                        startActivity(mIntent);


                    }


                }
            });


            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    firstname.setText("");
                    lastName.setText("");
                    sinNO.setText("");
                    DateTxt.setText("");
                    grossIncome.setText("");
                    rrsp.setText("");
                    Age.setText("Age");






                }
            });


        }

        int calculateAge(long date){
            Calendar dob = Calendar.getInstance();
            dob.setTimeInMillis(date);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
                age--;
            }
            return age;
        }
        private boolean isValidSin(String sin) {
            String sinValidation = "\\d{9}";
            Pattern pattern = Pattern.compile(sinValidation);
            Matcher matcher = pattern.matcher(sin);
            return matcher.matches();
        }





    }
