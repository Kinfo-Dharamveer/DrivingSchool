package com.drivingschool.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.drivingschool.android.R;
import com.drivingschool.android.customviews.CustomTextView;
import com.drivingschool.android.response.schooldata.SchoolPayload;


import java.util.ArrayList;
import java.util.List;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.Holder> {

    private List<SchoolPayload> schoolListArrayList= new ArrayList<>();
    private Context context;
    public  buttonClickListenter buttonClickListenter;

    public interface buttonClickListenter{
        void reviewClick(int pos);
        void readMoreClick(int pos);
    }

    public SchoolsAdapter(List<SchoolPayload> schoolListArrayList, Context context, SchoolsAdapter.buttonClickListenter buttonClickListenter) {
        this.schoolListArrayList = schoolListArrayList;
        this.context = context;
        this.buttonClickListenter = buttonClickListenter;
    }

    @Override
    public SchoolsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_schools,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SchoolsAdapter.Holder holder, final int position) {

        final SchoolPayload schoolPayload = schoolListArrayList.get(position);

        holder.schoolImage.setImageResource(R.drawable.car);
        holder.tvTitle.setText(schoolPayload.getName());
        holder.tvLocation.setText(schoolPayload.getAddress());

        holder.tvReviewCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListenter.reviewClick(position);
            }
        });

        holder.tvReadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListenter.readMoreClick(schoolPayload.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return schoolListArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView schoolImage;
        private CustomTextView tvTitle,tvLocation,typeVehicle,tvInstr,tvVehicleCount,tvReviewCount,tvReadmore;

        public Holder(View itemView) {
            super(itemView);

            schoolImage = itemView.findViewById(R.id.schoolImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            typeVehicle = itemView.findViewById(R.id.typeVehicle);
            tvInstr = itemView.findViewById(R.id.tvInstr);
            tvVehicleCount = itemView.findViewById(R.id.tvVehicleCount);
            tvReviewCount = itemView.findViewById(R.id.tvReviewCount);
            tvReadmore = itemView.findViewById(R.id.tvReadmore);

        }
    }
}
