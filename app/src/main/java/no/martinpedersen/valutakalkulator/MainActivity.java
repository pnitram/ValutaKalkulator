package no.martinpedersen.valutakalkulator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    TextView mValutaView;
    EditText mValutaInput;
    TextView mErrorMsg;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    RadioButton mRadioButton1;
    RadioButton mRadioButton2;
    String spinnerValue;
    private static final String INPUT_TEXT_VALUE = "inputTextValue";
    private static final String OUTPUT_TEXT_VALUE = "outputTextValue";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValutaView = (TextView) findViewById(R.id.vTekst);
        mValutaInput = (EditText) findViewById(R.id.eTekst);
        mErrorMsg = (TextView) findViewById(R.id.errorMsg);
        mErrorMsg.setTextColor(Color.RED);
        //mButton1 = (Button) findViewById(R.id.button1);
        //mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        //mRadioButton1 = (RadioButton) findViewById(R.id.rNE);
        //mRadioButton2 = (RadioButton) findViewById(R.id.rEN);
        //mValutaInput.setOnClickListener(this);


        if (savedInstanceState != null) {
            //Saved as instanceState
            mValutaInput.setText(savedInstanceState.getString(INPUT_TEXT_VALUE));
            mValutaView.setText(savedInstanceState.getString(OUTPUT_TEXT_VALUE));

            //Saved as settings
            SharedPreferences settings = getSharedPreferences("Radio", 0);
            mRadioButton1.setChecked(settings.getBoolean("radioA", false));
            mRadioButton2.setChecked(settings.getBoolean("radioB", false));
        }

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.rateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.fromToArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    protected void nokToEurBase() {

        try {
            Double input = Double.parseDouble(mValutaInput.getText().toString());
            Double output;
            switch (spinnerValue) {
                case "NOK to EUR":
                    output = input/8.5;
                    mValutaView.setText(String.format("%.2f", output) + " EUR");
                    break;
                case "EUR to NOK":
                    output = input/8.5;
                    mValutaView.setText(String.format("%.2f", output) + " NOK");
                    break;
                case "NOK to USD":
                    output = input/8.5;
                    mValutaView.setText(String.format("%.2f", output) + " USD");
                    break;
                case "USD to NOK":
                    output = input/8.5;
                    mValutaView.setText(String.format("%.2f", output) + " NOK");
                    break;
                case "NOK to GBP":
                    output = input/8.5;
                    mValutaView.setText(String.format("%.2f", output) + " GBP");
                    break;
                case "GBP to NOK":
                    output = input/8.5;
                    mValutaView.setText(String.format("%.2f", output) + " NOK");
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.d("TAG","Error nokToEur");
            mErrorMsg.setText(R.string.errorInput);
        }
    }

    protected void eurToNokBase() {
        try {
            Double input = Double.parseDouble(mValutaInput.getText().toString());
            Double output = input*8.5;
            mValutaView.setText(String.format("%.2f", output) + " NOK");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.d("TAG","Error eurToNok");
            mErrorMsg.setText(R.string.errorInput);
        }
    }

    protected void nokToEur() {

        Log.d("TAG","NOKtoEUR pressed");
        nokToEurBase();
    }

    protected void eurToNok(View view) {

        Log.d("TAG","EURtoNOK pressed");
        eurToNokBase();
    }



    protected void clearOnClick(View view) {

        if(view == mValutaInput) {
            Log.d("TAG","Input and buttons cleard");
            mValutaInput.setText("");
            mErrorMsg.setText("");
            mValutaView.setText(R.string.V_tekst);
        }

    }


    public void veksle(View view) {

        mErrorMsg.setText("");
        //mValutaView.setText(spinnerValue);
        nokToEurBase();

       /* if (mRadioButton1.isChecked()) {
            nokToEurBase();
        }
        else if (mRadioButton2.isChecked()) {
            eurToNokBase();
        }
        else if (!mRadioButton1.isChecked() || !mRadioButton2.isChecked()){
            mErrorMsg.setText(R.string.errorInput);
        }*/
    }


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Saved as instanceState
        super.onSaveInstanceState(outState);
        outState.putString(INPUT_TEXT_VALUE, mValutaInput.getText().toString());
        outState.putString(OUTPUT_TEXT_VALUE, mValutaView.getText().toString());

        //Saved as settings
        SharedPreferences settings = getSharedPreferences("Radio", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("radio1", mRadioButton1.isChecked());
        editor.putBoolean("radio2", mRadioButton2.isChecked());
        editor.commit();

        }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        spinnerValue = adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}





