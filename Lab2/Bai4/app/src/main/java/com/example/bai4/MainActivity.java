package com.example.bai4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtName;
    CheckBox chbxManager;
    Button btnAdd;
    ListView lvEmployee;
    ArrayList<Employee> employees;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtName);
        chbxManager = (CheckBox) findViewById(R.id.ckbxManager);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        employees = new ArrayList<Employee>();

        adapter = new EmployeeAdapter(this, R.layout.item_employee,employees);
        lvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Employee employee = new Employee();
                if (chbxManager.isChecked())
                {
                    employee.setManager(true);
                }
                else
                {
                    employee.setManager(false);
                }
                employee.setFullName(name);
                employees.add(employee);
                adapter.notifyDataSetChanged();
            }
        });
    }
}