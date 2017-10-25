package no.martinpedersen.valutakalkulator;

import android.content.SharedPreferences;
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
        mButton3 = (Button) findViewById(R.id.button3);

        if (savedInstanceState != null) {
            //Saved as instanceState
            mValutaInput.setText(savedInstanceState.getString(INPUT_TEXT_VALUE));
            mValutaView.setText(savedInstanceState.getString(OUTPUT_TEXT_VALUE));

        }

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.rateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.fromToArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    protected void exchangeMoney() {

        try {
            Double input = Double.parseDouble(mValutaInput.getText().toString());
            Double output;
            switch (spinnerValue) {
                case "NOK to EUR":
                    output = input/9.4;
                    mValutaView.setText(String.format("%.2f", output) + " EUR");
                    break;
                case "EUR to NOK":
                    output = input*9.4;
                    mValutaView.setText(String.format("%.2f", output) + " NOK");
                    break;
                case "NOK to USD":
                    output = input/8;
                    mValutaView.setText(String.format("%.2f", output) + " USD");
                    break;
                case "USD to NOK":
                    output = input*8;
                    mValutaView.setText(String.format("%.2f", output) + " NOK");
                    break;
                case "NOK to GBP":
                    output = input/10.6;
                    mValutaView.setText(String.format("%.2f", output) + " GBP");
                    break;
                case "GBP to NOK":
                    output = input*10.6;
                    mValutaView.setText(String.format("%.2f", output) + " NOK");
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.d("TAG","Error nokToEur");
            mErrorMsg.setText(R.string.errorInput);
        }
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
        exchangeMoney();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Saved as instanceState
        super.onSaveInstanceState(outState);
        outState.putString(INPUT_TEXT_VALUE, mValutaInput.getText().toString());
        outState.putString(OUTPUT_TEXT_VALUE, mValutaView.getText().toString());

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        spinnerValue = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        //Nothing
    }
}





