//js.js
var image;
var image_blue;
var x;
var y;

function place_figure(event){
	x = event.srcElement.classList.length;
	console.log(x);
	if(x < 2){
	event.srcElement.classList.add(image);
	document.getElementById("red").classList.remove("select_red");
	document.getElementById("blue").classList.remove("select_blue");
	}else{
		return;
	}
}

function setBlue(event) {
	document.getElementById("red").classList.remove("select_red");
	event.srcElement.classList.add("select_blue");
	image = "order";	
}

function setRed() {
	document.getElementById("blue").classList.remove("select_blue");
	event.srcElement.classList.add("select_red");
	image="chaos";
}

function restart_page(){
	location.reload();
}
