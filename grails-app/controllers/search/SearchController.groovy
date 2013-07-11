package search

import grails.converters.JSON
import org.json.simple.JSONArray
import org.json.simple.JSONObject

class SearchController {

	def index() {
		redirect(action: "buscar")
	}

	def buscar(){
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
			//producto.price = it.get("price")
			//productsList.add()
			productsList.add(producto)
		}
			def s = productsList.first()
			render productsList
		}
	
}
