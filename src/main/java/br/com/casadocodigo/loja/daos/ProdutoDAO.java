package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

//mapeia classe no spring
//@Component
//repositorios de controle de DAOs, repository vai mapear produto dao, repository herda component

@Repository
// dao vai ter transações de bd
@Transactional
public class ProdutoDAO {

	// gerente de entidades
	// injetar dependencia
	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		//bug lazy initialization, por conta do precos
		// return manager.find(Produto.class, id);
		return manager.createQuery(
				"select distinct(p) from Produto p join fetch p.precos preco where p.id = :id",Produto.class)
				.setParameter("id", id).getSingleResult();
	}

}
