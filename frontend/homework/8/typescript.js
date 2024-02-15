var SearchApp = /** @class */ (function () {
    function SearchApp() {
        this.recipes = [];
    }
    SearchApp.prototype.fetchRecipesFromAPI = function () {
        var _this = this;
        return fetch("https://dummyjson.com/recipes").then(function (response) { return response.json(); }).
            then(function (data) {
            console.log('data fetched from the api');
            if (data != null && data.recipes != null) {
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
                console.log('Recipes fetched from the API:', _this.recipes);
            }
            else {
                console.log('No recipes found in the response');
            }
        })
            .catch(function (error) {
            console.log('error fetching recipes:', error);
        });
    };
    SearchApp.prototype.searchRecipes = function (query) {
        return fetch("https://dummyjson.com/recipes/search?q=".concat(query)).then(function (response) { return response.json(); }).
            then(function (data) {
            console.log('recipes fetched corresponding the given query', data);
        })
            .catch(function (error) {
            console.log("error in fetching ", error);
        });
    };
    SearchApp.prototype.printAllRecipes = function () {
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
    return SearchApp;
}());
var search = new SearchApp();
search.fetchRecipesFromAPI().then(function () { return search.searchRecipes("Brazilian Caipirinha"); });
