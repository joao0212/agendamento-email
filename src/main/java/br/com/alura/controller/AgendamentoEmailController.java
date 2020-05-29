package br.com.alura.controller;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.servico.AgendamentoEmailServico;

@Path("/emails")
public class AgendamentoEmailController {

	@EJB
	private AgendamentoEmailServico servico;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.ok(servico.listar()).build();
	}

}
