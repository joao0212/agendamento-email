package br.com.alura.jobs;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Singleton
public class JobAgendamentoEmail {

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@EJB
	private AgendamentoEmailServico agendamentoEmailServico;

	@Schedule(hour = "*", minute = "*")
	public void enviar() throws InterruptedException {
		List<AgendamentoEmail> listaNaoAgendados = agendamentoEmailServico.listarPorNaoAgendado();
		listaNaoAgendados.forEach(na -> {
			context.createProducer().send(this.queue, na);
			agendamentoEmailServico.alterar(na);
		});
	}
}
