function store(){
    let shoe1={type:"sneaker",color:"red",size:10,price:1000};
    let shoe2={type:"sport",color:"blue",size:9,price:1500};
    let shoes=[shoe1,shoe2];

    let shirt1={type:"plain",color:"black",size:42,price:150};
    let shirt2={type:"stripe",color:"blue",size:40,price:100};
    let shirt3={type:"cheque",color:"peach",size:42,price:250};
    let shirts=[shirt1,shirt2,shirt3];

    let warehouse=[shoe1,shoe2,shirt1,shirt2,shirt3];
    return warehouse;
}
console.log(store());

function totalWorth(){
    let warehouse=store();
    let amount=0;
    for(let i=0;i<warehouse.length;i++){
        amount+=warehouse[i].price;
    }
    return amount;
}
console.log(totalWorth());

function sortFunction(){
    let warehouse=store();
    warehouse=warehouse.sort((a,b)=>a.price - b.price);
    return warehouse;
}
console.log(sortFunction());

function displayItems(){
    let warehouse=store();
    let items=[];
    for(let i=0;i<warehouse.length;i++){
        if(warehouse[i].color=="blue"){
            items.push(warehouse[i]);
        }
    }
    return items;
}
console.log(displayItems());