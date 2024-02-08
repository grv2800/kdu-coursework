function tipCalculator(bill){
    var tip=0;
    var amount=0;
    if(bill < 50 ){
        tip=0.2*bill;
        amount=bill+tip;
    }
    else if(bill >=50 && bill <200){
        tip=0.15*bill;
        amount=bill+tip;
    }
    else{
        tip=0.10*bill;
        amount=bill+tip;
    }
    return [tip,amount];
}
function generator(){
    var bill=[140,45,280];
    var tipa=[];
    var amount=[];
    for(let i=0;i<bill.length;i++){
       var temp=tipCalculator(bill[i]);
       tipa.push(temp[0]);
        amount.push(temp[1]);

    }
    console.log(tipa);
    console.log(amount);
}
generator();
