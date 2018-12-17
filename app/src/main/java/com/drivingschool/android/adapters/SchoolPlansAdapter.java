package com.drivingschool.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivingschool.android.R;
import com.drivingschool.android.customviews.CustomTextView;
import com.drivingschool.android.response.schooldetail.PlanPayload;

import java.util.ArrayList;
import java.util.List;

public class SchoolPlansAdapter extends RecyclerView.Adapter<SchoolPlansAdapter.Holder> {

    private ArrayList<PlanPayload> planPayloadList;
    Context context;

    public SchoolPlansAdapter(ArrayList<PlanPayload> planPayloadList, Context context) {
        this.planPayloadList = planPayloadList;
        this.context = context;
    }

    @Override
    public SchoolPlansAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_plans_row,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolPlansAdapter.Holder holder, int i) {

        final PlanPayload planPayload = planPayloadList.get(i);

        holder.tvPlainName.setText(planPayload.getName());
        holder.tvPrice.setText(planPayload.getPrice());
        holder.tvPlanDes.setText(planPayload.getDesc());
    }

    @Override
    public int getItemCount() {
        return planPayloadList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private CustomTextView tvPlainName,tvPrice,tvPlanDes;

        public Holder( View itemView) {
            super(itemView);


            tvPlainName = itemView.findViewById(R.id.tvPln);
            tvPrice = itemView.findViewById(R.id.tvPr);
            tvPlanDes = itemView.findViewById(R.id.tvPlnDes);
        }
    }
}
