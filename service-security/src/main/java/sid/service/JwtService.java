package sid.service;


import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import sid.Entity.Token;
import sid.dto.UserDto;

public interface JwtService {

	JwtClaimsSet generateToken(UserDto user);
	
	String refreshToken(UserDto userDto);
	
	String accessToken(UserDto userDto);

	
	UserDto verfierToken(String token);
	
	Token saveToken(String email,UUID tokenUUID);
	
}
