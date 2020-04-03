package com.example.routine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routine.DbHelper.DBConstants;
import com.example.routine.Model.DailyReminder;
import com.example.routine.Model.Reminder;
import com.example.routine.R;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private Context mContext;
    private List<Reminder> reminderList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ReminderAdapter(Context mContext, List<Reminder> reminderList) {
        this.mContext = mContext;
        this.reminderList = reminderList;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.reminder_item, viewGroup, false);
        return new ReminderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder currentItem = reminderList.get(position);
        Integer id = currentItem.getID();
        String eventName = currentItem.getEventName();
        String notificationMessage = currentItem.getNotificationMessage();
        String startedDate = currentItem.getStartedDate();
        String endedDate = currentItem.getEndedDate();
        String time = currentItem.getTime();
        String type = currentItem.getType();
        if(type.equals(DBConstants.TYPE_DAILY)){
            DailyReminder currentDailyReminder = currentItem.getDailyReminder();
            String frequency = currentDailyReminder.getFrequency();
            holder.frequencyTV.setText(frequency);
        }
        holder.eventNameTV.setText(eventName);
        holder.notificationMessageTV.setText(notificationMessage);
        holder.startingDateTV.setText(startedDate);
        holder.endingDateTV.setText(endedDate);
        holder.timeTV.setText(time);
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder{

        TextView eventNameTV;
        TextView notificationMessageTV;
        TextView startingDateTV;
        TextView endingDateTV;
        TextView timeTV;
        TextView frequencyTV;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTV = itemView.findViewById(R.id.reminder_item_event_name);
            notificationMessageTV = itemView.findViewById(R.id.reminder_item_notification_message);
            startingDateTV = itemView.findViewById(R.id.reminder_item_starting_date);
            endingDateTV = itemView.findViewById(R.id.reminder_item_end_date);
            timeTV = itemView.findViewById(R.id.reminder_item_clock);
            frequencyTV = itemView.findViewById(R.id.reminder_item_frequency_value);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
