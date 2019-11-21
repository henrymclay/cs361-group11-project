/* Initializing all of the variables for the records pages */
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

var patient = ["aw3ks35d2","Bland","Kelly"]

patInfo();
medication.forEach(medListGen);

/* Initialization of patient info */

function patInfo() {
	var headN = document.getElementById("patName").innerHTML=patient[1]+", "+patient[2];
	var headI = document.getElementById("patID").innerHTML=" (Patient ID: "+patient[0]+")"
}

/* Initialization of medication list */

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
