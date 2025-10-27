package com.example.bai6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private final Context context;
    private final int layoutId;
    private final List<Employee> employees;

    public EmployeeAdapter(Context context, int layoutId, List<Employee> employees) {
        this.context = context;
        this.layoutId = layoutId;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee e = employees.get(position);
        if (e == null) return;

        holder.tvFullName.setText(e.getFullName() != null ? e.getFullName() : "");

        if (e.isManager()) {
            holder.ivManager.setImageResource(R.drawable.ic_manager);
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.ivManager.setContentDescription("Manager");
            holder.tvPosition.setVisibility(View.GONE);
            holder.tvPosition.setText("");
        } else {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText("Staff");

        }

        try {
            int colorRes = (position % 2 == 0) ? R.color.white : R.color.light_blue;
            int bgColor = ContextCompat.getColor(context, colorRes);
            holder.llParent.setBackgroundColor(bgColor);
        } catch (Exception ex) {
            holder.llParent.setBackgroundColor(0xFFFFFFFF);
        }

    }

    @Override
    public int getItemCount() {
        return employees == null ? 0 : employees.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvFullName;
        final TextView tvPosition;
        final ImageView ivManager;
        final LinearLayout llParent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.item_employee_tv_fullname);
            tvPosition = itemView.findViewById(R.id.item_employee_tv_position);
            ivManager = itemView.findViewById(R.id.item_employee_iv_manager);
            llParent = itemView.findViewById(R.id.item_employee_ll_parent);
        }
    }
}
