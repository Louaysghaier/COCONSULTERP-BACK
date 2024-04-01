package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.User;

import java.util.List;

public interface GroupChatInterface {

    // Method to create a group chat for a role
    GroupChat createGroupChat(RoleName roleName, GroupChat groupChat);

    // Method to affect a user with the same role to the same group chat
    GroupChat addUserToGroupChat(RoleName roleName, GroupChat groupChat, User user);

    // Method to disaffect a user from the group chat
    GroupChat removeUserFromGroupChat(GroupChat groupChat, User user);

    // Method to update an existing group chat for a role
    GroupChat updateGroupChat(RoleName roleName, Long groupId);

    // Method to delete an existing group chat for a role
    GroupChat deleteGroupChat(RoleName roleName, Long groupId);

    // Method to retrieve all group chats for a role
    List<GroupChat> getAllGroupChatsByRole(RoleName roleName);

    // Method to retrieve a specific group chat for a role
    GroupChat getGroupChatByRoleAndId(RoleName roleName, Long groupId);
}
