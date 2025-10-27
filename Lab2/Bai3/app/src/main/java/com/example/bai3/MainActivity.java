package com.example.bai3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listNV;
    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;
    Employee employee;
    Button btn;
    EditText edtMaNV, edtTenNV;
    RadioGroup rgType;
    RadioButton rdChinhthuc, rdThoivu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMaNV = (EditText) findViewById(R.id.edtMaNV);
        edtTenNV = (EditText) findViewById(R.id.edtTenNV);
        rgType = (RadioGroup) findViewById(R.id.rgType);
        btn = (Button) findViewById(R.id.btnNhap);
        listNV = (ListView) findViewById(R.id.lvHien);
        employees = new ArrayList<Employee>();
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1,employees);
        listNV.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radId = rgType.getCheckedRadioButtonId();
                String id = edtMaNV.getText().toString();
                String name = edtTenNV.getText().toString();
                if (radId == R.id.rd_chinhthuc) {
                    //tạo instance là FullTime
                    employee = new EmployeeFulltime();
                } else {
                    //Tạo instance là Partime
                    employee = new EmployeeParttime();
                }
                //FullTime hay Partime thì cũng là Employee nên có các hàm này là hiển nhiên
                employee.setId(id);
                employee.setName(name);
                //Đưa employee vào ArrayList
                employees.add(employee);
                //Cập nhập giao diện
                adapter.notifyDataSetChanged();
            }
        });

    }
}