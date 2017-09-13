package no.martinpedersen.valutakalkulator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView mValutaView;
    EditText mValutaInput;
    TextView mErrorMsg;
    Button mButton1;
    Button mButton2;
    RadioButton mRadioButton1;
    RadioButton mRadioButton2;


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
        mRadioButton1 = (RadioButton) findViewById(R.id.rNE);
        mRadioBut55555ton2 = (RadioButton) findViewById(R.id.rEN);
        mValutaInput.setOnClickListener(this);

    }

    protected void nokToEurBase() {

        try {
            Double input = Double.parseDouble(mValutaInput.getText().toString());
            Double output = input/8.5;
            mValutaView.setText(String.format("%.2f", output) + " EUR");
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

    protected void nokToEur(View view) {

        Log.d("TAG","NOKtoEUR pressed");
        nokToEurBase();
    }

    protected void eurToNok(View view) {

        Log.d("TAG","EURtoNOK pressed");
        eurToNokBase();
    }


    @Override
    public void onClick(View view) {

        if(view == mValutaInput) {
            Log.d("TAG","Input and buttons cleard");
            mValutaInput.setText("");
            mErrorMsg.setText("");
            mRadioButton1.setChecked(false);
            mRadioButton2.setChecked(false);
        }

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rNE:
                if (checked) {
                    Log.d("TAG","NOKtoEUR radio checked");
                    nokToEurBase();
                }
                break;
            case R.id.rEN:
                if (checked) {
                    Log.d("TAG","EURtoNOK radio checked");
                    eurToNokBase();
                }
                break;
        }
    }

}



