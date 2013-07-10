package search

import grails.converters.JSON

class SearchController {

	def index() {
		redirect(action: "buscar")
	}

	def buscar(){
		// curl https://api.mercadolibre.com/sites/MLA/search?q=ipod
		def search=params.item
		withHttp(id: "generator", uri: "https://api.mercadolibre.com/sites/MLA/") {
			def response = get(path: "search", query: [
				q: search,
				state:'AR-X',
				accepts_mercadopago:'yes',
				condition:'new',
//				shipping:'free',
//				power_seller:'yes',
//				has_pictures:"yes",
//				format: 'json'
				])

			render response as JSON
		}
	}
}
