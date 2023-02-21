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

	private String serviceName;
	private String methode;
	private String requestId;
	private String endpoids;
	private int statusCode;
	//[key,value]
	private String params;
	private String bidies;
	private Boolean anonymous;
	private String userName;
	private String role;
	private  String keyValue;
}
