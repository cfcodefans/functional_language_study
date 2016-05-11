interface Person {
	firstName: 	string;
	lastName:	string;
}

function greeter(person: Person) {
	return "Hello, " + person.firstName + " " + person.lastName;
}

var user = {firstName: "Jane", lastName: "User"};
console.info(greeter(user));