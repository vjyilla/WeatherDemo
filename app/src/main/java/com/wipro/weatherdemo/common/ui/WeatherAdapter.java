package com.wipro.weatherdemo.common.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wipro.weatherdemo.R;
import com.wipro.weatherdemo.common.dto.WeatherObject;

import java.util.ArrayList;

/**
 * Created by illa on 19-10-2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<WeatherObject> weatherObjects;
    private Context context;


    public WeatherAdapter(Context context, ArrayList<WeatherObject> weatherObjects) {
        this.weatherObjects = weatherObjects;
        this.context = context;


    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_weather_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.day_text_view.setText(weatherObjects.get(i).getDayOfWeek());
        viewHolder.desc_text_view.setText(weatherObjects.get(i).getDescription());
        viewHolder.result_text_view.setText(weatherObjects.get(i).getWeatherResult());
        viewHolder.result_small_text_view.setText(weatherObjects.get(i).getWeatherResultSmall());

    }

    @Override
    public int getItemCount() {
        return weatherObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day_text_view;
        private TextView desc_text_view;
        private TextView result_text_view;
        private TextView result_small_text_view;


        public ViewHolder(View view) {
            super(view);

            day_text_view = (TextView) view.findViewById(R.id.day);
            desc_text_view = (TextView) view.findViewById(R.id.description);
            result_small_text_view = (TextView) view.findViewById(R.id.result_small_value);
            result_text_view = (TextView) view.findViewById(R.id.result_value);
        }
    }

}
