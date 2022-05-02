window.onload = function(){
    var data=localStorage.getItem("isw");
    if(data.trim()==="true")
    {
        document.getElementById("fff").style.display="inline";
    }
};

function del(){
    localStorage.clear();
    window.location.href="index.html";
}

