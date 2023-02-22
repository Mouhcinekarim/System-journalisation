package sid.Conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import sid.utils.RsaKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private PasswordEncoder passwordEncoder;
	
	private RsaKey rsaKey;
	
	public SecurityConfig(PasswordEncoder passwordEncoder, RsaKey rsaKey) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.rsaKey = rsaKey;
	}

	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		var provider= new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return new ProviderManager(provider);
	}
	
	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk=new RSAKey.Builder(rsaKey.publicKey()).privateKey(rsaKey.privateKey()).build();
		JWKSource<SecurityContext> jwkSource=new ImmutableJWKSet<SecurityContext>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwkSource);
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		return  NimbusJwtDecoder.withPublicKey(rsaKey.publicKey()).build();
	}
	

}
