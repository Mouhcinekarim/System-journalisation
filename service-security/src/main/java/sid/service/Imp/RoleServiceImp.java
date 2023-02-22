package sid.service.Imp;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sid.Entity.Permission;
import sid.Entity.Role;
import sid.Entity.User;
import sid.repo.RepoPermision;
import sid.repo.RepoRole;
import sid.repo.RepoUser;
import sid.service.RoleService;

@Service
@Transactional
public class RoleServiceImp implements RoleService{

    private RepoRole repoRole;
	
    private RepoPermision repoPermision;
    
    private RepoUser repoUser;
	
    
    
    
	public RoleServiceImp(RepoRole repoRole, RepoPermision repoPermision, RepoUser repoUser) {
		super();
		this.repoRole = repoRole;
		this.repoPermision = repoPermision;
		this.repoUser = repoUser;
	}

	public Role addRole(Role role) {
		return repoRole.save(role);
	}
	
	public Permission addPermission(Permission permission) {
		return repoPermision.save(permission);
	}
	
	public void addRoleToPermission(String roleName,String permissionName) {
		
		Role role=repoRole.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("role not found"));
		Permission permission=repoPermision.findByName(permissionName).orElseThrow(()-> new RuntimeException("permission not found"));
		
		permission.addRole(role);
		repoRole.save(role);
		repoPermision.save(permission);	
	}
	
	public void addRoleToUser(String roleName,String email) {
		
		User user=repoUser.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
		Role role=repoRole.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("role not found"));

		user.addRole(role);
		repoRole.save(role);
		repoUser.save(user);
	}
	
	public Set<Permission> getPermissionByRole(String roleName){
		return repoRole.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("role not Fount")).getPermissions();
	}
	
	public List<Role> getRoleByUserName(String userName){
		return repoUser.findByUsername(userName).orElseThrow(()-> new RuntimeException("role not Fount")).getRoles();
	}

	@Override
	public Role getRoleByName(String rolename) {
		
		return repoRole.findByRoleName(rolename).orElseThrow(()-> new RuntimeException("role not Fount"));
		
	}
	
	
	
	
}
