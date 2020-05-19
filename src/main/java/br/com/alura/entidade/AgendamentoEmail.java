package br.com.alura.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class AgendamentoEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank(message = "E-mail não pode ser nulo ou vazio")
	@Email(message = "E-mail inválido!")
	private String email;

	@Column
	@NotBlank(message = "Assunto não pode ser nulo ou vazio")
	private String assunto;

	@Column
	@NotBlank(message = "Mensagem não pode ser nulo ou vazio")
	private String mensagem;

	@Column
	private Boolean agendado;

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAgendado() {
		return agendado;
	}

	public void setAgendado(Boolean agendado) {
		this.agendado = agendado;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
