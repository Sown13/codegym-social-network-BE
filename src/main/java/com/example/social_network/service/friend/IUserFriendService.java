package com.example.social_network.service.friend;

import com.example.social_network.model.friend.dto.*;
import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserFriendService extends IGeneralService<UserFriend> {
    Iterable<UserFriend> findAllFriendsByUserId(Long id);
    Long countFriend(Long id);

   List<SourceUserFriendDTO> findUserFriendByUserFriendId(Long id);

    List<TargetUserFriendDTO> findUserFriendByTargetUser(Long id);

   HaveBeenFriendsDTO findRelationShip(@Param("targetId") Long targetId, @Param("sourceId") Long sourceId);
//    List<HaveBeenFriendsDTO> findUserFriendByTargetUserOrSourceUser(@Param("targetId") Long targetId, @Param("sourceId") Long sourceId);

    List<MutualFriendsDTO> findAcceptedUserFriendsByTargetUserId(Long targetUserId);

    CountMutualFriendDTO countAcceptedFriendsByUserId(@Param("userId") Long userId);


}
