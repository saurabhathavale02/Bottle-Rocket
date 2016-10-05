package com.example.saurabh.storedatadisplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabh.storedatadisplay.R;
import com.example.saurabh.storedatadisplay.model.pojo.Store;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 10/02/16.
 */

public class StoreListAdapter  extends RecyclerView.Adapter<StoreListAdapter.ViewHolder>
{
    private List<Store> stores;

    private final StoreItemListener mListener;


    public StoreListAdapter(Context context, StoreItemListener listener)
    {
        this.stores = new ArrayList<>();
        this.mListener = listener;
    }

    public void addStores(List<Store> newstore)
    {
        System.out.println("add ="+newstore.get(0).getAddress());
        stores.addAll(newstore);
        notifyDataSetChanged();
    }

    @Override
    public StoreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoreListAdapter.ViewHolder holder, int position) {
        holder.storename.setText(stores.get(position).getName());
        holder.storeaddress.setText(stores.get(position).getAddress());

        holder.logo.setImageBitmap(null);

        Picasso.with(holder.logo.getContext())
                .load(stores.get(position).getStoreLogoURL())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView storename;
        TextView storeaddress;
        ImageView logo;

        public ViewHolder(View view)
        {
            super(view);
            view.setOnClickListener(this);

            storename=(TextView) view.findViewById(R.id.storename);
            storeaddress=(TextView) view.findViewById(R.id.storeaddress);
            logo=(ImageView) view.findViewById(R.id.imageView2);

        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();
            Store SelectedStore = stores.get(position);
            mListener.onStoreClick(SelectedStore);

        }
    }
    public interface StoreItemListener
    {
        void onStoreClick(Store item);
    }
}