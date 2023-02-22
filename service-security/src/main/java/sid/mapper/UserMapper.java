package sid.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sid.Entity.User;
import sid.dto.SignInRequest;
import sid.dto.UserDto;
@Mapper(componentModel = "spring",uses = User.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface UserMapper {
	User requestUserToUser(SignInRequest signInRequest);
	@Mapping(source = "roles.permissions.name",target ="permission")
	UserDto userToDto(User user);
}
