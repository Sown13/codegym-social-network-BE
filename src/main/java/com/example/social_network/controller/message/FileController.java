package com.example.social_network.controller.message;

import com.example.social_network.service.message.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/file")
public class FileController {
    @Autowired
    private IFileService fileService;
}
