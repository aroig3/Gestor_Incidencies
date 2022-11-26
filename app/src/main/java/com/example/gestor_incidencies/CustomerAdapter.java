package com.example.gestor_incidencies;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    Context context;
    ArrayList n_elem, dt, ub;
    CustomerAdapter( Context context, ArrayList n_elem, ArrayList dt, ArrayList ub){
        this.context = context;
        this.n_elem = n_elem;
        this.dt = dt;
        this.ub = ub;
    }

    @NonNull
    //referenciem al layout que tenim per mostrar cada fila
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.incidencia_llista_lyt, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    //Obtenim la posició de cada element (int)
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.n_elemtxt.setText(String.valueOf(n_elem.get(position)));
        holder.dttxt.setText(String.valueOf(dt.get(position)));
        holder.ubtxt.setText(String.valueOf(ub.get(position)));
    }


    public int getItemCount() {
        return n_elem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView n_elemtxt, dttxt, ubtxt;
        //Referenciant cada element que volem posar a la llista
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            n_elemtxt = itemView.findViewById(R.id.nom_element);
            dttxt = itemView.findViewById(R.id.data);
            ubtxt = itemView.findViewById(R.id.ubi);
        }
    }
}
