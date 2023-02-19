package sid.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


import sid.Dto.ResponseApiKeyDto;
import sid.entity.ApiKey;

@Mapper(componentModel = "spring",uses = ApiKey.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface ApiKeyMapper {
	
	ResponseApiKeyDto ApiKeyToDto(ApiKey apiKey);
}