package com.example.noteprojectsematec;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNote extends AppCompatActivity {
    EditText edtTitleNote,edtTxtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        edtTitleNote = findViewById(R.id.edtTitleNote);
        edtTxtNote = findViewById(R.id.edtNoteText);

        Button btnCreateNote=findViewById(R.id.btnCreateNote);

        btnCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  TitleNote= edtTitleNote.getText().toString().trim();
                String TxtNote = edtTxtNote.getText().toString().trim();

                if(TitleNote.isEmpty() || TitleNote.length() == 0 || TitleNote.equals("") || TitleNote == null) {
                    Toast.makeText(CreateNote.this, "فیلد عنوان  را پر نمایید", Toast.LENGTH_LONG).show();
                }
                else {

                    Intent intent=getIntent();

                    intent.putExtra("TitleNote", TitleNote);
                    intent.putExtra("TxtNote", TxtNote);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }
            }
        });
    }
}
