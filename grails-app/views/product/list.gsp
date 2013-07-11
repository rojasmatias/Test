
<%@ page import="search.Product" %>
<!DOCTYPE html>
<html>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="title" title="${message(code: 'product.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="subtitle" title="${message(code: 'product.subtitle.label', default: 'Subtitle')}" />
					
						<g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${productInstanceList}" status="i" var="productInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					<!-- <td><g:link action="show" id="${productInstance.id}">
						</g:link></td> -->	
						
						<td>${fieldValue(bean: productInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: productInstance, field: "subtitle")}</td>
					
						<td>${fieldValue(bean: productInstance, field: "price")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
</html>
