package Service;


import DTO.Request.UserRequest;
import DTO.Response.UserResponse;
import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import MapperData.UserMapper;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse add (UserRequest userRequest) {
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity =  userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    public List<UserResponse> all() {
        return userRepository.findAll().stream().map(u-> userMapper.toUserResponse(u)).toList();
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public UserResponse fix (UserRequest userRequest,int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        userMapper.updateUser(userRequest,userEntity.get());
        return userMapper.toUserResponse(userRepository.save(userEntity.get()));
    }

    public boolean login(UserRequest userRequest) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(userRequest.getUsername());
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        return passwordEncoder.matches(userRequest.getPassword(), userEntity.get().getPassword());
    }
}
