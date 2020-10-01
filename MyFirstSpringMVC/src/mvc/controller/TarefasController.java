package mvc.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import
org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Tarefa;
import mvc.model.TarefaDAO;
@Controller
public class TarefasController {
	@RequestMapping("/")
	public String execute() {
	System.out.println("Lógica do MVC");
	return "info";
	}
	
	@RequestMapping("criaTarefa")
	 public String form() {
		return "formTarefa";
	 }
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.adicionaDescricao(tarefa);
		return "adicionada";
	}
	
	@RequestMapping("listaTarefa")
	public String lista() {
		TarefaDAO dao = new TarefaDAO();
		List<Tarefa> tarefas = dao.getLista();
		return "listagem";
	}

}
