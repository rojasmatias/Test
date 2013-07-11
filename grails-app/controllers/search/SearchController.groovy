package search

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray;
import org.codehaus.groovy.grails.web.json.JSONObject;

class SearchController {

	def index() {
		redirect(action: "buscar")
	}

	def buscar(){
		// curl https://api.mercadolibre.com/sites/MLA/search?q=ipod
		def search=params.item
		withRest(id: "generator", uri: "https://api.mercadolibre.com/sites/MLA/") {
			def response = get(path: "search", query: [
				q: search.toString(),
				state:'AR-X',
				accepts_mercadopago:'yes',
				condition:'new',
				shipping:'free',
//				power_seller:'yes',
//				has_pictures:"yes",
				format: 'json'
				])
			def categoriesList = []
			
			def categoriesJson = response.data
			
			def categoriesJsonObj=new JSONObject(categoriesJson.toString())
//			categoriesJson.value("result")
			def categoriesJA = new JSONArray(categoriesJsonObj.get("results"))
			
			for(int i = 0; i < categoriesJA.length(); i++ ){
				def categoria = new Search()
				def jo = new JSONObject(categoriesJA.get(i).toString())
				
				categoria.thumbnail = jo.get("thumbnail")
				categoria.permalink = jo.get("permalink")
				categoria.price = jo.get("price")
				categoria.title = jo.get("title")
				
				categoriesList.add(categoria)
			}
							
			[categoriaInstanceList: categoriesList, 	Total:categoriesList.size()]
		}
	}
}
