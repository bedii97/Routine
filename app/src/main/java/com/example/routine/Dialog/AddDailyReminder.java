package com.example.routine.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.routine.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddDailyReminder extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    //CONSTANTS
    private static String TIME_PREFIX = "Saat: ";
    private static String DATE_PREFIX = "Tarih: ";
    private static String CURRENT_DATE_TAG = "currentDateDialog";
    private static String END_DATE_TAG = "endDateDialog";

    private Button saveButton, cancelButton;
    private EditText eventNameEditText, notificationMessageEditText, currentDateEditText, endDateEditText, currentTimeEditText, frequencyEditText;
    private AddDailyReminderListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_daily_reminder, null);
        builder.setView(view)
                .setTitle(R.string.add_daily_reminder_dialog_tag);
        initViews(view);
        getDefaultData();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = eventNameEditText.getText().toString();
                String notifMessage = notificationMessageEditText.getText().toString();
                String currentDate = currentDateEditText.getText().toString();
                String endDate = endDateEditText.getText().toString();
                String currentTime = currentTimeEditText.getText().toString();
                String frequency = frequencyEditText.getText().toString();

                listener.getDailyInfo(eventName, notifMessage, currentDate, endDate, currentTime, frequency);
                getDialog().dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        
        currentTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        currentDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(CURRENT_DATE_TAG);
            }
        });

        endDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(END_DATE_TAG);
            }
        });

        return builder.create();
    }

    private void showDatePicker(String tag) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        // If you're calling this from a support Fragment
        dpd.show(getFragmentManager(), tag);
        // If you're calling this from an AppCompatActivity
        // dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    private void getDefaultData() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        int monthOfYear = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);
        String time = TIME_PREFIX + hour + ":" + minute;
        String date = DATE_PREFIX+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        currentTimeEditText.setText(time);
        currentDateEditText.setText(date);
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog dpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR),
                now.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getContext())
        );
        // If you're calling this from a support Fragment
        dpd.show(getFragmentManager(), "timePickerDialog");
        // If you're calling this from an AppCompatActivity
        // dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    private void initViews(View view) {
        //Buttons
        saveButton = view.findViewById(R.id.add_reminder_common_buttons_save_btn);
        cancelButton = view.findViewById(R.id.add_reminder_common_buttons_cancel_btn);
        //EditTexts
        eventNameEditText = view.findViewById(R.id.add_reminder_common_variables_event_name_et);
        notificationMessageEditText = view.findViewById(R.id.add_reminder_common_variables_notification_message_et);
        currentDateEditText = view.findViewById(R.id.add_reminder_common_variables_starting_calendar_et);
        endDateEditText = view.findViewById(R.id.add_reminder_common_variables_finishing_calendar_et);
        currentTimeEditText = view.findViewById(R.id.add_reminder_common_variables_clock_et);
        frequencyEditText = view.findViewById(R.id.add_daily_reminder_how_many_days_et);
    }

    //Listeners

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            listener = (AddDailyReminderListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement AddReminderDialogListener");
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        currentTimeEditText.setText(TIME_PREFIX + hourOfDay + ":" + minute);
        //Toast.makeText(getContext(), "Saat: " + hourOfDay + "Minute: " + minute + "Second: " + second, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = DATE_PREFIX+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        String tag = view.getTag();
        if(tag.equals(CURRENT_DATE_TAG)){
            currentDateEditText.setText(date);
        }else{
            endDateEditText.setText(date);
        }
    }


    public interface AddDailyReminderListener{
        void getDailyInfo(String eventName, String notifMessage, String currentDate, String endDate, String currentTime, String frequency);
    }
}
