package com.example.architectureExample;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Objects;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.architectureExample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.architectureExample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.architectureExample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.architectureExample.EXTRA_PRIORITY";

    private EditText Title, Description;
    private NumberPicker Priority;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Title = findViewById(R.id.EditTitleId);
        Description = findViewById(R.id.EditDescriptionId);
        Priority = findViewById(R.id.NumberPickerPriorityId);

        Priority.setMaxValue(10);
        Priority.setMinValue(1);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("EDIT NOTE");
            Title.setText(intent.getStringExtra(EXTRA_TITLE));
            Description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            Priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.saveNote) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = Title.getText().toString();
        String description = Description.getText().toString();
        int priority = Priority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "please add a valid title or description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
