package com.cbre.cm.dsf.ds.deal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import java.util.logging.Logger;
@EnableAspectJAutoProxy
@SpringBootApplication
public class DealApplication {


	public static void main(String[] args) {
		SpringApplication.run(DealApplication.class, args);
	}
}
