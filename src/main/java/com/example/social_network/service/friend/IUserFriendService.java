package com.example.social_network.service.friend;

import com.example.social_network.dto.dto.CountMutualFriendDTO;
import com.example.social_network.dto.dto.HaveBeenFriendsDTO;
import com.example.social_network.dto.dto.SourceUserFriendDTO;
import com.example.social_network.dto.dto.TargetUserFriendDTO;
import com.example.social_network.model.user_friend.UserFriend;
import com.example.social_network.service.IGeneralService;
import org.springframework.data.repository.query.Param;
import com.example.social_network.model.friend.dto.MutualFriendsDTO;

import java.util.List;
import java.util.Optional;

public interface IUserFriendService extends IGeneralService<UserFriend> {
    Iterable<UserFriend> findAllFriendsByUserId(Long id);
    Long countFriend(Long id);

   List<SourceUserFriendDTO> findUserFriendByUserFriendId(Long id);

    List<TargetUserFriendDTO> findUserFriendByTargetUser(Long id);

   Optional<UserFriend> findRelationShip(@Param("targetId") Long targetId, @Param("sourceId") Long sourceId);

    List<MutualFriendsDTO> findAcceptedUserFriendsByTargetUserId(Long targetUserId);

    CountMutualFriendDTO countAcceptedFriendsByUserId(@Param("userId") Long userId);


}
