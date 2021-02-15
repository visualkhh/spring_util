package com.clone.chat.service;

import com.clone.chat.code.MsgCode;
import com.clone.chat.domain.User;
import com.clone.chat.exception.BusinessException;
import com.clone.chat.exception.ErrorTrace;
import com.clone.chat.model.UserToken;
import com.clone.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class FriendService {

    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    public User search(String userId) {
        Optional<User> findUser = userRepository.findByIdEquals(userId);
        if(findUser.isPresent()) {
            return findUser.get();
        }
        return null;
    }

    @Transactional
    public void addFriend(String userId, String friendId) {
        Optional<User> findUser = userRepository.findById(userId);
        Optional<User> findFriend = userRepository.findById(friendId);

        if(!findUser.isPresent() || !findFriend.isPresent()) {
            throw new BusinessException(MsgCode.ERROR_ENTITY_NOT_FOUND, ErrorTrace.getName());
        }

        if(findUser.get().getId() == findFriend.get().getId()) {
            throw new BusinessException(MsgCode.ERROR_INVALID_FRIEND_RELATIONSHIP, ErrorTrace.getName());
        }

        if(findUser.get().getFriends().contains(findFriend.get()))
            throw new BusinessException(MsgCode.ERROR_INVALID_FRIEND_RELATIONSHIP, ErrorTrace.getName());

        findUser.get().addFirend(findFriend.get());
        userService.save(findUser.get());
        refreshFriends(userId);
    }

    @Cacheable(value = "friends", key = "#userId")
    public User findFriends(String userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isPresent())
            return findUser.get();
        return null;
    }

    @CacheEvict(value = "friends", key = "#userId")
    public void refreshFriends(String userId) {
    }
}
