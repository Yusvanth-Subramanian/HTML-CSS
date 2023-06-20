let submitButton=document.getElementsByClassName("submit");
let adminUserName="Admin";
let adminPassword="admin@123";
let usernameInput=null;
let passwordInput=null;


let newEmployeeName=null;
let newEmployeeEmail=null;
let newEmployeePhone=null;

let table=[];
let grid = document.getElementsByClassName("table")[0];

let index=0;

let employee={
    userName:null,
    email:null,
    phone:null
}

function changeUsername(name){
    usernameInput=name;
}
function changePassword(pass){
    passwordInput=pass;
}

//submitButton[0].addEventListener("click",showSectionTwo());
function showSectionTwo(){
    console.log(adminPassword+" "+usernameInput+" "+adminUserName+" "+passwordInput);
    if(adminPassword==passwordInput&&adminUserName==usernameInput){
        document.getElementById("section-1").style.display="none";
        document.getElementsByClassName("addEmployee")[0].style.display="block";
        document.getElementById("section-3").style.display="block";
    }
}

function showAddSection(){
    document.getElementById("section-2").style.display="block";
}

function changeNewEmployeeName(name){
    newEmployeeName=name;
}

function changeNewEmployeeEmail(email){
    newEmployeeEmail=email;
}

function changeNewEmployeePhone(phone){
    newEmployeePhone=phone
}
function addEmploye(){
    table[index]={newEmployeeName,newEmployeeEmail,newEmployeePhone};
    console.log(table[index]);
    index++;

    for( var i in table){
        let tr=document.createElement("tr");
        td1=document.createElement("td");
        td2=document.createElement("td");
        td3=document.createElement("td");
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        

        tr.cells[0].appendChild( document.createTextNode(newEmployeeName) );
        tr.cells[1].appendChild( document.createTextNode(newEmployeeEmail) );
        tr.cells[2].appendChild( document.createTextNode(newEmployeePhone) );
        tr.cells[0].style.alignItems="center";
        tr.cells[1].style.alignItems="center";
        tr.cells[2].style.alignItems="center";
        var td = document.createElement("td");
        var btn = document.createElement("button");
        btn.style.backgroundColor="red";
        btn.style.borderRadius="5px";
        btn.style.padding="10px";
        btn.textContent = "Delete";
        td.appendChild(btn);
        tr.appendChild(td);
        grid.appendChild(tr);
        btn.addEventListener("click", function () {
            row = this.parentNode.parentNode;
            row.parentNode.removeChild(row);
          });
        console.log(td1.innerHTML);
        tr.addEventListener("click",function(){
            console.log(this.childNodes);
            document.getElementsByClassName("addEmployee")[0].style.display="none";
            document.getElementById("section-2").style.display="none";
            document.getElementById("section-3").style.display="none";
            document.getElementById("section-4").style.display="block";
            document.getElementById("giveName").innerHTML="<h6>"+this.childNodes[0].innerHTML+"</h6>";
            document.getElementById("giveEmail").innerHTML="<h6>"+this.childNodes[1].innerHTML+"</h6>";
            document.getElementById("givePhone").innerHTML="<h6>"+this.childNodes[2].innerHTML+"</h6>";
        });
          break;
    }
    document.getElementById("section-2").style.display="none";
}

function gotoSection3(){
    document.getElementsByClassName("addEmployee")[0].style.display="block";
    document.getElementById("section-2").style.display="block";
    document.getElementById("section-3").style.display="block";
    document.getElementById("section-4").style.display="none";
}