window.onload=()=>{
    document.getElementById("formregister").onsubmit=(e)=>{
        e.preventDefault();
        if(document.getElementById("pass").value===document.getElementById("repass").value){
            document.getElementById("formregister").submit();
        }else{
            alert("Password must be equals");
        }
    }
}