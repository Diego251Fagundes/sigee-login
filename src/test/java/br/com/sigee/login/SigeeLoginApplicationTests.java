package br.com.sigee.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"sigee.session.mongodb.enabled=false",
		"spring.data.mongodb.auto-index-creation=false",
		"spring.mongodb.uri=mongodb://localhost:27017/sigee_login_test"
})
class SigeeLoginApplicationTests {

	@Test
	void contextLoads() {
	}

}
