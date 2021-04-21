function getTable(description,tableId)
{
    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange=function ()
    {
        if(this.readyState==4 && this.status==200)
        {
            document.getElementById(tableId).innerHTML= this.responseText;
        }
    };
    xhttp.open("GET","tasks?description="+document.getElementById(description).value, true);
    xhttp.send();
}
function addToTable(description, startDate,endDate,startTime,endTime,person,tableId)
{
    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange=function ()
    {
        if(this.readyState==4 && this.status==200)
        {
            document.getElementById(tableId).innerHTML= this.responseText;
        }
    };
    description=document.getElementById(description).value;
    startDate=document.getElementById(startDate).value;
    endDate=document.getElementById(endDate).value
    startTime=document.getElementById(startTime).value
    endTime= document.getElementById(endTime).value;
    person=document.getElementById(person).value;

    xhttp.open("GET","addTasks?description="+description+"&startDate="+startDate+"&endDate="+endDate+"&startTime="+startTime+"&endTime="+endTime+"&person="+person, true);
    xhttp.send();
}
function deleteTask(deleteDescription,tableId)
{
    var xhttp= new XMLHttpRequest();
    xhttp.onreadystatechange=function ()
    {
        if(this.readyState==4 && this.status==200)
        {
            document.getElementById(tableId).innerHTML= this.responseText;
        }
    };
    xhttp.open("GET","deleteTasks?deleteDescription="+document.getElementById(deleteDescription).value, true);
    xhttp.send();
}