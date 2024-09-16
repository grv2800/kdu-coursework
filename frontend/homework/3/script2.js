function one(){
    let arr=["Sunday   ","   Monday  ", "  Tuesday","Wednesday  ","  Thursday   ","   Friday","Saturday    "];
    
    let ans=[];
    for(let i=0;i<arr.length;i++){
        ans.push(arr[i].trim().substring(0,3).toUpperCase());
    }
    console.log(ans);
}
// one();

function two(str){
    str=str.trim();
    
    str = str.replace(/a/g, '4');
    str = str.replace(/e/g, '3');
    str = str.replace(/i/g, '1');
    str = str.replace(/o/g, '0');
    str = str.replace(/s/g, '5');
    
    console.log(str);
}
two("programming is fun");