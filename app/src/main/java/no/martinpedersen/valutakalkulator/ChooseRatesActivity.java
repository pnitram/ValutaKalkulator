package no.martinpedersen.valutakalkulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ChooseRatesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_rates);

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.rateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fromToArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        spinnerValue = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        //Nothing
    }

    public void backToMainActivity(View view) {
        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra("spinnerValue", spinnerValue);
        setResult(MainActivity.RESULT_OK,returnIntent);
        finish();

    }

}
