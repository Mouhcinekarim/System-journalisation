package sid.entity;


import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Logger {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String serviceName;
	private String methode;
	private Instant dateRequest;
	private String requestId;
	private String endpoids;
	private int statusCode;
	//[key,value]
	private String params;
	private String bidies;
    @Column(columnDefinition = "boolean default true")
	private Boolean anonymous;
	private String userName;
	private String role;
	@ManyToOne()
	private ApiKey apiKey;
	
}


