
<%@ page import="search.Product" %>
<!DOCTYPE html>
<html>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="title" title="${message(code: 'product.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="subtitle" title="${message(code: 'product.subtitle.label', default: 'Subtitle')}" />
					
						<g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
					
						<g:sortableColumn property="image" title="${message(code: 'product.price.image', default: 'Image')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${productInstanceList}" status="i" var="productInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					<!-- <td><g:link action="show" id="${productInstance.id}">
						</g:link></td> -->	
						
						<td><a href="${productInstance.link}" target=”_blank”>
						${fieldValue(bean: productInstance, field: "title")}</a></td>
					
						<td>${fieldValue(bean: productInstance, field: "subtitle")}</td>
					
						<td>${fieldValue(bean: productInstance, field: "price")}</td>
						
						<td><img src="${fieldValue(bean: productInstance, field: "image")}" width="40" height="40"/></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
</html>
