package com.example.social_network.repo.message;

import com.example.social_network.model.message.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGroupRepo extends JpaRepository<Group,Long> {
    @Query(value = "SELECT * FROM user_group ug JOIN group_member gm ON ug.group_id = gm.group_group_id WHERE gm.user_user_id = :userId",nativeQuery = true)
    List<Group> findGroupsByUserId(@Param("userId") Long userId);
}
