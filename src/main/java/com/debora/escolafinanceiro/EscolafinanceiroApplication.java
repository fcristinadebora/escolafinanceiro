package com.debora.escolafinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.debora.escolafinanceiro")
public class EscolafinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolafinanceiroApplication.class, args);
	}

}
