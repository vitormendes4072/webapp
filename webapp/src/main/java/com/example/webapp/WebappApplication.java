package com.example.webapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	// Teste de conexão com o banco
	@Bean
	public CommandLineRunner testDatabase(DataSource dataSource) {
		return args -> {
			try (Connection conn = dataSource.getConnection()) {
				System.out.println("✅ Conexão com o banco de dados estabelecida com sucesso!");
				System.out.println("🔗 URL: " + conn.getMetaData().getURL());
				System.out.println("👤 Usuário: " + conn.getMetaData().getUserName());
			} catch (Exception e) {
				System.err.println("❌ Falha na conexão com o banco: " + e.getMessage());
			}
		};
	}

}
