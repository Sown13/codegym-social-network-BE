package com.example.social_network.model.message;

import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long messageId;
    private String textContent;
    private Date dateCreated;
    private boolean isRead;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnore
    private Group group;
    @OneToMany(mappedBy = "message")
    private List<File> files;

    public Message() {
    }

    public Message(Long messageId, String textContent, Date dateCreated, boolean isRead, User user, Group group, List<File> files) {
        this.messageId = messageId;
        this.textContent = textContent;
        this.dateCreated = dateCreated;
        this.isRead = isRead;
        this.user = user;
        this.group = group;
        this.files = files;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

}
