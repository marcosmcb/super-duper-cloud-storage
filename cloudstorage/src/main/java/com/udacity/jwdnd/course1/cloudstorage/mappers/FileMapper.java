package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE FROM FILES " +
            "WHERE fileid = #{fileId} " +
            "AND userid = #{userId}")
    int deleteFileById(Integer fileId, Integer userId);

    @Select("SELECT * FROM FILES " +
            "WHERE userid = #{userId}")
    List<File> getFilesByUserId(Integer userId);

    @Select("SELECT * FROM FILES " +
            "WHERE fileid = #{fileId} " +
            "AND userid = #{userId}")
    File getFileById(Integer fileId, Integer userId);

    @Select("SELECT * FROM FILES " +
            "WHERE filename = #{fileName} " +
            "AND userid = #{userId}")
    File getFileByName(String fileName, Integer userId);
}
