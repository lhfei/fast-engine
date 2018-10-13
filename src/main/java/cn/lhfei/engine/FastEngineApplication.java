package cn.lhfei.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.lhfei.engine.orm.mapper.LineitemMapper;

@SpringBootApplication
public class FastEngineApplication {
	
	public FastEngineApplication(LineitemMapper lineitemMapper) {
		this.lineitemMapper = lineitemMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(FastEngineApplication.class, args);
	}
	
	private LineitemMapper lineitemMapper;
}
