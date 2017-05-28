package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.config.valida.produtoValidation;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

//cuida de requisições
@Controller
@RequestMapping(value = "/produtos")
public class ProdutosController {

	// injetar dependencia
	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	// liga produto com produto validator
	public void initBinder(WebDataBinder binder) {
		// adiciona o validador ao binder
		binder.addValidators(new produtoValidation());

	}

	// carrega forumario
	@RequestMapping("form")
	public ModelAndView form(Produto produto) {
		ModelAndView mnv = new ModelAndView("produtos/form");
		mnv.addObject("tiposLista", TipoPreco.values());
		return mnv;
	}

	@RequestMapping(method = RequestMethod.POST)
	// Biding dos parametros sao feitos pelo spring
	// baseados na JSP, mesmo sendo um objeto
	// a ORDEM dos parametros importa
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result,
			RedirectAttributes redirectAttributes) {

		// System.out.println(sumario.getOriginalFilename());

		if (result.hasErrors()) {
			return form(produto);
		}
		String sumarioPath = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(sumarioPath);
		produtoDAO.gravar(produto);
		// mnv.addObject("sucesso","produto cadastrado com sucesso!");
		// return listar();
		// necescita de redirect, aways redirect after post
		ModelAndView mnv = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "produto cadastrado com sucesso!");
		return mnv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mnv = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDAO.listar();
		mnv.addObject("produtos", produtos);
		return mnv;
	}
	
	//via metadata
//	@RequestMapping("/detalhe")
//	public ModelAndView detalhe(Integer id){
//		ModelAndView mnv = new ModelAndView("produtos/detalhe");
//		Produto produto = produtoDAO.find(id);
//		mnv.addObject("produto",produto);
//		return mnv;
//	}
	
	//via url path detalhe/id
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Integer id){
		ModelAndView mnv = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		mnv.addObject("produto",produto);
		return mnv;
	}

	public FileSaver getFileSaver() {
		return fileSaver;
	}

	public void setFileSaver(FileSaver fileSaver) {
		this.fileSaver = fileSaver;
	}
}
