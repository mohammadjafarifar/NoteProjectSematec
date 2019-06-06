package com.example.noteprojectsematec;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteprojectsematec.Models.NoteBookModels;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DataBaseNoteBook Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Db = new DataBaseNoteBook(MainActivity.this, "NoteBook", null, 1);
        ArrayList<NoteBookModels> list = null;
        list = Db.AllNotes();

        if(list.size()>0)
        {


            TextView titlesss=findViewById(R.id.txtheadertitle);
            titlesss.setText("یادداشت ها");
        RecyclerView recycler = findViewById(R.id.recyclerNote);

        AdapterNotes adapter = new AdapterNotes(list, new AdapterNotes.OnItemClickListener() {
            @Override
            public void onItemClick(View item) {

                Intent Readnote =new Intent(MainActivity.this,readnotes.class);
                TextView Id = item.findViewById(R.id.txtnoteid);
                Readnote.putExtra("NoteId",Id.getText());
                startActivityForResult(Readnote,4);


                }




        });

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.CreateNote: {
                Intent CreatenoteActivity = new Intent(MainActivity.this, CreateNote.class);
                startActivityForResult(CreatenoteActivity,2);
            }
            break;
            case R.id.ListNotes:
            {
                Intent CreatenoteActivity = new Intent(MainActivity.this, MainActivity.class);
                startActivityForResult(CreatenoteActivity,2);
            }
            break;


        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2)
        {
            String TitleNote = data.getStringExtra("TitleNote");
            String TxtNote = data.getStringExtra("TxtNote");


           Db.InsertNote(TitleNote,TitleNote);

            Toast.makeText(this, "یادداشت شما ذخیره شد", Toast.LENGTH_LONG).show();
        }
        if(requestCode==4)
        {

            String noteId = data.getStringExtra("noteId");
            Integer Type=data.getIntExtra("Type",0);
            if(Type==1)
            {
                String Title=data.getStringExtra("Title");
                String Text=data.getStringExtra("Text");
                Db.updateNote(noteId,Title,Text);
                Toast.makeText(this,"با موفقیت ویرایش شد.", Toast.LENGTH_LONG).show();
            }
            else if(Type==2)
            {
                Db.deleteById(noteId);
                Toast.makeText(this,"با موفقیت پاک شد.", Toast.LENGTH_LONG).show();
            }
            ArrayList<NoteBookModels> list = Db.AllNotes();
            RecyclerView recycler = findViewById(R.id.recyclerNote);

            AdapterNotes adapter = new AdapterNotes(list, new AdapterNotes.OnItemClickListener() {
                @Override
                public void onItemClick(View item) {

                    Intent Readnote =new Intent(MainActivity.this,readnotes.class);
                    TextView Id = item.findViewById(R.id.txtnoteid);
                    Readnote.putExtra("NoteId",Id.getText());
                    startActivityForResult(Readnote,4);


                }




            });

            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }

    }
}
