package sid.Entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenId;
	private UUID token;
	private Instant dateCreated;
	@OneToOne(mappedBy = "token")
	private User user;
}
