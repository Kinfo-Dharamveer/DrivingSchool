package com.drivingschool.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivingschool.android.R;
import com.drivingschool.android.customviews.CustomTextView;
import com.drivingschool.android.models.PackageList;

import java.util.ArrayList;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {

    private ArrayList<PackageList> packageListArrayList;
    private Context context;

    public PackageAdapter(ArrayList<PackageList> packageListArrayList, Context context) {
        this.packageListArrayList = packageListArrayList;
        this.context = context;
    }

    @Override
    public PackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.row_packages,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageAdapter.ViewHolder holder, int position) {

        final PackageList packageList = packageListArrayList.get(position);

        holder.tvPackage.setText(packageList.getPackageName());
        holder.tvDuration.setText(packageList.getDuation());
    }

    @Override
    public int getItemCount() {
        return packageListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CustomTextView tvPackage,tvDuration;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPackage = itemView.findViewById(R.id.tvPackage);
            tvDuration = itemView.findViewById(R.id.tvDuration);

        }
    }
}
