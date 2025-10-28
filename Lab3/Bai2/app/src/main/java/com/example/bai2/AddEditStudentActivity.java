package com.example.bai2;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class AddEditStudentActivity extends AppCompatActivity {
    private EditText etName, etMssv, etDob, etCourse, etMajor, etGpa;
    private RadioGroup rgGender;
    private Button btnSave, btnCancel;
    private DatabaseHandler db;
    private boolean isEdit = false;
    private String originalMssv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_student);


        db = new DatabaseHandler(this);


        etName = findViewById(R.id.etName);
        etMssv = findViewById(R.id.etMssv);
        etDob = findViewById(R.id.etDob);
        etCourse = findViewById(R.id.etCourse);
        etMajor = findViewById(R.id.etMajor);
        etGpa = findViewById(R.id.etGpa);
        rgGender = findViewById(R.id.rgGender);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);


        String mssv = getIntent().getStringExtra("mssv");
        if (mssv != null) {

            Student s = db.getStudent(mssv);
            if (s != null) {
                isEdit = true;
                originalMssv = s.getMssv();
                etMssv.setText(s.getMssv());
                etMssv.setEnabled(false);
                etName.setText(s.getName());
                etDob.setText(s.getDob());
                etCourse.setText(s.getCourse());
                etMajor.setText(s.getMajor());
                etGpa.setText(String.valueOf(s.getGpa()));
                if ("Male".equalsIgnoreCase(s.getGender())) {
                    ((RadioButton)findViewById(R.id.rbMale)).setChecked(true);
                } else {
                    ((RadioButton)findViewById(R.id.rbFemale)).setChecked(true);
                }
            }
        }

        btnCancel.setOnClickListener(v -> finish());


        btnSave.setOnClickListener(v -> {
            if (validateInputs()) {
                String name = etName.getText().toString().trim();
                String m = etMssv.getText().toString().trim();
                String dob = etDob.getText().toString().trim();
                String course = etCourse.getText().toString().trim();
                String major = etMajor.getText().toString().trim();
                double gpa = Double.parseDouble(etGpa.getText().toString().trim());
                int gid = rgGender.getCheckedRadioButtonId();
                String gender = gid == R.id.rbMale ? "Male" : "Female";


                Student s = new Student(m, name, gender, dob, course, major, gpa);
                boolean ok;
                if (isEdit) {
                    ok = db.updateStudent(s);
                } else {
                    // check if MSSV exists
                    if (db.getStudent(m) != null) {
                        Toast.makeText(this, "MSSV đã tồn tại.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    ok = db.addStudent(s);
                }
                if (ok) {
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(this, "Lưu thất bại.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateInputs() {
        String name = etName.getText().toString().trim();
        String m = etMssv.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String gpaS = etGpa.getText().toString().trim();


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(m) || TextUtils.isEmpty(dob) || TextUtils.isEmpty(gpaS)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ các trường bắt buộc.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!dob.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            Toast.makeText(this, "Ngày sinh phải có định dạng dd/MM/yyyy.", Toast.LENGTH_LONG).show();
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false);
        try {
            sdf.parse(dob);
        } catch (ParseException e) {
            Toast.makeText(this, "Ngày sinh không hợp lệ.", Toast.LENGTH_LONG).show();
            return false;
        }

        double gpa;
        try {
            gpa = Double.parseDouble(gpaS);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "GPA phải là số.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (gpa < 0.0 || gpa > 4.0) {
            Toast.makeText(this, "GPA phải trong khoảng 0.0 - 4.0.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
