var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
document.getElementsByTagName('head')[0].appendChild(script);

if (Notification.permission !== 'granted') {
    Notification.requestPermission();
}
 
var n = new Notification("Queue first");
var n = new Notification("Queue second");

var id = setInterval(function () {
    $.ajax("https://docs.google.com/spreadsheet/pub?key=0Auwt3KepmdoudE1iZFVFYmlQclcxbW85YzNZSTYyUHc&single=true&gid=0&range=b5&output=csv")
        .done(function(result) {
			
	    }
    });
}, 10000);
