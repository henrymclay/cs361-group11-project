/////////////////////////////////////////////////////////////////////
// Name: Group 11 / Henry Clay
// Date: 11/19
// Assignment: SE1 Project
// Comments: Runner for the reminder app based on projects from 290
// and 325 (and some personal hacking) 
// Uses Node to serve an express / handlebars page. 
/////////////////////////////////////////////////////////////////////

var express = require('express');
var path = require('path');
var bodyParser = require("body-parser");

var app = express();
var handlebars = require('express-handlebars').create({defaultLayout:'main'});
//var mysql = require('./dbcon.js'); // not used YET!

app.use( bodyParser.json() );       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}));

app.use(express.json());       // to support JSON-encoded bodies
app.use(express.urlencoded()); // to support URL-encoded bodies

app.engine('handlebars', handlebars.engine);
app.set('view engine', 'handlebars');
var handlebars = require('express-handlebars').create({defaultLayout:'main'});


app.set('port', 44); // a port for serving the site
//app.set('port', 3607); // a port for serving the site -- debug

app.use(express.static('public')); // for static resources

////////////////////////////////////////////////////////////////////////////////////
// records script section
// some redundant code from pre-conversion records.js
////////////////////////////////////////////////////////////////////////////////////


/* Initializing all of the variables for the records pages */
// array not used here, just for records
var patients = [
	["aw3ks35d2","Bland","Kelly"],
	["bd9s3nm32","Williams","Lana"],
	["me983hc29","Kent","Tom"],
	["jor921w2n","Smith","Don"],
	["ut3923fj9","Jones","Sally"],
	["p435fjh32","Nord","Lance"]
];

// json below should be the same data as the above array

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

var recordsJSON = [
	pat1, pat2, pat3, pat4, pat5, pat6
]

//patientsJSON.forEach(patListGenJSON); 

/* Initialization of patients list */

// leaving for posterity but this should be covered w/ handlebars
/*
function patListGen(item) {
	var table = document.getElementById("patList");
  var row = table.insertRow(1);
  */
  // iterates through 3 part array
  /*
  for(i=0;i<3;i++) {
		var cell = row.insertCell(i);
		cell.innerHTML=item[i];
  }
  */

  // these break out above w/ JSON access
  /*
  var idCell = row.insertCell(0);
  idCell.innerHTML = item.id;

  var fNameCell = row.insertCell(1);
  fNameCell.innterHTML = item.firstName;

  var lNameCell = row.insertCell(2);
  lNameCell.innerHTML = item.lastName;
  */
  // no i... so needed to hardcode the 3
  /*
	var finalCell = row.insertCell(3);
	var a = document.createElement("A");
	var img = document.createElement('img');
  
  img.src = "fwdIcon.png";
  img.className="tblIcons";
  a.setAttribute("href","patient.html");
  a.appendChild(img);
  finalCell.appendChild(a);
  
}
*/
////////////////////////////////////////////////////////////////////////////////////
// patient script section
// some redundant code from pre-conversion patient.js
////////////////////////////////////////////////////////////////////////////////////

/* Initializing all of the variables for the records pages */
/*
var medication = [
	["aw3ks35d2","100mg","Aspirin","08:04","1","01/01/2020"],
	["aw3ks35d2","100mg","Aspirin","13:04","1","01/01/2020"],
	["aw3ks35d2","100mg","Aspirin","18:04","1","01/01/2020"],
	["bd9s3nm32","50mg","Penicilin","09:31","0","12/01/2019"],
	["me983hc29","200mg","Lisinoprol","11:15","1","12/12/2019"],
	["me983hc29","200mg","Ibuprofen","12:31","1","03/01/2020"],
	["jor921w2n","50mg","Amoxicillin","06:10","1","11/29/2019"],
	["jor921w2n","50mg","Amoxicillin","10:10","1","11/29/2019"],
	["jor921w2n","50mg","Amoxicillin","14:10","1","11/29/2019"],
	["jor921w2n","50mg","Amoxicillin","18:10","1","11/29/2019"],
	["ut3923fj9","200mg","Aspirin","","0","12/22/2019"],
	["ut3923fj9","200mg","Aspirin","","0","12/22/2019"],
	["p435fjh32","100mg","Ibuprofen","","0","2/2/2020"],
	["p435fjh32","100mg","Ibuprofen","","0","2/2/2020"],
	["p435fjh32","100mg","Ibuprofen","","0","2/2/2020"]
];
*/

