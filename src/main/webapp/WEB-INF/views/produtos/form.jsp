<%@ page
  language="java"
  contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib
  uri="http://java.sun.com/jsp/jstl/core"
  prefix="c"%>
<%@ taglib
  uri="http://www.springframework.org/tags/form"
  prefix="form"%>
<%@ taglib
  uri="http://www.springframework.org/tags"
  prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros</title>
</head>
<body>
  <h1>Casa do Codigo</h1>
  <!-- PC = ProdutosController -->
  <form:form 
    action="${s:mvcUrl('PC#gravar').build()}"
    method="POST" enctype="multipart/form-data"
    commandName="produto">
    <div>
      <form:errors path="*" />
    </div>
    <div>
      <label>Titulo</label>
      <form:input path="titulo" />
      <form:errors path="titulo" />
    </div>
    <div>
      <label>Descrição</label>
      <form:textarea
        rows="10"
        cols="20"
        path="descricao"></form:textarea>
      <form:errors path="descricao" />
    </div>
    <div>
      <label>Paginas</label>
      <form:input path="paginas" />
      <form:errors path="paginas" />
    </div>
    <div>
      <label>Data de Lançamento</label>
      <form:input path="dataLancamento" />
      <form:errors path="dataLancamento" />
    </div>
    <c:forEach
      varStatus="status"
      items="${tiposLista}"
      var="tipoPreco">
      <div>
        <label>${tipoPreco}</label>
        <form:input path="precos[${status.index}].valor" />
        <!-- precos por conta do binding de Produto-->
        <form:hidden
          path="precos[${status.index}].tipo"
          value="${tipoPreco}" />
      </div>
    </c:forEach>
    <div>
      <label>Sumário</label>
      <input type="file" name="sumario" />
      <form:errors path="sumarioPath" />
    </div>
    <button type="submit">Cadastrar</button>
  </form:form>
</body>
</html>