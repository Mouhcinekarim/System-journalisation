package sid.service;


import sid.dto.AuthenticationResponse;
import sid.dto.LoginRequest;

public interface AuthService  {
	
	AuthenticationResponse loginByPassword(LoginRequest loginRequest);
	
	AuthenticationResponse loginByRefreshToken(LoginRequest loginRequest);
	
	
	}
