function keyValue(json){
    let keys=[];
    let valueList=[];
    function temp(json){
       for (let key in json) {
          if(typeof json[key]=="object" && json[key] != null){
            keys.push(key);
             temp(json[key]);
         }
          else{
            keys.push(key);
            valueList.push(json[key]);
         }
        }  
       } 
    temp(json);
    return [keys,valueList];
}

console.log(keyValue({

    firstName: "Leo",
  
    lastName: "Messi",
  
    address: {
  
      country: "Spain",
  
      city: "Barcelona",
  
    },
  
    careerInfo: {
  
      fcBarcelona: {
  
        appearances: 780,
  
        goals: {
  
          premierLeagueGoals: 590,
  
          championsLeagueGoals: 50,
  
        },
  
      },
    }
  }));