/* Initializing all of the variables for the records pages */
var patients = [
	["aw3ks35d2","Bland","Kelly"],
	["bd9s3nm32","Williams","Lana"],
	["me983hc29","Kent","Tom"],
	["jor921w2n","Smith","Don"],
	["ut3923fj9","Jones","Sally"],
	["p435fjh32","Nord","Lance"]
];

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
