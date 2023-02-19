package sid.entity;


import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiKey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer keyId;
//	@Column(unique = true)
	private String keyValue;
	private Instant dateCreated;
	private Instant dateExpert;
	private boolean isActice;
//	@Column(unique = true)
	private String userId;
	@OneToMany(mappedBy = "apiKey",fetch = FetchType.EAGER)
	List<Logger> loggers;
	
}
