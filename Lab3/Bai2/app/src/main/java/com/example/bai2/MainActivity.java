package com.example.bai2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_ADD_EDIT = 1001;


    private DatabaseHandler db;
    private RecyclerView rv;
    private StudentAdapter adapter;
    private List<Student> students = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHandler(this);
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));


        adapter = new StudentAdapter(students);
        rv.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddEditStudentActivity.class);
            startActivityForResult(i, REQ_ADD_EDIT);
        });


        loadStudents();
    }

    private void loadStudents() {
        students.clear();
        students.addAll(db.getAllStudents());
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_EDIT && resultCode == Activity.RESULT_OK) {
            loadStudents();
        }
    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.VH> {
        private List<Student> list;


        StudentAdapter(List<Student> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            Student s = list.get(position);
            holder.tvName.setText(s.getName());
            holder.tvMssv.setText(s.getMssv());
            holder.btnDelete.setOnClickListener(v -> confirmDelete(s.getMssv(), position));
            holder.btnEdit.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, AddEditStudentActivity.class);
                i.putExtra("mssv", s.getMssv());
                startActivityForResult(i, REQ_ADD_EDIT);
            });
            holder.itemView.setOnClickListener(v -> {
                String details = "Họ tên: " + s.getName()
                        + "\nMSSV: " + s.getMssv()
                        + "\nGiới tính: " + s.getGender()
                        + "\nNgày sinh: " + s.getDob()
                        + "\nKhóa học: " + s.getCourse()
                        + "\nNgành: " + s.getMajor()
                        + "\nGPA: " + s.getGpa();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Chi tiết sinh viên")
                        .setMessage(details)
                        .setPositiveButton("OK", null)
                        .show();
            });
        }

        @Override
        public int getItemCount() { return list.size(); }


        class VH extends RecyclerView.ViewHolder {
            TextView tvName, tvMssv;
            ImageButton btnEdit, btnDelete;


            VH(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvName);
                tvMssv = itemView.findViewById(R.id.tvMssv);
                btnDelete = itemView.findViewById(R.id.btnDelete);
                btnEdit = itemView.findViewById(R.id.btnEdit);
            }
        }

        private void confirmDelete(String mssv, int position) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Xác nhận")
                    .setMessage("Xóa sinh viên MSSV: " + mssv + "?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        boolean ok = db.deleteStudent(mssv);
                        if (ok) {
                            list.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setMessage("Xóa thất bại.")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        }
    }
}