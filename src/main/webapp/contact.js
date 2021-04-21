function getTable(telephone,tableId)
{
    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange=function ()
    {
        if(this.readyState==4 && this.status==200)
        {
            document.getElementById(tableId).innerHTML= this.responseText;
        }
    };
    xhttp.open("GET","contacts?telephone="+document.getElementById(telephone).value, true);
    xhttp.send();
}
function addToTable(telephone, address,email,person, tableId)
{
    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange=function ()
    {
        if(this.readyState==4 && this.status==200)
        {
            document.getElementById(tableId).innerHTML= this.responseText;
        }
    };
    telephone=document.getElementById(telephone).value;
    address=document.getElementById(address).value;
    email=document.getElementById(email).value;
    person=document.getElementById(person).value;

    xhttp.open("GET","addContacts?telephone="+telephone+"&address="+address+"&email="+email+"&person="+person, true);
    xhttp.send();
}
function deleteContact(deleteTelephone,tableId)
{
    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange=function ()
    {
        if(this.readyState==4 && this.status==200)
        {
            document.getElementById(tableId).innerHTML= this.responseText;
        }
    };
    xhttp.open("GET","deleteContacts?deleteTelephone="+document.getElementById(deleteTelephone).value, true);
    xhttp.send();
}