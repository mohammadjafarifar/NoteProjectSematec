package com.example.noteprojectsematec.Models;

public class NoteBookModels {
    private int Id;
    private String Title;
    private String CreateDate;
    private String txt;

    public String getDate(){
        return String.valueOf(CreateDate);
    }

    public String getTitle(){
        return Title;
    }
    public NoteBookModels(int id, String title, String ndate, String ntext)
    {
        this.Id = id;
        this.Title = title;
        this.CreateDate = ndate;
        this.txt = ntext;
    }

    public int getId(){
        return Id;
    }


    public String getText(){
        return txt;
    }
}
