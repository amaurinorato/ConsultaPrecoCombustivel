package br.com.fiap.combustivel.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.combustivel.model.Cadastro;

@Path("/combustivelServicesWS")
public class CombustivelServices {

	public static List<Cadastro> cadastros;
	
	static {
		cadastros = new ArrayList<Cadastro>();
		Cadastro cadastro = new Cadastro();
		cadastro.setCombustivel("ETANOL");
		cadastro.setLatitude(123456);
		cadastro.setLongitude(-1234566);
		cadastro.setNomePosto("Shell Carrefour raposo tavares");
		cadastro.setValor(2.89);
		cadastros.add(cadastro);
	}
	
	@GET
	@Path("/{nomePosto}/{combustivel}")
    @Produces({MediaType.APPLICATION_JSON})
	public Response consultar(@PathParam("nomePosto")String nomePosto, @PathParam("combustivel")String combustivel) {
		for (Cadastro cadastro : cadastros) {
			if(nomePosto.equals(cadastro.getNomePosto()) && combustivel.equals(cadastro.getCombustivel()))
			return Response.status(200).entity(cadastro).build();
		}
		return Response.status(200).entity("Não localizado").build();
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cadastro cadastro) {
		for (Cadastro cadastroLoop : cadastros) {
			if(cadastro.getNomePosto().equals(cadastroLoop.getNomePosto()) && cadastroLoop.getCombustivel().equals(cadastro.getCombustivel())) {
				cadastros.set(cadastros.indexOf(cadastroLoop), cadastro);
				return Response.status(200).entity(cadastroLoop).build();
			}
		}
		cadastros.add(cadastro);
		return Response.status(200).entity(cadastro).build();
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cadastro> consultarCadastros() {
		return cadastros;
	}
}
