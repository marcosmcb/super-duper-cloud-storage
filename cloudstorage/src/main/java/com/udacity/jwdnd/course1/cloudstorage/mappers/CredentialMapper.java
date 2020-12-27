package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS " +
            "WHERE credentialid = #{credentialId} " +
            "AND userid = #{userId}")
    void deleteCredentialById(Integer credentialId, Integer userId);

    @Select("SELECT * FROM CREDENTIALS " +
            "WHERE userid = #{userid}")
    List<Credential> getCredentialsByUserId(Integer userid);

    @Update("UPDATE CREDENTIALS " +
            "SET " +
                "url = #{url}, " +
                "username = #{username}, " +
                "key = #{key}, " +
                "password = #{password} " +
            "WHERE " +
            "credentialid = #{credentialId} AND " +
            "userid = #{userId}")
    void updateCredential(Credential credential);
}
