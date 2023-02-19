package sid.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLoggerDto {
	private String keyValue;
	private String serviceName;
	private String methode;
	private String endpois;
}
