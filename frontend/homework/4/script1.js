let add=document.getElementById("input1");
add.addEventListener("click",addTodo);

function addTodo(){
    let input=document.getElementById("todo-input");
    const data=input.value;
    
    if(data===""){
        return;
    }

    const item=document.createElement("li");
    item.textContent=data;
    
    const deleteButton=document.createElement("button");
    deleteButton.textContent="delete";
    deleteButton.setAttribute("id", "deleteButton");
    deleteButton.addEventListener("click",deleteFunction);
    function deleteFunction(){
        this.parentNode.remove();
    }
    item.append(deleteButton);

    let list=document.getElementById("todo-list");
    list.parentNode.appendChild(item);
}