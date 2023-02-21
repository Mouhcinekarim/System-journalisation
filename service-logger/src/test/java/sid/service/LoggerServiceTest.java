package sid.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
//je veux dit a spring que je veux tester chez vous
import org.springframework.test.context.junit4.SpringRunner;

import sid.ServiceLoggerApplication;
import sid.Dto.RequestLoggerDto;
import sid.Dto.ResponseLoggerDto;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLoggerApplication.class)
public class LoggerServiceTest {

	private LoggerService loggerServicel;
	private ServiceApiKey serviceApiKey;
	
	public LoggerServiceTest(LoggerService loggerServicel, ServiceApiKey serviceApiKey) {
		super();
		this.loggerServicel = loggerServicel;
		this.serviceApiKey = serviceApiKey;
	}

	private String key;
	private RequestLoggerDto requestLoggerDto1,requestLoggerDto2,requestLoggerDto3;
	
	
	@BeforeEach
	public void initialize(){
		System.out.println(key);
		if(key==null) {
		key=serviceApiKey.CreateApiKey("user");
		System.out.println(key);
	    requestLoggerDto1=RequestLoggerDto.builder()
				.keyValue(key)
				.endpoids("saveProduct")
				.methode("post")
				.serviceName("product-service")	
				.build();
		requestLoggerDto2=RequestLoggerDto.builder()
				.keyValue(key)
				.endpoids("products")
				.methode("get")
				.serviceName("product-service")	
				.build();
	 requestLoggerDto3=RequestLoggerDto.builder()
				.keyValue(key)
				.endpoids("updateproducts")
				.methode("put")
				.serviceName("product-service")	
				.build();
	    loggerServicel.info(requestLoggerDto1);
		loggerServicel.info(requestLoggerDto2);
		loggerServicel.info(requestLoggerDto3);
		}

	}
	
	@Test
	void testSaveLogger() {
		RequestLoggerDto requestLoggerDto=RequestLoggerDto.builder()
				.keyValue(key)
				.endpoids("products")
				.methode("get")
				.serviceName("product-service")	
				.build();
		ResponseLoggerDto dto= loggerServicel.info(requestLoggerDto);
		assertNotNull(dto);
		assertEquals(dto.getMethode(), "get");
		assertEquals(dto.getServiceName(), "product-service");
		assertEquals(requestLoggerDto.getKeyValue(), key);
		
	}

	@Test
	void testGetLoggersByDate() {
		
		List<ResponseLoggerDto> dtos=loggerServicel.getLoggersbetweenDates(new Date(System.currentTimeMillis()-1000*60*60), new Date());
		assertTrue(dtos.size()>=3);
		
		for (ResponseLoggerDto Dto : dtos) {
			assertTrue(Dto.getDateRequest().isBefore(Instant.now()) && Dto.getDateRequest().isAfter(new Date(System.currentTimeMillis()-1000*60*60).toInstant()));
		}
	}

	@Test
	void testGetLoggersByApiKey() {

		List<ResponseLoggerDto> dtos=loggerServicel.getLoggersByApiKey(key);
		System.out.println(dtos);
		assertTrue(dtos.size()>=3);
		for (ResponseLoggerDto Dto : dtos) {
		assertEquals(Dto.getKeyValue(), key);
		}
		}

	

}
