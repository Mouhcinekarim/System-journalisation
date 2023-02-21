package sid.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sid.Dto.RequestLoggerDto;
import sid.Dto.ResponseLoggerDto;
import sid.entity.ApiKey;
import sid.entity.Logger;
import sid.mapper.LoggerMapper;
import sid.repo.ApiKeyRepo;
import sid.repo.LoggerRepo;

@Service

public class LoggerService {

	
	private final LoggerRepo loggerRepo;
	
	private final ApiKeyRepo apiKeyRepo;

	private final LoggerMapper loggerMapper;
	
	
	public LoggerService(LoggerRepo loggerRepo, ApiKeyRepo apiKeyRepo, LoggerMapper loggerMapper) {
		super();
		this.loggerRepo = loggerRepo;
		this.apiKeyRepo = apiKeyRepo;
		this.loggerMapper = loggerMapper;
	}

	@Transactional
	public ResponseLoggerDto  info(RequestLoggerDto requestLoggerDto ) {
		ApiKey apiKey=apiKeyRepo.findByKeyValue(requestLoggerDto.getKeyValue())
				                .orElseThrow(()->new RuntimeException("key not found"));
		Logger logger=loggerMapper.ToLogger(requestLoggerDto);
	
		logger.setApiKey(apiKey);
		
		logger.setDateRequest(Instant.now());
		apiKey.getLoggers().add(logger);
		apiKeyRepo.save(apiKey);
		return loggerMapper.ToResponseLoggerDto(loggerRepo.save(logger));  
	}
	
	public List<ResponseLoggerDto> getLoggersbetweenDates(Date date1,Date date2){
		
		return loggerMapper.ToResponseLoggerDtos(loggerRepo.findAllbetweenDates(date1.toInstant(),date2.toInstant())
				.orElseThrow(()->new RuntimeException("loggers not found in this date")));			
	}
	
	public List<ResponseLoggerDto> getLoggersByApiKey(String key){
		return loggerMapper.ToResponseLoggerDtos(apiKeyRepo.findByKeyValue(key)
				.orElseThrow(()-> new RuntimeException("ApiKey not found"))
				.getLoggers());
	}

	
	
	 
	
}