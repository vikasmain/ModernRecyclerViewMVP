package com.cogoport.architectureComponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cogoport.R;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_note";

    private EditText etNewNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent resultIntent = new Intent();
        if (TextUtils.isEmpty(etNewNote.getText())) {
            setResult(RESULT_CANCELED, resultIntent);
        } else {
            String note = etNewNote.getText().toString();
            resultIntent.putExtra(NOTE_ADDED, note);
            setResult(RESULT_OK, resultIntent);
        }
        finish();
    }
}