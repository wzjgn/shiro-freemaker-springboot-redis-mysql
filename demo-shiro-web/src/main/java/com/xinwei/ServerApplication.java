package com.xinwei;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.xinwei"})
@EnableJpaRepositories("com.xinwei.dao")
public class ServerApplication implements CommandLineRunner {

	@Override
	public void run(String... args) {
		log.info("初始化系统...");

	}


	 

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ServerApplication.class, args);
	}
}

