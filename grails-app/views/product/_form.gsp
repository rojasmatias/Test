<%@ page import="search.Product" %>



<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="product.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'subtitle', 'error')} ">
	<label for="subtitle">
		<g:message code="product.subtitle.label" default="Subtitle" />
		
	</label>
	<g:textField name="subtitle" value="${productInstance?.subtitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="product.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${productInstance?.title}"/>
</div>

