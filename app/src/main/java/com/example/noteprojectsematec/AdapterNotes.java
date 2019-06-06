package com.example.noteprojectsematec;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noteprojectsematec.Models.NoteBookModels;

import java.util.ArrayList;

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.NoteViewHolder>
{
    private final ArrayList<NoteBookModels> myList;
    private final AdapterNotes.OnItemClickListener listener;

    public AdapterNotes(ArrayList<NoteBookModels> list, AdapterNotes.OnItemClickListener listener) {
        myList = list;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(View item);
    }
    @NonNull
    @Override
    public AdapterNotes.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note_recycle, viewGroup, false);
        AdapterNotes.NoteViewHolder holder = new AdapterNotes.NoteViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotes.NoteViewHolder noteViewHolder, int i) {
        String title = myList.get(i).getTitle();

        String noid = String.valueOf(myList.get(i).getId());
        noteViewHolder.txtTitle.setText(title);

        noteViewHolder.txtnid.setText(noid);
        noteViewHolder.bind(noteViewHolder.root, listener);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

     class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        TextView txtnid;
        View root;
        public NoteViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            txtnid = itemView.findViewById(R.id.txtnoteid);
            root = itemView;
        }
        public void bind(final View item, final AdapterNotes.OnItemClickListener listener) {


            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
