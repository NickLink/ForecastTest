package com.example.nicklink.forecasttest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nicklink.forecasttest.R;
import com.example.nicklink.forecasttest.domain.WeatherItem;
import com.example.nicklink.forecasttest.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nick on 19.04.2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private String TAG = WeatherAdapter.class.getCanonicalName();
    private List<WeatherItem> itemList;
    private int rowLayout;
    private Context context;

    private RecyclerClick listener;

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemLayout;
        ImageView imageView;
        TextView date;
        TextView title;
        TextView temperature_day;
        TextView temperature_night;
//        TextView humidity;
        TextView wind;


        public WeatherViewHolder(View v) {
            super(v);
            itemLayout = (LinearLayout) v.findViewById(R.id.item_layout);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            date = (TextView) v.findViewById(R.id.date);
            title = (TextView) v.findViewById(R.id.title);
            temperature_day = (TextView) v.findViewById(R.id.temperature_day);
            temperature_night = (TextView) v.findViewById(R.id.temperature_night);
//            humidity = (TextView) v.findViewById(R.id.humidity);
            wind = (TextView) v.findViewById(R.id.wind_speed);
        }
    }

    public void setOnClick(RecyclerClick listener){
        this.listener = listener;
    }

    public WeatherAdapter(List<WeatherItem> list, int rowLayout, Context context) {
        this.itemList = list;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        switch (viewType) {

            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.today_list_item;
                break;
            }

            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.list_item;
                break;
            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        view.setFocusable(true);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, final int position) {



        holder.date.setText(StringUtils.getDateFromMillis((long)itemList.get(position).getDt()));
        holder.title.setText(itemList.get(position).getWeather().get(0).getDescription());
        holder.temperature_day.setText("День " + String.valueOf(itemList.get(position).getTemp().getDay()) + "");
        holder.temperature_night.setText("Ніч " + String.valueOf(itemList.get(position).getTemp().getNight()) + "");
//        holder.humidity.setText(String.valueOf(itemList.get(position).getHumidity()));
        holder.wind.setText(String.valueOf(itemList.get(position).getSpeed()));

        Picasso.with(context).load("http://openweathermap.org/img/w/" + itemList.get(position).getWeather().get(0).getIcon()  + ".png")
                .resize(48, 48)
                .into(holder.imageView);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                listener.onClick(position, 100);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                listener.onClick(position, 101);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_TODAY;
        } else {
            return VIEW_TYPE_FUTURE_DAY;
        }
    }
}
