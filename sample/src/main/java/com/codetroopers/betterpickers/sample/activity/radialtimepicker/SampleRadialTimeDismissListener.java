package com.codetroopers.betterpickers.sample.activity.radialtimepicker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.widget.AppCompatTextView;

import com.codetroopers.betterpickers.OnDialogDismissListener;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.sample.R;
import com.codetroopers.betterpickers.sample.activity.BaseSampleActivity;

public class SampleRadialTimeDismissListener extends BaseSampleActivity
        implements RadialTimePickerDialogFragment.OnTimeSetListener, OnDialogDismissListener {

    private static final String FRAG_TAG_TIME_PICKER = "timePickerDialogFragment";

    private AppCompatTextView mResultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_and_button_colored);

        mResultTextView = (AppCompatTextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        mResultTextView.setText(R.string.no_value);
        button.setText(R.string.radial_time_picker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(SampleRadialTimeDismissListener.this)
                        .setOnDismissListener(SampleRadialTimeDismissListener.this);
                rtpd.show(getSupportFragmentManager(), FRAG_TAG_TIME_PICKER);
            }
        });
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        mResultTextView.setText(getString(R.string.radial_time_picker_result_value, hourOfDay, minute));
    }

    @Override
    public void onResume() {
        // Example of reattaching to the fragment
        super.onResume();
        RadialTimePickerDialogFragment rtpd = (RadialTimePickerDialogFragment) getSupportFragmentManager().findFragmentByTag(FRAG_TAG_TIME_PICKER);
        if (rtpd != null) {
            rtpd.setOnTimeSetListener(this);
        }
    }

    @Override
    public void onDialogDismiss(DialogInterface dialoginterface) {
        mResultTextView.setText(R.string.dialog_dismissed);
    }
}
