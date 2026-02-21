package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProjectCrudApplication {

	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		System.out.println("=== Iniciando aplicação Java com Hibernate ===");
		
		try {
			// Inicializa o EntityManagerFactory
			emf = Persistence.createEntityManagerFactory("project_crud_pu");
			System.out.println("Hibernate configurado com sucesso!");
			System.out.println("Banco de dados H2 em: ./data/usuariodb");
			
			// Exemplo de uso
			EntityManager em = emf.createEntityManager();
			System.out.println("EntityManager criado com sucesso!");
			em.close();
			
			System.out.println("\nAplicação rodando. Use TerminalMain.java para testar CRUD.");
			
		} catch (Exception e) {
			System.err.println("Erro ao inicializar aplicação: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (emf != null && emf.isOpen()) {
				emf.close();
			}
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory("project_crud_pu");
		}
		return emf;
	}
}
