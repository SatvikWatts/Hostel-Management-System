function check(){
    var data;
    var v=document.getElementById("uname");
    const em2=new RegExp('[a-zA-z]+');
    if(v.value==="" || !em2.test(v.value))
    {
        document.getElementById("error1").textContent="invalid username";
        document.getElementById("error1").style.visibility="visible";
        return;
    }
    else
    {
        data=v.value;
        document.getElementById("error1").style.visibility="hidden";
    }
    


    v=document.getElementById("pass");
    if(v.value==="")
    {
        document.getElementById("error2").textContent="invalid password";
        document.getElementById("error2").style.visibility="visible";
        return;
    }
    else
    {
        data=data + " " + v.value;
        document.getElementById("error2").style.visibility="hidden";
    }
    

    var httpx = new XMLHttpRequest();
    httpx.onreadystatechange = function(){
        if(httpx.status==200 && httpx.readyState==4){
            if(httpx.responseText.trim()=="0")
            {
                document.getElementById("error3").textContent="username already exists";
                document.getElementById("error3").style.visibility="visible";
            }
            if(httpx.responseText.trim()=="Done!")
            {
                alert("User Added Successfully!");
                window.location.href="main_screen.html";
            }
            else
            {
                alert(httpx.responseText);
                return;
            }
        }
    };

    httpx.open("POST","add_user?data=" + data,true);
    httpx.send();
    
}