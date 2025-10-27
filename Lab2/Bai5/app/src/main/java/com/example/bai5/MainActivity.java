package com.example.bai5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerDish;
    SpinnerAdapter spinnerAdapter;
    Dish resDish;
    Button btnAdd;
    GridView gvDish;
    EditText edtName;
    ArrayList<Dish> arrayDish;
    ArrayList<Dish> DishGv;
    DishAdapter adapter;
    CheckBox ckbxPromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayDish = new ArrayList<Dish>();
        arrayDish.add(new Dish("Thumbnail 1", R.drawable.thumb1));
        arrayDish.add(new Dish("Thumbnail 2", R.drawable.thumb2));
        arrayDish.add(new Dish("Thumbnail 3", R.drawable.thumb3));
        arrayDish.add(new Dish("Thumbnail 4", R.drawable.thumb4));
        arrayDish.add(new Dish("Thumbnail 5", R.drawable.thumb5));

        spinnerDish = (Spinner) findViewById(R.id.spnThumb);

        spinnerAdapter = new DSpinnerAdapter(getApplicationContext(), R.layout.dropdown_item, arrayDish);
        spinnerDish.setAdapter(spinnerAdapter);


        spinnerDish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                resDish = arrayDish.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ckbxPromo = (CheckBox) findViewById(R.id.ckboxPromo) ;
        btnAdd  = (Button) findViewById(R.id.btnAdd) ;
        gvDish = (GridView) findViewById(R.id.gvDish);

        edtName = (EditText) findViewById(R.id.edtName);
        DishGv = new ArrayList<Dish>();
        adapter = new DishAdapter(this, R.layout.dish,DishGv);
        gvDish.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Dish monan = new Dish();
                monan.setName(name);

                monan.setThumbnail(resDish.getThumbnail());

                if (ckbxPromo.isChecked())
                {
                    monan.setPromotion(true);
                }
                else
                {
                    monan.setPromotion(false);
                }

                DishGv.add(monan);
                edtName.setText("");
                spinnerDish.setSelection(0);
                ckbxPromo.setChecked(false);
                adapter.notifyDataSetChanged();
            }
        });
    }
}