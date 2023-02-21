package sid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class SignUpRequest {

	private String username;
	private String email;
	private String password;
	private String roleName;
	
}
