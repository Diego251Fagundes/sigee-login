package br.com.sigee.login.config;

import org.mongodb.spring.session.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMongoHttpSession
@ConditionalOnProperty(
		name = "sigee.session.mongodb.enabled",
		havingValue = "true",
		matchIfMissing = true
)
public class SessionConfig {
}
