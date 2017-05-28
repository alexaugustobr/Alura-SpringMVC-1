<%@ page
  language="java"
  contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib
  uri="http://java.sun.com/jsp/jstl/core"
  prefix="c"%>
<%@ taglib
  uri="http://www.springframework.org/tags"
  prefix="s"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
<a href="/carrinho" rel="nofollow">
Seu Carrinho ( ${carrinhoCompras.quantidade} ) 
</a>
  <h1>Lista de Produtos</h1>
  <div>${sucesso }</div>

  <table>
    <thead>
      <tr>
        <th>Titulo</th>
        <th>Descrição</th>
        <th>Paginas</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach
        items="${produtos}"
        var="produto">
        <tr>
          <td><a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">${produto.titulo}</a></td>
          <td>${produto.descricao}</td>
          <td>${produto.paginas}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>