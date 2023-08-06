package com.example.social_network.repo.message;

import com.example.social_network.model.message.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepo extends JpaRepository<File,Long> {
}
