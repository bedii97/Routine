package com.example.routine.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.routine.R;

public class AddMonthlyReminder extends DialogFragment {

    private EditText editText1, editText2;
    private AddMonthlyReminderListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_reminder_dialog, null);
        builder.setView(view)
                .setTitle("Deneme")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String et1 = editText1.getText().toString();
                        String et2 = editText2.getText().toString();
                        listener.getMonthlyInfo(et1, et2);
                    }
                });

        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            listener = (AddMonthlyReminderListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement AddReminderDialogListener");
        }
    }

    public interface AddMonthlyReminderListener{
        void getMonthlyInfo(String et1, String et2);
    }
}
