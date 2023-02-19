package sid.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sid.Dto.ResponseApiKeyDto;
import sid.entity.ApiKey;
import sid.mapper.ApiKeyMapper;
import sid.repo.ApiKeyRepo;

@Service
@AllArgsConstructor
public class ServiceApiKey {

	@Autowired
	private final ApiKeyRepo apiKeyRepo;
	@Autowired
	private final ApiKeyMapper apiKeyMapper;
	

	public String CreateApiKey(String userId) {
		UUID Key=UUID.randomUUID();
		
		ApiKey apiKey=ApiKey.builder()
				.keyValue(Key.toString())
				.userId(userId)
				.isActice(true)
				.dateCreated(Instant.now())
				.dateExpert(Instant.now().plus(3,ChronoUnit.DAYS))
				.build();
		apiKeyRepo.save(apiKey);
		
		return Key.toString();
	}
	
	

	public String LoginByUserId(String UserId ){
		
		ApiKey apiKey=apiKeyRepo.findByUserId(UserId).orElseThrow(()->new RuntimeException("User Not found"));
	
		isValideKey(apiKey.getKeyValue());
	    return apiKey.getKeyValue().toString();
		
	}
	
	public boolean isValideKey(String Key) {
	
		ApiKey apiKey=apiKeyRepo.findByKeyValue(Key).orElseThrow(()-> new RuntimeException("key not fount"));
		if(!apiKey.isActice()) throw new RuntimeException("ApiKey not active");
		if(Instant.now().isAfter(apiKey.getDateExpert())) throw new RuntimeException("key expert");
		return true;
	}	
	
	public ResponseApiKeyDto getApiKey(String Key) {
		ApiKey apiKey=apiKeyRepo.findByKeyValue(Key).orElseThrow(()->new RuntimeException("key not found"));
		return apiKeyMapper.ApiKeyToDto(apiKey);
	}
}