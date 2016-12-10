package br.com.fiap.combustivel.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.combustivel.dao.CadastroDAO;
import br.com.fiap.combustivel.model.Cadastro;

@Path("/combustivelServicesWS")
public class CombustivelServices {

	private CadastroDAO cadastroDAO = new CadastroDAO();
	
	@GET
	@Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
	public Response consultar(@PathParam("id")Long id) {
		try {
			Cadastro cadastro = cadastroDAO.findById(id);
			if(cadastro != null) {
				return Response.status(200).entity(cadastro).build();
			}
		} catch (Exception e) {
			return Response.status(200).entity("Erro ao consultar! talvez o id esteja incorreto.").build();
		}
		return Response.status(200).entity("Nenhum registro localizado!").build();
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cadastro cadastro) {
		try {
			cadastroDAO.salvar(cadastro);
		} catch (Exception e) {
			return Response.status(200).entity("Entidade Não cadastrada! Remova o campo Id ou tente novamente!").build();
		}
		return Response.status(200).entity(cadastro).build();
	}
	
	@GET
	@Path("consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cadastro> consultarCadastros() {
		return cadastroDAO.listar();
	}
	
	@DELETE
	@Path("/deletar/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String deletar(@PathParam("id")Long id) {
		try {
			cadastroDAO.deletar(id);
		} catch (Exception e) {
			return "Erro ao deletar o registro!";
		}
		return "Registro deletado com sucessso!";
		
	}
	
}
