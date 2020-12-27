package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NoteMapper noteMapper;

    public NotesService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesByUserId(Integer userId) {
        return this.noteMapper.getNotesByUserId(userId);
    }

    public void updateNote(Note noteForm) {
        this.noteMapper.update(noteForm);
    }

    public void insertNote(Note noteForm) {
        this.noteMapper.insertNote(noteForm);
    }

    public void deleteNote(Integer noteId, Integer userId) {
        this.noteMapper.deleteNoteByUserId(noteId, userId);
    }
}
