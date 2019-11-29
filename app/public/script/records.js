/* Initializing all of the variables for the records pages */
var patients = [
	["aw3ks35d2","Bland","Kelly"],
	["bd9s3nm32","Williams","Lana"],
	["me983hc29","Kent","Tom"],
	["jor921w2n","Smith","Don"],
	["ut3923fj9","Jones","Sally"],
	["p435fjh32","Nord","Lance"]
];

var pat1 = {
	"firstName": "Kelly",
	"lastName": "Bland",
	"id": "aw3ks35d2"
}

var pat2 = {
	"firstName": "Lana",
	"lastName": "Williams",
	"id": "bd9s3nm32"
}

var pat3 = {
	"firstName": "Tom",
	"lastName": "Kent",
	"id": "me983hc29"
}

var pat4 = {
	"firstName": "Don",
	"lastName": "Smith",
	"id": "jor921w2n"
}

var pat5 = {
	"firstName": "Sally",
	"lastName": "Jones",
	"id": "ut3923fj9"
}

var pat6 = {
	"firstName": "Lance",
	"lastName": "Nord",
	"id": "p435fjh32"
}

var patientsJSON = [
	pat1, pat2, pat3, pa4, pat5, pat6
]

patients.forEach(patListGen);

/* Initialization of patients list */

function patListGen(item) {
	var table = document.getElementById("patList");
	var row = table.insertRow(1);
	for(i=0;i<3;i++) {
		var cell = row.insertCell(i);
		cell.innerHTML=item[i];
	}
	var cell = row.insertCell(i);
	var a = document.createElement("A");
	var img = document.createElement('img');
    img.src = "fwdIcon.png";
    img.className="tblIcons";
    a.setAttribute("href","patient.html");
    a.appendChild(img);
    cell.appendChild(a);

}
