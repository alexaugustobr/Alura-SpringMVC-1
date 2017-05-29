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
<%@ taglib
  uri="http://java.sun.com/jsp/jstl/fmt"
  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${Produto.titulo}-Casa do Codigo</title>
</head>
<body>
  <a
    href="${s:mvcUrl('CCC#itens').build()}"
    rel="nofollow"> Seu Carrinho ( ${carrinhoCompras.quantidade} ) </a>
  <article id="${produto.id}">
    <header
      id="produto-highlight"
      class="clearfix">
      <div
        id="produto-overview"
        class="container">
        <img
          width="280px"
          height="395px"
          src=''
          class="produto-featured-image"
          alt="${produto.titulo}">
        <h1 class="produto-titulo">${produto.titulo}</h1>
        <p class="produto-author">
          <span class="produto-author-link"> ${produto.titulo} </span>
        </p>
        <p class="book-descricao">
          ${produto.descricao} Veja o
          <a
            href="${produto.sumarioPath}"
            target="_blank"> sumario </a>
          completo do livro!
        </p>
      </div>
    </header>
    <section class="buy-options clearfix">
      <form
        action='<c:url value="/carrinho/add"/>'
        class="container"
        method="POST">
        <input
          type="hidden"
          value="${produto.id}"
          name="produtoId" />
        <ul
          id="variants"
          class="clearfix">
          <c:forEach
            items="${produto.precos}"
            var="preco">
            <li class="buy-option">
              <input
                type="radio"
                name="tipoPreco"
                id="tipo"
                class="variant-radio"
                value="${preco.tipo}"
                ${preco.tipo.name() == 'COMBO' ? 'checked' : ''}>
              <label class="variant-label"> ${preco.tipo} </label>
              <p class="variant-preco">${preco.valor}</p>
            </li>
          </c:forEach>
        </ul>
        <button
          type="submit"
          class="submit-image icon-basket-alt"
          title="Compre agora '${produto.titulo}'!"
          value="comprar">Comprar Agora</button>
      </form>
    </section>
    <div class="container">
      <section class="author produto-detail">
        <h2 class="section-titulo">${produto.titulo}</h2>
        <p class="book-descricao">${produto.descricao}</p>
      </section>
      <section class="data produto-detail">
        <h2 class="section-titulo">Dados do livro:</h2>
        <p>
          Número de paginas: <span>${produto.paginas}</span>
        </p>
        <p>
          Data de publicação: <span><fmt:formatDate
              pattern="dd/MM/yyyy"
              value="${produto.dataLancamento.time}" /></span>
        </p>
        <p>
          Encontrou um erro?
          <a
            href='/submissao-errata'
            target='_blank'>Submeta uma errata </a>
        </p>
      </section>
    </div>
  </article>
</body>
</html>