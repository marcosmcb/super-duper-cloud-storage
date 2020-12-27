package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    private CredentialMapper credentialMapper;

    public CredentialsService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentialsByUser(Integer userId) {
        try {
            return this.credentialMapper.getCredentialsByUserId(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int insertCredential(Credential credential) {
        return this.credentialMapper.insertCredential(credential);
    }

    public void deleteCredential(Integer credentialId, Integer userId) {
        this.credentialMapper.deleteCredentialById(credentialId, userId);
    }

    public void updateCredential(Credential credential) {
        this.credentialMapper.updateCredential(credential);
    }
}
