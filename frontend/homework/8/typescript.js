var RecipeMania = /** @class */ (function () {
    function RecipeMania() {
        this.recipes = [];
    }
    RecipeMania.prototype.fetchRecipesFromAPI = function () {
        var _this = this;
        return fetch("https://dummyjson.com/recipes").then(function (response) { return response.json(); }).
            then(function (data) {
            console.log('data fetched from the api');
            if (data && data.recipes) {
                _this.recipes = data.recipes.map(function (recipe) { return ({
                    image: recipe.image,
                    name: recipe.name,
                    rating: recipe.rating,
                    cuisine: recipe.cuisine,
                    ingredients: recipe.ingredients,
                    difficulty: recipe.difficulty,
                    timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
                    calorieCount: recipe.caloriesPerServing
                }); });
                console.log('Recipes fetched from the API');
            }
            else {
                console.log('No recipes found in the response');
            }
        })
            .catch(function (error) {
            console.log('error fetching recipes:', error);
        });
    };
    RecipeMania.prototype.searchRecipes = function (query) {
        return fetch("https://dummyjson.com/recipes/search?q=".concat(query)).then(function (response) { return response.json(); }).
            then(function (data) {
            console.log('recipes fetched corresponding the given query', data);
        })
            .catch(function (error) {
            console.log("error in fetching ", error);
        });
    };
    RecipeMania.prototype.printAllRecipes = function () {
        this.recipes.map(function (recipe) {
            console.log("image:", recipe.image);
            console.log("name:", recipe.name);
            console.log("rating:", recipe.rating);
            console.log("cuisine:", recipe.cuisine);
            console.log("ingredients:", recipe.ingredients);
            console.log("difficulty:", recipe.difficulty);
            console.log("time taken:", recipe.timeTaken);
            console.log("calories:", recipe.calorieCount);
        });
    };
    return RecipeMania;
}());
var search = new RecipeMania();
search.fetchRecipesFromAPI().then(function () { return search.searchRecipes("Brazilian Caipirinha"); });
