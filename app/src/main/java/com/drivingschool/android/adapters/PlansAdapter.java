package com.drivingschool.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivingschool.android.R;
import com.drivingschool.android.customviews.CustomTextView;
import com.drivingschool.android.models.PlansList;

import java.util.ArrayList;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.ViewHolder> {

    private ArrayList<PlansList> plansListArrayList;
    private Context context;

    public PlansAdapter(ArrayList<PlansList> plansListArrayList, Context context) {
        this.plansListArrayList = plansListArrayList;
        this.context = context;
    }

    @Override
    public PlansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_plans,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAdapter.ViewHolder holder, int position) {

        final PlansList plansList = plansListArrayList.get(position);

        holder.tvPlaneName.setText(plansList.getPlanName());
        holder.tvDesc.setText(plansList.getDescription());
        holder.tvPrice.setText(plansList.getPrice());
        holder.tvType.setText(plansList.getType());

    }

    @Override
    public int getItemCount() {
        return plansListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CustomTextView tvPlaneName,tvType,tvDesc,tvPrice,tvPurchase;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlaneName = itemView.findViewById(R.id.tvPlaneName);
            tvType = itemView.findViewById(R.id.tvType);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPurchase = itemView.findViewById(R.id.tvPurchase);


        }
    }
}
