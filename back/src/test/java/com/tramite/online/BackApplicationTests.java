package com.tramite.online;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(ContainerConfiguration.class)
@SpringBootTest
class BackApplicationTests {

	@Test
	void contextLoads() {
	}

}
