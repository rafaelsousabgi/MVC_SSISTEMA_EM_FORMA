package com.academia.em_forma.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.util.HistoricoAlunoAvaliacao;


public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long >{
	
	
	
	/**@Query("select a from AvaliacaoFisica a "
			+ "where "
			+ "	(a.id = :id and a.aluno.usuario.email like :email) "
			)
			AND a.aluno.usuario.email LIKE :email  AND a.instrutor.usuario.email LIKE :email
			*
			*@Query("select af from AvaliacaoFisica af where af.aluno.id = :id and af.aluno.usuario.email like :email ")
			
	
	
	
	@Query("SELECT a FROM AvaliacaoFisica a "
	        + "WHERE "
	        + "    ((a.aluno.id = :id AND a.aluno.usuario.email like :email) "
	        + "    OR "
	        + "    (a.instrutor.id = :id AND a.instrutor.usuario.email like :email ))")
	List<AvaliacaoFisica> findAvaliacoesFisicasByAlunoId(Long id, String email);
	 * @param pageable 

	
	@Query("select a from AvaliacaoFisica a where a.aluno.usuario.email like :email")
	List<AvaliacaoFisica> findListaAvaliacoesByAlunoEmail(String email, Pageable pageable);
			
	@Query("select ai from AvaliacaoFisica ai where ai.instrutor.usuario.email like :email")

	
	List<AvaliacaoFisica> findListaAvaliacoesByInstrutorEmail(String email, Pageable pageable);
	
	@Query("select a.id as id,"
			+"a.aluno as aluno,"
			+"CONCAT(a.dataInicio, '', a.dataFim) as datasAvaliacao"
			+"a.instrutor as instrutor,"
			+ "from AvaliacaoFisica a "
			+ " a.aluno.usuario.email like :email")
	@Query("select a.id as id,"
			+"a.aluno as aluno,"
			+"CONCAT(a.dataInicio, '', a.dataFim) as datasAvaliacao)"
			+"a.instrutor as instrutor,"
			+ "from AvaliacaoFisica a ,"
			+ "where a.aluno.usuario.email like :email")
	

	@Query("select a from AvaliacaoFisica a where a.aluno.usuario.email like :email")
	List<AvaliacaoFisica> findListaAvaliacoesByAlunoEmail(String email);

	@Query("select ai from AvaliacaoFisica ai where ai.instrutor.usuario.email like :email")
	List<AvaliacaoFisica> findListaAvaliacoesByInstrutorEmail(String email);

	
	
	
	@Query("select a.id as id, " +
	        "a.identificacao as identificacao, " +
	        "a.altura as altura, " +
	        "a.peito as peito, " +
	        "a.peso as peso, " +
	        "a.cintura as cintura, " +
	        "a.panturrilhaDireita as panturrilhaDireita, " +
	        "a.panturrilhaEsquerda as panturrilhaEsquerda, " +
	        "a.coxaDireita as coxaDireita, " +
	        "a.coxaEsqueda as coxaEsquerda, " +
	        "a.bracoEsquedo as bracoEsquedo, " +
	        "a.bracoDireito as bracoDireito, " +
	        "a.antebracoEsquedo as antebracoEsquedo, " +
	        "a.antebracoDireito as antebracoDireito, " +
	        "a.gluteo as gluteo, " +
	        "a.imc as imc, " +
	        "CONCAT(a.dataInicio, '', a.dataFim) as datasAvaliacao, " +
	        "a.instrutor as instrutor " +
	        "from AvaliacaoFisica a " +
	        "where a.aluno.usuario.email like :email")	
	Page<HistoricoAlunoAvaliacao> findHistoricoByAlunoEmail(String email, Pageable pageable);

**/	

	 @Query("select a from AvaliacaoFisica a where a.aluno.usuario.email like :email")
	    Page<AvaliacaoFisica> findListaAvaliacoesByAlunoEmail(String email, Pageable pageable);

	    @Query("select ai from AvaliacaoFisica ai where ai.instrutor.usuario.email like :email")
	    Page<AvaliacaoFisica> findListaAvaliacoesByInstrutorEmail(String email, Pageable pageable);
}