// TODO
// add med ID for deleting routes 

var dose1 = {
  "patID"         :  "aw3ks35d2",
  "dose"       :  "100mg",
  "medication" :  "Aspirin",
  "time"       :  "08:04",
  "fill"       :  true,
  "date"       :  "01/01/2020"
}

var dose2 = {
  "patID"         :  "aw3ks35d2",
  "dose"       :  "100mg",
  "medication" :  "Aspirin",
  "time"       :  "13:04",
  "fill"       :  true,
  "date"       :  "01/01/2020"
}

var dose3 = {
  "patID"         :  "aw3ks35d2",
  "dose"       :  "100mg",
  "medication" :  "Aspirin",
  "time"       :  "18:04",
  "fill"       :  true,
  "date"       :  "01/01/2020"
}

var dose4 = {
  "patID"         :  "bd9s3nm32",
  "dose"       :  "50mg",
  "medication" :  "Penicilin",
  "time"       :  "09:31",
  "fill"       :  false,
  "date"       :  "12/01/2019"
}

var dose5 = {
  "patID"         :  "me983hc29",
  "dose"       :  "200mg",
  "medication" :  "Lisinoprol",
  "time"       :  "11:15",
  "fill"       :  true,
  "date"       :  "12/12/2019"
}

var dose6 = {
  "patID"     :  "me983hc29",
  "dose"       :  "200mg",
  "medication" :  "Ibuprofen",
  "time"       :  "12:31",
  "fill"       :  true,
  "date"       :  "03/01/2020"
}

var dose7 = {
  "patID"     :  "jor921w2n",
  "dose"       :  "50mg",
  "medication" :  "Amoxicillin",
  "time"       :  "06:10",
  "fill"       :  true,
  "date"       :  "11/29/2019"
}

var dose8 = {
  "patID"     :  "jor921w2n",
  "dose"       :  "50mg",
  "medication" :  "Amoxicillin",
  "time"       :  "10:10",
  "fill"       :  true,
  "date"       :  "11/29/2019"
}

var dose9 = {
  "patID"     :  "jor921w2n",
  "dose"       :  "50mg",
  "medication" :  "Amoxicillin",
  "time"       :  "14:10",
  "fill"       :  true,
  "date"       :  "11/29/2019"
}

var dose10 = {
  "patID"     :  "jor921w2n",
  "dose"       :  "50mg",
  "medication" :  "Amoxicillin",
  "time"       :  "18:10",
  "fill"       :  true,
  "date"       :  "11/29/2019"
}

var dose11 = {
  "patID"     :  "ut3923fj9",
  "dose"       :  "200mg",
  "medication" :  "Aspirin",
  "time"       :  "",
  "fill"       :  false,
  "date"       :  "12/22/2019"
}

var dose12 = {
  "patID"         :  "ut3923fj9",
  "dose"       :  "200mg",
  "medication" :  "Aspirin",
  "time"       :  "",
  "fill"       :  false,
  "date"       :  "12/22/2019"
}

var dose13 = {
  "patID"     :  "p435fjh32",
  "dose"       :  "100mg",
  "medication" :  "Ibuprofen",
  "time"       :  "",
  "fill"       :  false,
  "date"       :  "2/2/2020"
}

var dose14 = {
  "patID"     :  "p435fjh32",
  "dose"       :  "100mg",
  "medication" :  "Ibuprofen",
  "time"       :  "",
  "fill"       :  false,
  "date"       :  "2/2/2020"
}

