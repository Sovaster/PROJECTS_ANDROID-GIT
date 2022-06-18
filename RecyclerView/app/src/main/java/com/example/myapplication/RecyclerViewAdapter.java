package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.Model;
import com.example.recyclerview.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private final LayoutInflater inflater;
    private final List<Model> days;

    public RecyclerViewAdapter(Context context, ArrayList<Model> days)
    {
        this.days = days;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position)
    {
        Model model = days.get(position);
        holder.day.setText(model.getDay());
        holder.para1.setText(model.getPara1());
        holder.para2.setText(model.getPara2());
        holder.para3.setText(model.getPara3());
        holder.para4.setText(model.getPara4());
        holder.para5.setText(model.getPara5());
        holder.prepod1.setText(model.getPrepod1());
        holder.prepod2.setText(model.getPrepod2());
        holder.prepod3.setText(model.getPrepod3());
        holder.prepod4.setText(model.getPrepod4());
        holder.prepod5.setText(model.getPrepod5());
    }

    @Override
    public int getItemCount() {return  days.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView day;
        final TextView para1;
        final TextView para2;
        final TextView para3;
        final TextView para4;
        final TextView para5;
        final TextView prepod1;
        final TextView prepod2;
        final TextView prepod3;
        final TextView prepod4;
        final TextView prepod5;

        ViewHolder(View view)
        {
            super (view);
            day = view.findViewById(R.id.textView1Row1);
            para1 = view.findViewById(R.id.textView2ow3);
            para2 = view.findViewById(R.id.textView2ow4);
            para3 = view.findViewById(R.id.textView2ow5);
            para4 = view.findViewById(R.id.textView2ow6);
            para5 = view.findViewById(R.id.textView2ow7);
            prepod1 = view.findViewById(R.id.textView3Row3);
            prepod2 = view.findViewById(R.id.textView3Row4);
            prepod3 = view.findViewById(R.id.textView3Row5);
            prepod4 = view.findViewById(R.id.textView3Row6);
            prepod5 = view.findViewById(R.id.textView3Row7);
        }
    }
}

