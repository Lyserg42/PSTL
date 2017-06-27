function listeInterval(x,y){
	if(x==y){
		return cons(y,nil);
	}
	else {
		return cons(x,listeInterval(x+1,y));	    
	}
};
var z = listeInterval(1,5);
z;



