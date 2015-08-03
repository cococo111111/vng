$(document).ready(function(){
        alert('a');
	changeSelectedIndex();

});

function indexAction(page){

    //alert(page);
        if(page<0)
            return;
        $('#page').val(page);
        $('.action-form')[0].submit();
       
}


function changeSelectedIndex(){

        var appId = $("select[name='appId'] option:selected").val();
       // alert(appId);
	$.getJSON("getid?callback=?", {"appId": appId}, function(data) {
           document.getElementById("actId").innerHTML=data.content;
	});
}