var dose15 = {
  "patID"     :  "p435fjh32",
  "dose"       :  "100mg",
  "medication" :  "Ibuprofen",
  "time"       :  "",
  "fill"       :  false,
  "date"       :  "2/2/2020"
}

medInfoJSON = [dose1, dose2, dose3, dose4, dose5, dose6, dose7, dose8, dose9, dose10, dose11, dose12, dose13, dose14, dose15];

// as before, JSON should match the arrays. Just JSON. 
/*
var patientSolo = {
  "firstName" : "Kelly",
  "lastName"  : "Bland",
  "id"        : "aw3ks35d2"
}
*/
//patInfo();
//medication.forEach(medListGen);

/* Initialization of patient info */

// break into handlebars - figure out how to seperate patient and meds in results
//function patInfo() {
//	var headN = document.getElementById("patName").innerHTML=patient[1]+", "+patient[2];
//	var headI = document.getElementById("patID").innerHTML=" (Patient ID: "+patient[0]+")"
//}

/* Initialization of medication list */
/*
function medListGen(item) {
	if (patient[0]==item[0]){
		var table = document.getElementById("medList");
		var row = table.insertRow(1);
		for(i=0;i<6;i++) {
			var cell = row.insertCell(i);
			if (i==4) {
				if (item[i]=="1") {
					var img = document.createElement('img');
    				img.src = "chkIcon.png";
    				img.className="tblIcons";
    				cell.appendChild(img);
				}
				else {
					cell.innerHTML="";
				}
			}
			else{
				cell.innerHTML=item[i];
			}
		}
	}
}
*/
//////////////////////////////////////////////////////////////////////////////////
// ROUTES SECTION - ROUTING CODE GOES HERE
/////////////////////////////////////////////////////////////////////////////////




app.get('/' , function(req, res) {
  res.render('home', {'home' : true});
  // static page, should be good?
});

app.get('/home' , function(req, res) {
  res.render('home', {'home' : true});
  // static page, should be good?
});

app.get('/calendar' , function(req, res) {
  res.render('calendar', {'calendar' : true});
  // static page, should be good?
});

app.get('/notifications' , function(req, res) {
  res.render('notifications', {'notifications' : true});
  // static page, should be good?
});

app.get('/patients' , function(req, res) {
  var context = {};
  // just displays some static JSON - all medications saved
  context.results = medInfoJSON;
  res.render('patient', context); 

});

app.get('/patient/:patientID' , function(req, res) {
  var context = {};
  //context.patient = patientSolo; // not needed?
  context.results = [];
  // context is a JSON object w/ a results array
  sortID = req.params.patientID;
  // iterates through all JSON for medication doses and pushes
  // those with matching patID into context.results
  //console.log("SortID = "+ sortID); 
  for (var i in medInfoJSON)
  {
    //console.log("comparing " + medInfoJSON[i].patId )
    if (medInfoJSON[i].patID == sortID)
    {
      context.results.push(medInfoJSON[i]);
      //console.log("pushing... " + i); 
    }
  } 
  // renders it 
  res.render('patient', context);
});

app.get('/records' , function(req, res) {
  // gets the patient list and then passes it to handlebars to build the page
  var context = {};
  context.results=recordsJSON;
  res.render('records', context); 

});

app.get('/settings' , function(req, res) {
  res.render('settings', {'settings' : true});
  // static page, should be good?
});

app.get('/about/' , function(req, res) {
  res.render('about', {'about' : true});
  // static page, should be good?
});

app.get('/acknowledge/' , function(req, res) {
  res.render('acknowledge', {'acknowledge' : true});
  // static page, should be good?
});

app.use(function(req,res){
  res.status(404);
  res.render('404');
  // static page, should be good?
});

app.use(function(err, req, res, next){
  console.error(err.stack);
  res.type('plain/text');
  res.status(500);
  res.render('500');
  // static page, should be good?
});

app.listen(app.get('port'), function(){
  console.log('Express started on http://localhost:' + app.get('port') + '; press Ctrl-C to terminate.');
});
