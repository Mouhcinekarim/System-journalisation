package sid.Dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApiKeyDto {
	private String keyValue;
	private Instant dateCreated;
	private String userId;
	private Instant dateExpert;
	private boolean isActice;
	private List<ResponseApiKeyDto> loggers;
}
