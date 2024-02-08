function parseJson(jsonString) {
        let json = JSON.parse(jsonString);
    
        for (let key in json) {
            if (typeof json[key] === 'string') { 
                json[key] = json[key].toUpperCase();
            }
        }  
        console.log(json);
        
        delete json.email;

        return JSON.stringify(json);
}

let jsonString=parseJson('{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}');
console.log(jsonString);

