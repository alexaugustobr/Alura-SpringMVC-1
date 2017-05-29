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
<title>Carrinho</title>
</head>
<body>
  <section class="container middle">
    <h2 id="cart-titulo">Seu carrinho de compras</h2>
      <a
    href="${s:mvcUrl('CCC#itens').build()}"
    rel="nofollow"> Seu Carrinho ( ${carrinhoCompras.quantidade} ) </a>
    <table id="cart-table">
      <colgroup>
        <col class="item-col">
        <col class="item-preco-col">
        <col class="item-quantity-col">
        <col class="line-preco-col">
        <col class="delete-col">
      </colgroup>
      <thead>
        <tr>
          <th class="cart-img-col"></th>
          <th width="65%">Item</th>
          <th width="10%">Preço</th>
          <th width="10%">Quantidade</th>
          <th width="10%">Total</th>
          <th width="5%"></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach
          items="${carrinhoCompras.itens}"
          var="item">
          <tr>
            <td class="cart-img-col"><img
                src=""
                alt="${item.produto.titulo}" /></td>
            <td class="item-titulo">${item.produto.titulo}-
              ${item.tipoPreco}</td>
            <td class="numeric-cell">${item.tipoPreco}</td>
            <td class="quantity-input-cell">
            <input
                type="number"
                min="0"
                readonly="readonly"
                value="${carrinhoCompras.getQuantidade(item)}"/>
                </td>
            <td class="numeric-cell">${carrinhoCompras.getTotal(item)}</td>
            <td class="remove-item">
            <form:form
                method="post"
                >
                <input
                  type="image"
                  src="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/excluir.png?58522"
                  alt="Excluir"
                  title="Excluir" />
              </form:form></td>
          </tr>
        </c:forEach>
      </tbody>
      <tfoot>
        <tr>
        
          <td colspan="2"><form:form
              
              method="post">
              <input
                type="submit"
                class="checkout"
                name="checkout"
                value="Finalizar compra "
                id="checkout" />
            </form:form></td>
          <td class="numeric-cell">${carrinhoCompras.total}</td>
          <td></td>
        </tr>
      </tfoot>
    </table>
    
    <h2>
      <a href="http://www.casadocodigo.com.br">Veja todos os livros
        que publicamos!</a>
    </h2>
  </section>
</body>
</html>