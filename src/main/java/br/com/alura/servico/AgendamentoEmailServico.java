package br.com.alura.servico;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.validation.Valid;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.interception.Logger;

@Logger
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoEmailServico {

	@EJB
	private AgendamentoEmailDAO agendamentoEmailDAO;

	public List<AgendamentoEmail> listar() {
		return this.agendamentoEmailDAO.listar();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserir(@Valid AgendamentoEmail agendamentoEmail) {
		AgendamentoEmail agendamentoEmailConferido = agendamentoEmailDAO.listarPorEmail(agendamentoEmail);
		if (agendamentoEmailConferido.getEmail() != null && agendamentoEmailConferido.getAgendado() != null) {
			throw new RuntimeException("E-mail já foi cadastrado!");
		}
		agendamentoEmail.setAgendado(false);
		this.agendamentoEmailDAO.inserir(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarPorNaoAgendado() {
		return agendamentoEmailDAO.listarPorNaoAgendado();
	}

	public void alterar(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(true);
		agendamentoEmailDAO.alterar(agendamentoEmail);
	}

	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		System.out.println("E-mail enviado: " + agendamentoEmail.getEmail());
	}
}
