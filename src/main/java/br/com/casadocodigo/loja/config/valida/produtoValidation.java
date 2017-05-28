package br.com.casadocodigo.loja.config.valida;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class produtoValidation implements Validator {

	public void valida(Produto produto, Errors errors) {

	}

	@Override
	//se o objeto é um produto, chama o validador
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Produto produto = (Produto) target;
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		if (produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}
	}

}
