package com.lms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.lms.config.TestSecurityConfig;

@SpringBootTest
@Import(TestSecurityConfig.class)
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
