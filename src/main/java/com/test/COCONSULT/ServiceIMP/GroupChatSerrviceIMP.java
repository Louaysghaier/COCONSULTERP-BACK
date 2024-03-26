package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.GroupChat;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.GroupChatInterface;
import com.test.COCONSULT.Reposotories.GroupChatRepository;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
@Service
@AllArgsConstructor


public class GroupChatSerrviceIMP implements GroupChatInterface {
    @Autowired
    GroupChatRepository groupChatRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public GroupChat createGroupChat(RoleName roleName, GroupChat groupChat) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat with role " + roleName + " Notfound"));
        GroupChat existingGroupChat = groupChatRepository.findByRole(role);

        if (existingGroupChat != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group chat with role " + roleName + " already exists");
        } else {
            groupChat.setRole(role);

            return groupChatRepository.save(groupChat);
        }
    }


    @Override
    public Void addUserToGroupChatByRole(Long IdGroupChat, Long IdUser) {
        User user =userRepository.findById(IdUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Notfound"));
        GroupChat groupChat =groupChatRepository.findById(IdGroupChat)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"grpchat notfound"));
        if(groupChat.getUsers().contains(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User all ready in this group chat");

        }
        if (!user.getRoles().contains(groupChat.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have the necessary role to join this group chat");
        }

        if(!user.isBannedchatGP()){
        groupChat.getUsers().add(user);
        groupChatRepository.save(groupChat);
        }
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does is blocked  to join this group chat");
        return null;


    }


    @Override
    public GroupChat removeUserFromGroupChat(GroupChat groupChat, User user) {
        if (!groupChat.getUsers().contains(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not a member of this group chat");
        }
        groupChat.getUsers().remove(user);
        return groupChatRepository.save(groupChat);
    }

    @Override
    public GroupChat updateGroupChat(Long groupId, String groupTitle, String rules) {
        GroupChat groupChat = groupChatRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group chat not found with ID: " + groupId));

        if (groupTitle != null) {
            groupChat.setGroupTitle(groupTitle);
        }
        if (rules != null) {
            groupChat.setRules(rules);
        }

        groupChatRepository.save(groupChat);

        return groupChat;
    }



    @Override
    public List<GroupChat> getAllGroupChatsByRole(RoleName roleName) {
        return groupChatRepository.findByRoleName(roleName);
    }


    @Override
    public GroupChat getGroupChatById(Long groupId) {
        return groupChatRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group chat not found with ID: " + groupId));
    }

    @Override
    public User banneduser(Long groupid, Long banneduser) {
        GroupChat groupChat = groupChatRepository.findById(groupid)
                .orElseThrow(() -> new RuntimeException("Group chat not found with ID: " + groupid));
        User Banneduser =userRepository.findById(banneduser).orElse(null);
        Banneduser.setBannedchatGP(true);

        userRepository.save(Banneduser);
        removeUserFromGroupChat(groupChat, Banneduser);
        return Banneduser;    }

    @Override
    public User removeban(Long iduser) {
        User Banneduser =userRepository.findById(iduser).orElse(null);
        Banneduser.setBannedchatGP(false);
        userRepository.save(Banneduser);
        //GroupChat groupChat =groupChatRepository.findByUsersContains(Banneduser);

       // addUserToGroupChatByRole(groupChat.getId(),Banneduser.getId());
        return Banneduser;
    }

    @Override
    public GroupChat deleteGroupChat(Long groupId) {
        GroupChat groupChat = groupChatRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group chat not found"));

        groupChatRepository.delete(groupChat);

        return groupChat;
    }

@Transactional
    public List<GroupChat> getAllGroupChats() {
        return groupChatRepository.findAll();
    }
}
