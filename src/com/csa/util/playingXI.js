<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
/*
*@sanjaya
*
*extract playingXI from espncricinfo page
*
*/


//1st innings
var batting1=document.getElementsByClassName("batting-table innings")[0];

var len=batting1.children[0].children.length;
var battedInfo=batting1.children[0].children;
var team1= [];
var j=0;

//batted 1st inning
for(i=0;i<len;i++){
	

	if(battedInfo[i].hasAttributes()==false){
		team1[j]=battedInfo[i].children[1].children[0].innerHTML;
		j++;
	}
	
}

var batting2=document.getElementsByClassName("batting-table innings")[1];

var len=batting2.children[0].children.length;
var battedInfo=batting2.children[0].children;
var team2= [];
var k=0;

//batted 2nd inning
for(i=0;i<len;i++){
	

	if(battedInfo[i].hasAttributes()==false){
		team2[k]=battedInfo[i].children[1].children[0].innerHTML;
		k++;
	}
	
}



//1st inning tobat
if(document.getElementsByClassName("more-match-stats")[0].children.length>1){

var dnb= document.getElementsByClassName("more-match-stats")[0].children[0].children[0];
var len= dnb.children.length;

for(i=1;i<len;i++){
	
	team1[j]=dnb.children[i].children[0].innerHTML;
	j++;
}	
}


//2nd inning tobat

if(document.getElementsByClassName("more-match-stats")[1].children.length>1){

var dnb= document.getElementsByClassName("more-match-stats")[1].children[0].children[0];
var len= dnb.children.length;

for(i=1;i<len;i++){
	
	team2[k]=dnb.children[i].children[0].innerHTML;
	k++;
}	
}



//var object ={'matchid':Number(document.location.href.split("/").pop().split(".")[0]),'team1':t1,'team2':t2};

var id= document.location.href.split("/").pop().split(".")[0];
var url= "http://b16be682.ngrok.io";

var postData= {'id':id,'team1':team1,'team2':team2};
$.post(url+'/save.php',postData,function(data){
	
	alert("Sent");
});
