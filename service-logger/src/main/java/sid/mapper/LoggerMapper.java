package sid.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import sid.Dto.RequestLoggerDto;
import sid.Dto.ResponseLoggerDto;

import sid.entity.Logger;

@Mapper(componentModel = "spring",uses = Logger.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LoggerMapper {
	ResponseLoggerDto ToResponseLoggerDto(Logger logger);
	List<ResponseLoggerDto> ToResponseLoggerDtos(List<Logger> loggers);
	Logger ToLogger(RequestLoggerDto requestLoggerDto);
}
