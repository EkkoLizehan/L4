package com.example.lab_04;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText priceBox;
    Button add, delete, find;

    ListView productlist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView)  findViewById(R.id.id);
        productBox = (EditText) findViewById(R.id.optName);
        priceBox = (EditText) findViewById(R.id.optPrice);
        productlist = (ListView) findViewById(R.id.listview);
        add = (Button) findViewById(R.id.add);
        find = (Button) findViewById(R.id.find);
        delete = (Button) findViewById(R.id.delete);

        MyDBHandler db = new MyDBHandler(this);
        listItem = new ArrayList<>();

        viewData();

        productlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = productlist.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, "" + text, Toast.LENGTH_SHORT).show();

            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newProduct(view);

            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lookupProduct(view);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeProduct(view);

            }
        });


    }

    public void newProduct(View view) {

        MyDBHandler db1 = new MyDBHandler(this);
        double price = Double.parseDouble(priceBox.getText().toString());
        Product product = new Product(productBox.getText().toString(), price);

        db1.addProduct(product);

        productBox.setText("");
        priceBox.setText("");

        listItem.clear();
        viewData();

    }

    public void lookupProduct(View view) {

        MyDBHandler db2 = new MyDBHandler(this);

        Product product = db2.findProduct(productBox.getText().toString());

        if (product != null) {

            idView.setText(String.valueOf(product.getID()));
            priceBox.setText(String.valueOf(product.getPrice()));

        }   else {

            idView.setText("No Match Found");

        }

    }

    public void removeProduct(View view) {

        MyDBHandler db3 = new MyDBHandler(this);

        boolean res = db3.deleteProduct(productBox.getText().toString());

        listItem.clear();
        viewData();

        if (res) {

            idView.setText("Record Deleted");
            productBox.setText("");
            priceBox.setText("");

        }   else {

            idView.setText("No Match Found");

        }

    }

    private void viewData() {

        MyDBHandler db4 = new MyDBHandler(this);

        Cursor cursor = db4.viewData();

        if (cursor.getCount() == 0) {

            Toast.makeText(this, "No Data To Show", Toast.LENGTH_SHORT).show();

        }   else {

            while(cursor.moveToNext()) {

                listItem.add(cursor.getString(1));

            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);

            productlist.setAdapter(adapter);

        }

    }

}