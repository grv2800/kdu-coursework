interface Recipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    timeTaken: number;
    calorieCount: number;
}
class RecipeMania{

    private recipes:Recipe[];
    
    constructor(){
        this.recipes=[];
    }

    fetchRecipesFromAPI():Promise<void>{
        return fetch(`https://dummyjson.com/recipes`).then(response=>response.json()).
        then((data)=>{
            console.log('data fetched from the api');
            if (data && data.recipes) {
                this.recipes = data.recipes.map((recipe: any) => ({
                    image: recipe.image,
                    name: recipe.name,
                    rating: recipe.rating,
                    cuisine: recipe.cuisine,
                    ingredients: recipe.ingredients,
                    difficulty: recipe.difficulty,
                    timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
                    calorieCount: recipe.caloriesPerServing
                }));
                console.log('Recipes fetched from the API');
            } else {
                console.log('No recipes found in the response');
            }
            
        })
        .catch(error=>{
            console.log('error fetching recipes:',error);
        });
    }
    searchRecipes(query:string):Promise<void>{
        return fetch(`https://dummyjson.com/recipes/search?q=${query}`).then(response=>response.json()).
        then((data:Recipe[])=>{
            console.log('recipes fetched corresponding the given query',data);
        })
        .catch(error=>{
            console.log("error in fetching ",error);
        })
    }

    printAllRecipes():void{
        this.recipes.map((recipe)=>{
            console.log("image:",recipe.image);
            console.log("name:",recipe.name);
            console.log("rating:",recipe.rating);
            console.log("cuisine:",recipe.cuisine);
            console.log("ingredients:",recipe.ingredients);
            console.log("difficulty:",recipe.difficulty);
            console.log("time taken:",recipe.timeTaken);
            console.log("calories:",recipe.calorieCount);  
        });
    }
}

const search=new RecipeMania();
search.fetchRecipesFromAPI().then(()=>search.searchRecipes("Brazilian Caipirinha"));