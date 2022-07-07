package edu.unicauca.tindu;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventoAdaptador extends RecyclerView.Adapter<EventoAdaptador.RecyclerHolder> {
    private List<Eventos> items;
    private List<Eventos> originalItems;
    private RecyclerItemClick itemClick;

    public EventoAdaptador(List<Eventos> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_eventos, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final Eventos item = items.get(position);  //
        holder.imgItem.setImageResource(item.getImgResource());// contiene la imagen
        holder.tvTitulo.setText(item.getTitulo());             //Contiene el titulo
        holder.tvDescripcion.setText(item.getDescripcion()); //Contiene la descripcion


       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<Eventos> collect = originalItems.stream()
                        .filter(i -> i.getTitulo().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (Eventos i : originalItems) {
                    if (i.getTitulo().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvTitulo;
        private TextView tvDescripcion;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
// interfas de comunicacion
    public interface RecyclerItemClick {
        void itemClick(Eventos item);
    }
}
