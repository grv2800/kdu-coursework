document.addEventListener("DOMContentLoaded", function() {
    var tweetBox = document.querySelector(".tweet-box");
    var mobileTweetButton = document.querySelector(".floating-tweet-box-icon");
    
    var handleClick = function() {
        if (tweetBox.style.display === "none" || tweetBox.style.display === "") {
            tweetBox.style.display = "flex";
            mobileTweetButton.style.display = "none";
        } else {
            mobileTweetButton.style.display = "flex";
            tweetBox.style.display = "none";
        }
    };

    // Check if the viewport matches the specified condition
    var mediaQuery = window.matchMedia("(max-width: 414px) and (max-height: 896px)");

    // Add click event listener for the mobileTweetButton if viewport matches the condition
    if (mediaQuery.matches) {
        mobileTweetButton.addEventListener("click", handleClick);
    } else {
        tweetBox.style.display = "flex"; // Show tweet box by default in other viewport sizes
    }

    // Add event listener for changes in viewport size
    mediaQuery.addEventListener("change", function() {
        if (mediaQuery.matches) {
            // Add click event listener for the mobileTweetButton
            mobileTweetButton.addEventListener("click", handleClick);
        } else {
            // If the condition is not met, hide the tweet box and show the mobileTweetButton
            tweetBox.style.display = "flex"; // Show tweet box by default in other viewport sizes
            mobileTweetButton.style.display = "none";

            // Remove the event listener if the condition is not met
            mobileTweetButton.removeEventListener("click", handleClick);
        }
    });
});



document.addEventListener("DOMContentLoaded", function() {
// Select increment and decrement buttons
const incrementCount = document.querySelector(".like-btn");
// Select total count
const totalCount = document.querySelector(".likes-count");

// Variable to track count
var count = 0;

// Display initial count value
totalCount.innerHTML = count;

// Function to increment count
const handleIncrement = () => {
  count++;
  totalCount.innerHTML = count;
};

// Function to decrement count
const handleDecrement = () => {
  count--;
  totalCount.innerHTML = count;
};

let flag = true;
incrementCount.addEventListener("click", function() {

    if (flag) {
       handleIncrement();
    } 
    else{
     handleDecrement();
    }
  flag = !flag;
});
});
document.addEventListener("DOMContentLoaded", function() {
    // Select increment and decrement buttons
    const incrementCount1 = document.querySelector(".retweet-btn");
    // Select total count
    const totalCount1 = document.querySelector(".total-count");
    
    // Variable to track count
    var count1 = 0;
    
    // Display initial count value
    totalCount1.innerHTML = count1;
    
    // Function to increment count
    const handleIncrement = () => {
      count1++;
      totalCount1.innerHTML = count1;
    };
    
    // Function to decrement count
    const handleDecrement = () => {
      count1--;
      totalCount1.innerHTML = count1;
    };
    
    let flag = true;
    incrementCount1.addEventListener("click", function() {
    
        if (flag) {
           handleIncrement();
        } 
        else{
         handleDecrement();
        }
      flag = !flag;
    });
});
    
document.addEventListener("DOMContentLoaded", function() {
    // Select the comment button
    const showCommentBtn = document.getElementById("show-comment-btn");

    // Function to prompt the user for a comment
    const promptForComment = () => {
        // Use prompt to get the user's comment
        const commentText = prompt("Please enter your comment:");

        // Check if the user entered a comment
        if (commentText !== null && commentText.trim() !== "") {
            // Create a new list item for the comment
            const newComment = document.createElement("li");
            newComment.textContent = commentText;

            // Select the comment list
            const commentList = document.getElementById("comment-list");

            // Append the new comment to the comment list
            commentList.appendChild(newComment);
        }
    };

    // Add click event to the comment button
    showCommentBtn.addEventListener("click", promptForComment);
});
