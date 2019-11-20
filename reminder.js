/////////////////////////////////////////////////////////////////////
// Name: Group 11 / Henry Clay
// Date: 11/19
// Assignment: OS Project
// Comments: Modified from a personal site which was modified from
// 290 and 325 projects. Does Node things w/ js. uses json to write
// dynamic html. Javacriptin' on the net like it's '98. Loves it. 
/////////////////////////////////////////////////////////////////////

var express = require('express');



var app = express();
var handlebars = require('express-handlebars').create({defaultLayout:'main'});

app.engine('handlebars', handlebars.engine);
app.set('view engine', 'handlebars');
app.set('port', 44); // a port for serving the site

app.use(express.static('public')); // for static resources


app.get('/' , function(req, res) {
  res.render('home', {'home' : true});
});

app.get('/events/:eventId' , function(req, res) {
// this isn't gonna apply for the current project but it's a syntax example
// below vars are json examples - we can work from there! 
// handlebars will let you display html w/ these values from the 
// dispVer variable below 
  var ripples = { name     : "Ripples",
                  photoURL : '/images/ripplesPond.png',
                  guideURL : '"/ripplesGuide.pdf" download',
                  bomURL   : 'https://github.com/pichenettes/eurorack/blob/master/ripples/hardware_design/Ripples.xlsx',
                  buildURL : '/materials/ripples/',
                  buildDate: "March 22nd 2018",
                    current:false,
                      stock: 2,
                    events: true };

  var x0xheart = { name    : 'x0x Heart',
                  photoURL : '/images/x0x-heart-panel.jpg',
                  guideURL : 'http://wiki.openmusiclabs.com/wiki/EuroAdapter',
                  bomURL   : 'http://wiki.openmusiclabs.com/wiki/EuroAdapter',
                  buildURL : '/materials/x0xheart/',
                  buildDate: "TBA",
                  current:true,
                      stock: 0,
                  events: true };

  var benjolin = { name    : "Benjolin",
                  photoURL : '/images/kweiwenbenj.JPG',
                  guideURL : 'https://github.com/kweiwen/benjolin/wiki/1.-building-the-module',
                  bomURL   : 'https://github.com/kweiwen/benjolin/blob/master/bom.xlsx',
                  buildURL : '/materials/benjolin/',
                  buildDate: "October 10th 2017",
                    current:false,
                      stock: 1,
                  events: true };

  var build = req.params["eventId"];
  var dispVer = {};

  if(build == 'ripples') { dispVer = ripples; }
  else if(build == 'benjolin') { dispVer = benjolin; }
  else if(build == 'x0xheart') { dispVer = x0xheart; }

  res.render('event', dispVer);
});

app.get('/about/' , function(req, res) {
  res.render('about', {'about' : true});
});

app.get('/acknowledge/' , function(req, res) {
  res.render('acknowledge', {'acknowledge' : true});
});

app.use(function(req,res){
  res.status(404);
  res.render('404');
});

app.use(function(err, req, res, next){
  console.error(err.stack);
  res.type('plain/text');
  res.status(500);
  res.render('500');
});

app.listen(app.get('port'), function(){
  console.log('Express started on http://localhost:' + app.get('port') + '; press Ctrl-C to terminate.');
});
