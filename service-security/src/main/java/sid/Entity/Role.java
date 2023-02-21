package sid.Entity;

import java.util.ArrayList;
import java.util.List;

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
	private Boolean isActice;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_user")
	private List<User> users=new ArrayList<User>();
}
