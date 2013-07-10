package search

class User {

	String userName
	String firstName
	String lastName
	String password
	//  static hasMany = [todos: Todo, categories: Category]
	static constraints = {
		userName(blank:false,unique:true)
		firstName(blank:false)
		lastName(blank:false)
		password(blank:false)
	}
	String  toString () {
		"$lastName, $firstName"
	}
}
