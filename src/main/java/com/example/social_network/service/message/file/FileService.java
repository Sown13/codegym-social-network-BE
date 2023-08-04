package com.example.social_network.service.message.file;

import com.example.social_network.model.message.File;
import com.example.social_network.repo.message.IFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService implements IFileService{
    @Autowired
    private IFileRepo fileRepo;
    @Override
    public Iterable<File> findAll() {
        return null;
    }

    @Override
    public Optional<File> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public File save(File file) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
