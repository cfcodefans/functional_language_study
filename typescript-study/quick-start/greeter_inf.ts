{
	interface Person {
		firstName: string;
		lastName: string;
	}

	function greeter(person: Person) {
		return "Hello, " + person.firstName + " " + person.lastName;
	}

	var _user = { firstName: "Jane", lastName: "User" };
	console.info(greeter(_user));
}