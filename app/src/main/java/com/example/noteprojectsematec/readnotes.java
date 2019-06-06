package com.example.noteprojectsematec;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteprojectsematec.Models.NoteBookModels;

public class readnotes extends AppCompatActivity {
    String noteId;
    TextView title,text;
    final DataBaseNoteBook Db = new DataBaseNoteBook(readnotes.this, "NoteBook", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readnotes);
        Intent intent = getIntent();
        noteId = intent.getStringExtra("NoteId");
        title = findViewById(R.id.edtEditTitleNote);
        text = findViewById(R.id.edtEditNoteText);
        NoteBookModels nm = Db.getNoteById(noteId);
        if (nm != null){
            title.setText(nm.getTitle());
            text.setText(nm.getText());
        }

        Button btnEdit=findViewById(R.id.btnEditNote);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String TitleNote=title.getText().toString();
                if(TitleNote.isEmpty() || TitleNote.length() == 0 || TitleNote.equals("") || TitleNote == null) {
                    Toast.makeText(readnotes.this, "فیلد عنوان  را پر نمایید", Toast.LENGTH_LONG).show();
                }
                else
                {
                Intent intent=getIntent();

                intent.putExtra("noteId", noteId);
                intent.putExtra("Title",title.getText().toString());
                intent.putExtra("Text",text.getText().toString());
                intent.putExtra("Type",1);
                setResult(Activity.RESULT_OK, intent);
                finish();
                }
            }
        });
        Button btnDelete=findViewById(R.id.btnDeleteNote);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();

                intent.putExtra("noteId", noteId);

                intent.putExtra("Type",2);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
