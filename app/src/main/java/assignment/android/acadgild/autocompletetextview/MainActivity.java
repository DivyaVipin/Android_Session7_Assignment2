package assignment.android.acadgild.autocompletetextview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
private DBHelper dbHelper;
     AutoCompleteTextView autoCompleteTextView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        textView=(TextView) findViewById(R.id.textViewProduct);
        autoCompleteTextView.setThreshold(1);
        dbHelper=new DBHelper(MainActivity.this);
        dbHelper.openDB();
        dbHelper.insertProduct("HP Injet Printer");
        dbHelper.insertProduct("HP Laser Jet");
        dbHelper.insertProduct("Canon");
        dbHelper.insertProduct("Panasonic");
        dbHelper.insertProduct("Dell");
        dbHelper.insertProduct("Samsung");
        String[] productNames=dbHelper.getAllProducts();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,productNames);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setTextColor(Color.BLACK);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.closeDB();
    }
}
