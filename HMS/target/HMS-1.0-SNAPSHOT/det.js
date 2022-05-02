window.onload = function(){

    var data=localStorage.getItem("stdid");
    var isw=localStorage.getItem("isw");
     localStorage.clear();
     localStorage.setItem("isw",isw);
  
  
    var httpx = new XMLHttpRequest();
    httpx.onreadystatechange = function(){
        if(httpx.status===200 && httpx.readyState===4){
//            alert(httpx.responseText);
            var sss=httpx.responseText.trim();
            var ss=sss.split(" ");
            document.getElementById("name").value=ss[0];
            document.getElementById("stdid").value=ss[1];
            document.getElementById("cno").value=ss[2];
            document.getElementById("address").value=ss[3];
            document.getElementById("acc_no").value=ss[4];
            document.getElementById("mess_no").value=ss[5];
            document.getElementById("dues").value=ss[6];
            document.getElementById("is_room").value=ss[7];
            if(ss[7]=="Yes")
            {
                document.getElementById("rr").style.visibility="visible";
                document.getElementById("room").value=ss[8];
            }
        }
    };

    httpx.open("POST","get_det?data=" + data,true);
    httpx.send();
};


function fun() {
    v=document.getElementById("is_room");
    if(v.value==="Yes")
    {
        document.getElementById("rr").style.visibility="visible";
    }
    else
    {
        document.getElementById("rr").style.visibility="hidden";
    }
}


function check(){
    var data;
    var v=document.getElementById("name");
    const em2=new RegExp('[a-zA-z]+');
    if(v.value==="" || !em2.test(v.value))
    {
        document.getElementById("error1").textContent="invalid name";
        document.getElementById("error1").style.visibility="visible";
        return;
    }
    else
    {
        data=v.value;
        document.getElementById("error1").style.visibility="hidden";
    }
    


    v=document.getElementById("stdid");
    const em=new RegExp('[0-9]+');
    if(v.value==="" || !em.test(v.value))
    {
        document.getElementById("error2").textContent="invalid student id";
        document.getElementById("error2").style.visibility="visible";
        return;
    }
    else
    {
        data=data + " " + v.value;
        document.getElementById("error2").style.visibility="hidden";
    }
    
    
    
    v=document.getElementById("cno");
    const em1=new RegExp('[0-9]{10}');
    if(v.value==="" || !em1.test(v.value))
    {
        document.getElementById("error3").textContent="invalid contact number";
        document.getElementById("error3").style.visibility="visible";
        return;
    }
    else
    {
        data=data + " " + v.value;
        document.getElementById("error3").style.visibility="hidden";
    }
    
    
    
    v=document.getElementById("address");
    if(v.value==="")
    {
        document.getElementById("error4").textContent="invalid address";
        document.getElementById("error4").style.visibility="visible";
        return;
    }
    else
    {
        data=v.data=data + " " + v.value;;
        document.getElementById("error4").style.visibility="hidden";
    }
    
    
    
    v=document.getElementById("acc_no");
    if(v.value==="" || !em.test(v.value))
    {
        document.getElementById("error5").textContent="invalid account no";
        document.getElementById("error5").style.visibility="visible";
        return;
    }
    else
    {
        data=data + " " + v.value;
        document.getElementById("error5").style.visibility="hidden";
    }
    
    
    v=document.getElementById("mess_no");
    if(v.value==="" || !em.test(v.value))
    {
        document.getElementById("error6").textContent="invalid mess no";
        document.getElementById("error6").style.visibility="visible";
        return;
    }
    else
    {
        data=data + " " + v.value;
        document.getElementById("error6").style.visibility="hidden";
    }
    
    v=document.getElementById("dues");
    if(v.value==="Yes" || v.value==="No")
    {
        data=data + " " + v.value;
        document.getElementById("error7").style.visibility="hidden";
    }
    else
    {
        document.getElementById("error7").textContent="Please select an option";
        document.getElementById("error7").style.visibility="visible";
        return;
    }
    
    v=document.getElementById("is_room");
    if(v.value==="Yes" || v.value==="No")
    {
        data=data + " " + v.value;
        document.getElementById("error8").style.visibility="hidden";
    }
    else
    {
        document.getElementById("error8").textContent="Please select an option";
        document.getElementById("error8").style.visibility="visible";
        return;
    }
    
    if(v.value==="Yes")
    {
        v=document.getElementById("room");
        if(v.value==="" || !em.test(v.value))
        {
            document.getElementById("error9").textContent="invalid room no";
            document.getElementById("error9").style.visibility="visible";
            return;
        }
        else
        {
            data=data + " " + v.value;
            document.getElementById("error9").style.visibility="hidden";
        }
    }
    

    var httpx = new XMLHttpRequest();
    httpx.onreadystatechange = function(){
        if(httpx.status==200 && httpx.readyState==4){
            if(httpx.responseText.trim()=="Done!")
            {
                alert("Updated Successfully!");
                window.location.href="main_screen.html";
            }
            else
            {
                alert(httpx.responseText);
                return;
            }
        }
    };

    httpx.open("POST","up_det?data=" + data,true);
    httpx.send();
    
}

