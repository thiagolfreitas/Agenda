package br.com.agenda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;






import org.springframework.web.servlet.ModelAndView;

import br.com.agenda.dao.JpaUsuarioDao;
import br.com.agenda.interfaces.UsuarioDao;
import br.com.agenda.model.Usuario;

@Transactional
@Controller
public class UsuarioController {

	@Autowired
	JpaUsuarioDao dao;

	@RequestMapping("cadastrar")
	public String cadastrar(@Valid Usuario usuario,BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			System.out.println("Erro na valida��o");
			return "/novo";
		}
		
		dao.adiciona(usuario);
		return "ok";
	}
	
	@RequestMapping("logar")
	public String logar(Usuario user){
		if( dao.logarUsuario(user)){
			return "ok";
		}else{return "novo";}
		
	}
	@RequestMapping("listar")
	public ModelAndView listarUsuarios(JpaUsuarioDao dao){
		List<Usuario> lista = dao.listar();
		ModelAndView mv = new ModelAndView("listar");
		mv.addObject("listarUsuarios",lista);
		return mv;
	}
	
}
