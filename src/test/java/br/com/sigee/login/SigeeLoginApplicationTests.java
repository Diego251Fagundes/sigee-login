package br.com.sigee.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "sigee.session.mongodb.enabled=false")
class SigeeLoginApplicationTests {

	@Test
	void contextLoads() {
	}

}
