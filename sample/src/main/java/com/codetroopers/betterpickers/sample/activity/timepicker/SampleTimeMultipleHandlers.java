package com.codetroopers.betterpickers.sample.activity.timepicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.codetroopers.betterpickers.sample.R;
import com.codetroopers.betterpickers.sample.activity.BaseSampleActivity;
import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;

/**
 * User: derek Date: 3/17/13 Time: 3:59 PM
 */
public class SampleTimeMultipleHandlers extends BaseSampleActivity
        implements TimePickerDialogFragment.TimePickerDialogHandler {

    private AppCompatTextView mResultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_and_button);

        mResultTextView = (AppCompatTextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        mResultTextView.setText(R.string.no_value);
        button.setText(R.string.time_picker_set);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerBuilder tpb = new TimePickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .addTimePickerDialogHandler(new MyCustomHandler());
                tpb.show();
            }
        });
    }

    class MyCustomHandler implements TimePickerDialogFragment.TimePickerDialogHandler {

        @Override
        public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
            Toast.makeText(SampleTimeMultipleHandlers.this, "MyCustomHandler onDialogTimeSet!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
        mResultTextView.setText(getString(R.string.time_picker_result_value, String.format("%02d", hourOfDay), String.format("%02d", minute)));
    }
}
