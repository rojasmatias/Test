package search

import org.springframework.dao.DataIntegrityViolationException

class ProductController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		// curl https://api.mercadolibre.com/sites/MLA/search?q=ipod
		def search=params.item
		def productList = []
			
		def result
			
		withRest(id: "generator", uri: "https://api.mercadolibre.com/sites/MLA/") {
				def response = get(path: "search", query: [
					q: search.toString(),
					state:'AR-X',
					accepts_mercadopago:'yes',
					condition:'new',
	//				shipping:'free',
	//				power_seller:'yes',
	//				has_pictures:"yes",
	//				format: 'json'
					])
				result = response.data
				}

		def productsList = []
		def a = result.get("results")
		
		a.each {
			def producto = new Product()
			producto.id = it.get("id")
			producto.title = it.get("title")
			producto.subtitle = it.get("subtitle")
			producto.price = it.get("price") 
			//productsList.add()
			productsList.add(producto)
		}
		
        [productInstanceList: productsList, productInstanceTotal: productsList.size()]
    }

    def create() {
        [productInstance: new Product(params)]
    }

    def save() {
        def productInstance = new Product(params)
        if (!productInstance.save(flush: true)) {
            render(view: "create", model: [productInstance: productInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])
        redirect(action: "show", id: productInstance.id)
    }

    def show(Long id) {
        def productInstance = Product.get(id)
        if (!productInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), id])
            redirect(action: "list")
            return
        }

        [productInstance: productInstance]
    }

    def edit(Long id) {
        def productInstance = Product.get(id)
        if (!productInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), id])
            redirect(action: "list")
            return
        }

        [productInstance: productInstance]
    }

    def update(Long id, Long version) {
        def productInstance = Product.get(id)
        if (!productInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (productInstance.version > version) {
                productInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'product.label', default: 'Product')] as Object[],
                          "Another user has updated this Product while you were editing")
                render(view: "edit", model: [productInstance: productInstance])
                return
            }
        }

        productInstance.properties = params

        if (!productInstance.save(flush: true)) {
            render(view: "edit", model: [productInstance: productInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])
        redirect(action: "show", id: productInstance.id)
    }

    def delete(Long id) {
        def productInstance = Product.get(id)
        if (!productInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), id])
            redirect(action: "list")
            return
        }

        try {
            productInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'product.label', default: 'Product'), id])
            redirect(action: "show", id: id)
        }
    }
}
