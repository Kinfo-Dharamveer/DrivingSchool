package com.drivingschool.android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivingschool.android.R;
import com.drivingschool.android.customviews.CustomTextView;
import com.drivingschool.android.models.InstructorModel;

import java.util.ArrayList;

public class InstructorsAdapter extends RecyclerView.Adapter<InstructorsAdapter.Holder> {

    private ArrayList<InstructorModel> instructorModelArrayList;
    private Context context;
    private mClickListener mClickListener;

    public interface mClickListener{
        public void mClick(View v, int position);
    }

    public InstructorsAdapter(ArrayList<InstructorModel> instructorModelArrayList, Context context,mClickListener listener) {
        this.instructorModelArrayList = instructorModelArrayList;
        this.context = context;
        this.mClickListener =listener;
    }

    @Override
    public InstructorsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.row_intr,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorsAdapter.Holder holder, final int position) {

        final InstructorModel instructorModel = instructorModelArrayList.get(position);

        holder.tvname.setText(instructorModel.getName());


        holder.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClickListener.mClick(view,position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return instructorModelArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CustomTextView tvname,tvDetail;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.tvname);
            tvDetail = itemView.findViewById(R.id.tvDetail);


        }
    }
}
