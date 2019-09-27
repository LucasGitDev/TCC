package ga.appsmartwallet.smartwallet.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ga.appsmartwallet.smartwallet.Modelos.ModeloItem;
import ga.appsmartwallet.smartwallet.R;

/**
 * Created by Teles
 */


public class AdapterItem extends RecyclerView.Adapter<AdapterItem.MyViewHolder> {

    Context context;
    ArrayList<ModeloItem> modelFeedArrayList = new ArrayList<>();

    public AdapterItem(Context context, ArrayList<ModeloItem> modelFeedArrayList) {

        this.context = context;
        this.modelFeedArrayList = modelFeedArrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ModeloItem modeloItem = modelFeedArrayList.get(position);

        holder.tvNomeProd.setText(modeloItem.getNomeProd());
        holder.tvValorProd.setText("R$" + String.valueOf(modeloItem.getValorProd()));
        holder.tvInfoSimples.setText(modeloItem.getTextoSimples());
        holder.imgViewProd.setImageResource(modeloItem.getFoto());
    }

    @Override
    public int getItemCount() {
        return modelFeedArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomeProd, tvInfoSimples, tvValorProd;
        ImageView imgViewProd;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgViewProd = (ImageView) itemView.findViewById(R.id.imgProd);

            tvNomeProd = (TextView) itemView.findViewById(R.id.tvNomeProduto);
            tvInfoSimples = itemView.findViewById(R.id.tvInfoSimples);
            tvValorProd = itemView.findViewById(R.id.tvValorProd);

        }
    }


}
