package com.example.teste.cadastro_funcionarios_clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.example.teste.cadastro_funcionarios_clientes.*")
@ComponentScan(basePackages = { "com.example.teste.cadastro_funcionarios_clientes.*" })
@EntityScan("com.example.teste.cadastro_funcionarios_clientes.*")  
@EnableTransactionManagement
public class CadastroFuncionariosClientesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CadastroFuncionariosClientesApplication.class, args);
	}

}
