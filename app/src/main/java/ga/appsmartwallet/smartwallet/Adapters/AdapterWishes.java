package ga.appsmartwallet.smartwallet.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ga.appsmartwallet.smartwallet.Modelos.ModelItemWish;
import ga.appsmartwallet.smartwallet.R;

public class AdapterWishes extends RecyclerView.Adapter<AdapterWishes.MyViewHolder> {

    ArrayList<ModelItemWish> modelItemWishArrayList = new ArrayList<>();
    Context context;

    public AdapterWishes(Context context, ArrayList<ModelItemWish> modelItemWishArrayList) {

        this.context = context;
        this.modelItemWishArrayList = modelItemWishArrayList;
    }

    @Override
    public AdapterWishes.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishes, parent, false);
        AdapterWishes.MyViewHolder viewHolder = new AdapterWishes.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterWishes.MyViewHolder holder, int position) {
        final ModelItemWish modelItemWish = modelItemWishArrayList.get(position);
        holder.tvQtd.setText(modelItemWish.getQtd());
        holder.tvNome.setText(modelItemWish.getNome());
    }

    @Override
    public int getItemCount() {
        return modelItemWishArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNome, tvQtd;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvItemWishes);
            tvQtd = itemView.findViewById(R.id.tvWishesQtd);

        }
    }
}
