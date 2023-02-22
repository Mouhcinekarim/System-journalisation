package sid.service;

import java.util.List;
import java.util.Set;

import sid.Entity.Permission;
import sid.Entity.Role;

public interface RoleService {

	Role addRole(Role role);
	
	Permission addPermission(Permission permission);
	
	void addRoleToPermission(String roleName,String permissionName);
	
	void addRoleToUser(String roleName,String email);
	
	Set<Permission> getPermissionByRole(String roleName);
	
	List<Role> getRoleByUserName(String userName);
	
	Role getRoleByName(String rolename);
	
}
