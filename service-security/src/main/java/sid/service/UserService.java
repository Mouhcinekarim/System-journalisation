package sid.service;

import sid.dto.SignInRequest;
import sid.dto.UserDto;

public interface UserService {

	void signIn( SignInRequest signupRequest );
	
	UserDto getUserByUserName(String username);
	
	UserDto getUserByEmail(String email);
	
	UserDto getUserByUserNameAndEmail(String email,String username);
	
	UserDto updateUser(SignInRequest signInRequest);

}
