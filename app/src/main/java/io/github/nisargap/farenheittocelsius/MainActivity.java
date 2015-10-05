package io.github.nisargap.farenheittocelsius;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> listData;

    private ArrayAdapter<String> answerAdapter;

    private void updateList() {

        ListView myList = (ListView)findViewById(R.id.listView);

        myList.setAdapter(answerAdapter);

    }

    private void getErrorBox(){
        AlertDialog.Builder alertError = new AlertDialog.Builder(MainActivity.this);

        alertError.setTitle("Incorrect input!");

        alertError.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertError.setCancelable(true);

        alertError.show();
    }
    public void deleteVal(View view) {

        View current = (View) view.getParent();

        TextView thisAnswer = (TextView) current.findViewById(R.id.results);

        String answer = thisAnswer.getText().toString();

        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).equals(answer)) {

                listData.remove(i);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listData = new ArrayList<>();
        answerAdapter = new ArrayAdapter<>(this, R.layout.formatlist, R.id.results, listData);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void fahrenheitToCelsius(View view){

        try {
            EditText field = (EditText) findViewById(R.id.numberInput);

            double fahrenheit = Double.parseDouble(field.getText().toString());

            double celsius = ((fahrenheit - 32) / 1.8);


            String answer = field.getText().toString() + "°F" + " = " + String.format("%1$,.1f", celsius) + "°C";

            listData.add(0, answer);

            updateList();
        }catch(Exception e){

            getErrorBox();

        }

        //answer.setText(Double.toString(celsius));


    }
    public void celsiusToFahrenheit(View view){
        try {
            EditText field = (EditText) findViewById(R.id.numberInput);

            double celsius = Double.parseDouble(field.getText().toString());

            double fahrenheit = celsius * 1.8 + 32;

            String answer = field.getText().toString() + "°C" + " = " + String.format("%1$,.1f", fahrenheit) + "°F";

            listData.add(0, answer);


            updateList();
        }
        catch(Exception e) {

            getErrorBox();


        }


    }

}
