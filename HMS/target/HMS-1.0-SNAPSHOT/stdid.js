function check(){
    var v=document.getElementById("stdid");
    var data;
    const em=new RegExp('[0-9]+');
    if(v.value==="" || !em.test(v.value))
    {
        document.getElementById("error1").textContent="invalid student id";
        document.getElementById("error1").style.visibility="visible";
        return;
    }
    else
    {
        data=v.value;
        document.getElementById("error1").style.visibility="hidden";
    }
    var httpx = new XMLHttpRequest();
    httpx.onreadystatechange = function(){
        if(httpx.status==200 && httpx.readyState==4){
            if(httpx.responseText.trim()=="0")
            {
                document.getElementById("error2").textContent="Student id doesn't exists";
                document.getElementById("error2").style.visibility="visible";
                return;
            }
            if(httpx.responseText.trim()=="Done!")
            {
                localStorage.setItem("stdid",data);
                window.location.href="Student_Details.html";
            }
            else
            {
                alert(httpx.responseText);
                return;
            }
        }
    };

    httpx.open("POST","stdid?data=" + data,true);
    httpx.send();
    
}




