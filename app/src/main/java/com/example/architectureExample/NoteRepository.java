package com.example.architectureExample;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;

    NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();
    }

    void insert(Note note){
        new InsertNoteAsyncTask(noteDAO).execute(note);
    }

    void update(Note note){
        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }

    void delete(Note note){
        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }

    void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDAO).execute();
    }

    LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDAO noteDAO;

        InsertNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDAO noteDAO;

        UpdateNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDAO noteDAO;

        DeleteNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDAO noteDAO;

        DeleteAllNotesAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAllNotes();
            return null;
        }
    }
}