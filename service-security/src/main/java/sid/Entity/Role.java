package sid.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	private Integer roleId;
	private String roleName;
	private Boolean isActice;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_user")
	private List<User> users=new ArrayList<User>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission")
    private Set<Permission> permissions;
    
    public void addPermission(Permission permission) {
    	permissions.add(permission);
    	permission.addRole(this);
    }
    
    public void AddUser(User user) {
    	users.add(user);
    	user.addRole(this);
    }
}
