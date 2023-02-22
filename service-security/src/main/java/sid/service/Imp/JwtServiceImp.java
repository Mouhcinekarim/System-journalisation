package sid.service.Imp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import sid.Entity.Token;
import sid.Entity.User;
import sid.dto.AuthenticationResponse;
import sid.dto.RefreshTokenRequest;
import sid.dto.UserDto;
import sid.repo.RepoToken;
import sid.repo.RepoUser;
import sid.service.JwtService;

public class JwtServiceImp implements JwtService{

	@Value("${jwt.accessToken.expiration.time}")
	private Long expertTimeAccessToken;
	@Value("${jwt.refreshToken.expiration.time}")
	private Long expertTimeRefreshToken;
	
	private RepoToken repoToken;
	
	private RepoUser repoUser;
	
	private JwtDecoder jwtDecoder;
	
	private JwtEncoder jwtEncoder;
	
	@Override
	public JwtClaimsSet generateToken(UserDto user) {
		Instant instant=Instant.now();
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
				.subject(user.getUsername()+"&&"+user.getEmail())
				.issuedAt(instant)
				.build();
				
		if(!user.getPermissions().isEmpty()) {
			String scope=user.getPermissions().stream().collect(Collectors.joining(" "));
			jwtClaimsSet=JwtClaimsSet.from(jwtClaimsSet)
					.claim("scope", scope)
					.build();
			
		} 
		return jwtClaimsSet;
	}

	@Override
	public String refreshToken(UserDto userDto) {
		// TODO Auto-generated method stub
		JwtClaimsSet jwtClaimsSet=generateToken(userDto);
		JwtClaimsSet newJwtClaimsSet=JwtClaimsSet.from(jwtClaimsSet)
				.expiresAt(jwtClaimsSet.getIssuedAt().plusMillis(expertTimeRefreshToken))
				.build();
		String token=jwtEncoder.encode(JwtEncoderParameters.from(newJwtClaimsSet)).getTokenValue();
		
		return saveToken(userDto.getEmail(),UUID.fromString(token)).getToken().toString();
		
	}

	
	
	@Override
	public UserDto verfierToken(String token) {

		Token tokenfind=repoToken.findbyToken(UUID.fromString(token)).orElseThrow(()->new RuntimeException("token not found"));
		
		return getUserByToken(tokenfind.getToken().toString());
	}

	private UserDto getUserByToken(String token) {
		Jwt jwt=jwtDecoder.decode(token);
		String username=jwt.getSubject().split("&&")[0];
		String email=jwt.getSubject().split("&&")[1];
		String scope=jwt.getClaimAsString("scope");
		
		return UserDto.builder()
				.email(email)
				.username(username)
				.permissions(Set.of( scope.split(" ")))
				.build();
		
	}
	
	@Override
	public Token saveToken(String email,UUID tokenUUID) {
		// TODO Auto-generated method stub
		User user=repoUser.findByEmail(email).orElseThrow(()->new RuntimeException("token not found"));
		Token token=Token.builder()
				.dateCreated(Instant.now())
				.token(tokenUUID)
				.user(user)
				.build();
		return repoToken.save(token);
	}

	@Override
	public String accessToken(UserDto userDto) {
		// TODO Auto-generated method stub
		JwtClaimsSet jwtClaimsSet=generateToken(userDto);
		JwtClaimsSet newJwtClaimsSet=JwtClaimsSet.from(jwtClaimsSet)
				.expiresAt(jwtClaimsSet.getIssuedAt().plusMillis(expertTimeAccessToken))
				.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(newJwtClaimsSet)).getTokenValue();
	}
	
	

	

}
