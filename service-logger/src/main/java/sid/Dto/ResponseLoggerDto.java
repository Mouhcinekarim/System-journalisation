package sid.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoggerDto {
	private String serviceName;
	private String methode;
	private Instant dateRequest;
	private String endpois;
	private String keyValue;
	private String userName;
	private String role;
	private String params;
	private String bidies;
}
