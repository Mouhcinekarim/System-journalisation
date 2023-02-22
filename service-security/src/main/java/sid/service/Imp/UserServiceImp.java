package sid.service.Imp;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sid.Entity.Role;
import sid.Entity.User;
import sid.dto.SignInRequest;
import sid.dto.UserDto;
import sid.mapper.UserMapper;
import sid.repo.RepoUser;
import sid.service.RoleService;
import sid.service.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService ,UserDetailsService{

	private RepoUser repoUser;
	
	private UserMapper userMapper;
	
	private PasswordEncoder passwordEncoder;
	
	private RoleService roleService;
	 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		String usrname=username.split("&&")[0];
		String email=username.split("&&")[1];
		
		User user=repoUser.findByUsername(usrname)
				.orElseThrow(()-> new UsernameNotFoundException("username not found"));
		if(!user.getEmail().equals(email)) throw new UsernameNotFoundException("email not found  or not correspond username");
		
		Set<GrantedAuthority> authorities= new HashSet<>();
		
		user.getRoles().forEach((role)->{
			role.getPermissions().forEach((permmission)-> authorities.add(new SimpleGrantedAuthority(permmission.getName())));
		});
	   
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public void signIn(SignInRequest signupRequest) {

		User user=userMapper.requestUserToUser(signupRequest);
		user.setIsActive(true);
		user.setPassword( passwordEncoder.encode(user.getPassword()));
		repoUser.save(user);
		signupRequest.getRoleNames().forEach((roleName)->{
			roleService.addRoleToUser(roleName, user.getEmail());
		});
		
	}

	@Override
	public UserDto getUserByUserName(String username) {

		User user=repoUser.findByUsername(username).orElseThrow(()-> new RuntimeException("user not found"));
		UserDto userDto=userMapper.userToDto(user);

		return userDto;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user=repoUser.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
		UserDto userDto=userMapper.userToDto(user);
		return userDto;
	}

	@Override
	public UserDto getUserByUserNameAndEmail(String email, String username) {
		UserDto userDto= getUserByUserName(username);
		if(!userDto.getEmail().equals(email)) throw new RuntimeException("email not correspond username");
		return userDto;
	}

	@Override
	public UserDto updateUser(SignInRequest signInRequest) {
		// TODO Auto-generated method stub
		User user= repoUser.findByEmail(signInRequest.getEmail()).orElseThrow(()->new RuntimeException("user not found"));
		if(!signInRequest.getPassword().isEmpty()) user.setPassword(passwordEncoder.encode(signInRequest.getPassword()));
		if(!signInRequest.getUsername().isEmpty()) user.setUsername(signInRequest.getUsername());
		signInRequest.getRoleNames().forEach((rolename)->{
			Role role=roleService.getRoleByName(rolename);
			if(!user.getRoles().contains(role))
			              user.addRole(role);
		});
		return userMapper.userToDto(repoUser.save(user));
	}

}
