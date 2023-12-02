package com.example.tp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.forecastModels.Forecast

class WeatherAdapter(private val forecast : Forecast?) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val pressure : TextView
        val date : TextView
        val humidity : TextView
        val minTempTextView: TextView
        var maxTempTextView: TextView
                init {
                    pressure = itemView.findViewById(R.id.textViewPressure)
                    date = itemView.findViewById(R.id.textViewDate)
                    humidity = itemView.findViewById(R.id.textViewHumidity)
                    minTempTextView = itemView.findViewById(R.id.textViewMinTemp)
                    maxTempTextView= itemView.findViewById(R.id.textViewMaxTemp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pressure.text = "${forecast!!.list[position].pressure.toString()}"
        holder.date.text = "date ${forecast!!.list[position].dt.toString()}"
        holder.humidity.text = "${forecast!!.list[position].humidity.toString()}"
        holder.maxTempTextView.text=" ${forecast!!.list[position].temp.max.toString()}"
        holder.minTempTextView.text=" ${forecast!!.list[position].temp.min.toString()}"
    }

    override fun getItemCount(): Int {
        if(forecast != null) return forecast.list.size
        return 0
    }
}