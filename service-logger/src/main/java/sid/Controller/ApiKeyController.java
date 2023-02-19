package sid.Controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sid.service.ServiceApiKey;

@Controller
@RequestMapping("apiKey")
public class ApiKeyController {

	private ServiceApiKey serviceApiKey;
	
	public ApiKeyController(ServiceApiKey serviceApiKey) {
		super();
		this.serviceApiKey = serviceApiKey;
	}

	@GetMapping("/loginByApi")
	ResponseEntity<String> LoginByUserId( String userId) {
		 return  new ResponseEntity<String>( serviceApiKey.LoginByUserId(userId),HttpStatus.OK) ;
	}
	
	@PostMapping(value="createApiKey")
	ResponseEntity<String> CreateApiKey(String userId) {
		System.out.println(userId);
		return  new ResponseEntity<String>(serviceApiKey.CreateApiKey(userId),HttpStatus.OK) ;
	}
	
	@GetMapping("/isValideKey")
	ResponseEntity<Boolean> isValideKey(String key) {
		return new ResponseEntity<Boolean>(serviceApiKey.isValideKey(key),HttpStatus.OK) ;
	}
	
}
