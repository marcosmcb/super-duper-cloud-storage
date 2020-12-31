package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Delete("DELETE FROM NOTES " +
            "WHERE noteid = #{noteId} " +
            "AND userid = #{userId}")
    void deleteNoteByUserId(Integer noteId, Integer userId);

    @Select("SELECT * FROM NOTES " +
            "WHERE userid = #{userid}")
    List<Note> getNotesByUserId(Integer userid);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE " +
                "noteid = #{noteId} AND " +
                "userid = #{userId}")
    void update(Note note);
}
