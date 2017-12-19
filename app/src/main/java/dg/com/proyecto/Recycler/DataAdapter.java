package dg.com.proyecto.Recycler;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dg.com.proyecto.DetailActivity;
import dg.com.proyecto.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private String[] names;
    private int[] images;

    public DataAdapter(String[] names, int[] images) {
        this.names = names;
        this.images = images;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.nametxt.setText(names[position]);
        viewHolder.imgvw.setImageResource(images[position]);

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("name", names[position]);
                intent.putExtra("image", images[position]);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nametxt;
        ImageView imgvw;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemview) {
            super(itemview);
            nametxt = (TextView) itemView.findViewById(R.id.tv_name);
            imgvw = (ImageView) itemView.findViewById(R.id.iv_image);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }
    }

}
