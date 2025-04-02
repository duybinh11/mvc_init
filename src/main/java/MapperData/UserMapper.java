package MapperData;

import DTO.Request.UserRequest;
import DTO.Response.UserResponse;
import Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserEntity toUserEntity(UserRequest userRequest);
    UserResponse toUserResponse(UserEntity userEntity);
    public void updateUser(UserRequest userRequest,@MappingTarget UserEntity userEntity);
}
