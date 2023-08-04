package com.example.social_network.repo.message;

import com.example.social_network.model.message.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupMemberRepo extends JpaRepository<GroupMember,Long> {
}
