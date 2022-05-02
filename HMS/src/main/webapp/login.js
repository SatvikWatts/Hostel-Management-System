function check(){
    var data;
    var v=document.getElementById("username");
    document.getElementById("t_error").style.visibility="hidden";
    document.getElementById("u_error").style.visibility="hidden";
    document.getElementById("p_error").style.visibility="hidden";
    if(v.value==="")
    {
        document.getElementById("u_error").textContent="please enter a valid username";
        document.getElementById("u_error").style.visibility="visible";
        return;
    }
    else
    {
        data=v.value;
        document.getElementById("u_error").style.visibility="hidden";
    }
    
    v=document.getElementById("pass");
    if(v.value==="")
    {
        document.getElementById("p_error").textContent="please enter a valid password";
        document.getElementById("p_error").style.visibility="visible";
        return;
    }
    else
    {
        data=data + " " + v.value;
        document.getElementById("p_error").style.visibility="hidden";
    }
    
    var httpx = new XMLHttpRequest();
    httpx.onreadystatechange = function(){
        if(httpx.status===200 && httpx.readyState===4){
            if(httpx.responseText.trim()==="0")
            {
                document.getElementById("t_error").textContent="invalid email id or password";
                document.getElementById("t_error").style.visibility="visible";
            }
            else
            {
                var sss=httpx.responseText.trim();
//                var ss=sss.split(" ");
                localStorage.setItem("isw",sss);
//                localStorage.setItem("visid",ss[0]);
                window.location.href="main_screen.html";
            }
        }
    };

    httpx.open("POST","login?data=" + data,true);
    httpx.send();
    
};


