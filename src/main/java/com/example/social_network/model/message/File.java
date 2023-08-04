package com.example.social_network.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long fileId;
    private String fileUrl;
    @ManyToOne
    @JsonIgnore
    private Message message;
}
