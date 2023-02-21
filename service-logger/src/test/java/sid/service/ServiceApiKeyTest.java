package sid.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


import sid.ServiceLoggerApplication;
import sid.Dto.ResponseApiKeyDto;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLoggerApplication.class)
public class ServiceApiKeyTest {

	@Autowired
	private ServiceApiKey serviceApiKey;

	

	String useeId="user";

	String key;
	
	
	

	@Test
	void testCreateApiKey() {
		key=serviceApiKey.CreateApiKey(useeId+"1");
		Assertions.assertNotNull(key);
	}

	@Test
	void testLoginByUserId() {
		key=serviceApiKey.CreateApiKey(useeId+"2");
		String keyLogin=serviceApiKey.LoginByUserId(useeId+"2");
		
		Assertions.assertEquals(key, keyLogin);
		
		
		
	}

	@Test
	void testIsValideKey() {
		key=serviceApiKey.CreateApiKey(useeId+"4");
		Assertions.assertTrue(serviceApiKey.isValideKey(key)); 
	}

	@Test
	void testGetApiKey() {
		key=serviceApiKey.CreateApiKey(useeId+"5");
		ResponseApiKeyDto apiKeyDto=serviceApiKey.getApiKey(key);
		assertEquals(apiKeyDto.getKeyValue(), key);
		assertEquals(apiKeyDto.getUserId(),useeId+"5");
		
	}

}
