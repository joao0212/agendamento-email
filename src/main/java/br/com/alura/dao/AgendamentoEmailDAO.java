package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager em;

	public List<AgendamentoEmail> listar() {
		return em.createQuery("select ae from AgendamentoEmail ae", AgendamentoEmail.class).getResultList();
	}

	public void inserir(AgendamentoEmail agendamentoEmail) {
		em.persist(agendamentoEmail);
	}

	public AgendamentoEmail listarPorEmail(AgendamentoEmail agendamentoEmail) {
		try {
			Query query = em.createQuery(
					"select ae from AgendamentoEmail ae where ae.email = :email and ae.agendado = false",
					AgendamentoEmail.class);
			query.setParameter("email", agendamentoEmail.getEmail());
			return (AgendamentoEmail) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<AgendamentoEmail> listarPorNaoAgendado() {
		return em.createQuery("select ae from AgendamentoEmail ae where ae.agendado = false", AgendamentoEmail.class)
				.getResultList();
	}

	public void alterar(AgendamentoEmail agendamentoEmail) {
		em.merge(agendamentoEmail);
	}
}
