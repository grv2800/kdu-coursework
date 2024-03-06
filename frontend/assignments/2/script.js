document.addEventListener("DOMContentLoaded", function () {
  let tweetBox = document.querySelector(".tweet-box");
  let mobileTweetButton = document.querySelector(".floating-tweet-box-icon");

  let handleClick = function () {
    if (tweetBox.style.display === "none" || tweetBox.style.display === "") {
      tweetBox.style.display = "flex";
      mobileTweetButton.style.display = "none";
    } else {
      mobileTweetButton.style.display = "flex";
      tweetBox.style.display = "none";
    }
  };

  let mediaQuery = window.matchMedia(
    "(max-width: 414px) and (max-height: 896px)"
  );

  if (mediaQuery.matches) {
    mobileTweetButton.addEventListener("click", handleClick);
  } else {
    tweetBox.style.display = "flex";
  }

  mediaQuery.addEventListener("change", function () {
    if (mediaQuery.matches) {
      mobileTweetButton.addEventListener("click", handleClick);
    } else {
      tweetBox.style.display = "flex";
      mobileTweetButton.style.display = "none";

      mobileTweetButton.removeEventListener("click", handleClick);
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const tweetBtn = document.querySelector(".tweet-btn");
  const postInput = document.querySelector(".post-input");
  const postsContainer = document.querySelector(".posts");

  function createPost(postContent) {
    const newPost = document.createElement("div");
    newPost.classList.add("new-post");
    newPost.innerHTML = `
            <img src="profile-pic.png" alt="Profile Picture">
            <div class=" post">
                <div class="inner-post">
                    <h2>Nitesh Gupta</h2>
                    <h5>@nit_hck</h5>
                    <p>&middot; &nbsp;Just now</p>
                </div>
                <div class="tweet-content">
                    <div class="tweet-text">
                        <span>${postContent}</span>
                    </div>
                    <!-- Include other elements as needed -->
                </div>
                <div class="tweet-react">
                    <input type="image" src="comment-button.png" alt="comment" name="saveForm" class="comment-btn" id="show-comment-btn"/>
                    <div class="retweet">
                        <input type="image" src="retweet.png" alt="retweet" name="saveForm" class="retweet-btn" id="saveForm"/>
                        <span class="total-count" >0</span>
                    </div>
                    <div class="like-post">
                        <input type="image" src="like.png" alt="like" name="saveForm" class="like-btn" id="saveForm"/>
                        <span class="likes-count">0</span>
                    </div>
                    <img class="insights-btn" src="insights.png" alt="Insights">
                    <img class="bookmark-btn" src="bookmark-tweet.png" alt="Bookmark">
                    <img class="upload-btn" src="upload.png" alt="Upload">
                </div>
            </div>
        `;
    postsContainer.prepend(newPost);
  }

  tweetBtn.addEventListener("click", function () {
    const postContent = postInput.value.trim();

    if (postContent) {
      fetch("http://localhost:3010/api/posts", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ content: postContent }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          createPost(data.content);
          postInput.value = "";
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const messageButton = document.querySelector(".messages");
  const messagePage = document.querySelector(".message-page");

  messageButton.addEventListener("click", function () {
    if (messagePage.style.display === "none") {
      messagePage.style.display = "flex";
    } else {
      messagePage.style.display = "none";
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  let page = 1;
  const postsContainer = document.querySelector(".posts");
  function createPost(postContent) {
    const newPost = document.createElement("div");
    newPost.classList.add("new-post");
    newPost.innerHTML = `
                <img src="profile-pic.png" alt="Profile Picture">
                <div class=" post">
                    <div class="inner-post">
                        <h2>Nitesh Gupta</h2>
                        <h5>@nit_hck</h5>
                        <p>&middot; &nbsp;Just now</p>
                    </div>
                    <div class="tweet-content">
                        <div class="tweet-text">
                            <span>${postContent}</span>
                        </div>
                        <!-- Include other elements as needed -->
                    </div>
                    <div class="tweet-react">
                        <input type="image" src="comment-button.png" alt="comment" name="saveForm" class="comment-btn" id="show-comment-btn"/>
                        <div class="retweet">
                            <input type="image" src="retweet.png" alt="retweet" name="saveForm" class="retweet-btn" id="saveForm"/>
                            <span class="total-count" >0</span>
                        </div>
                        <div class="like-post">
                            <input type="image" src="like.png" alt="like" name="saveForm" class="like-btn" id="saveForm"/>
                            <span class="likes-count">0</span>
                        </div>
                        <img class="insights-btn" src="insights.png" alt="Insights">
                        <img class="bookmark-btn" src="bookmark-tweet.png" alt="Bookmark">
                        <img class="upload-btn" src="upload.png" alt="Upload">
                    </div>
                </div>
            `;
    postsContainer.append(newPost);
  }

  function fetchPosts() {
    fetch(`http://localhost:3010/api/posts?page=${page}&pageSize=5`)
      .then((response) => response.json())
      .then((posts) => {
        posts.forEach((post) => {
          createPost(post.content);
        });
      });
  }

  fetchPosts();

  window.addEventListener("scroll", function () {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
      page++;
      fetchPosts();
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const incrementCount = document.querySelector(".like-btn");

  const totalCount = document.querySelector(".likes-count");

  let count = 0;

  totalCount.innerHTML = count;

  const handleIncrement = () => {
    count++;
    totalCount.innerHTML = count;
  };

  const handleDecrement = () => {
    count--;
    totalCount.innerHTML = count;
  };

  let flag = true;
  incrementCount.addEventListener("click", function () {
    if (flag) {
      handleIncrement();
    } else {
      handleDecrement();
    }
    flag = !flag;
  });
});
document.addEventListener("DOMContentLoaded", function () {
  const incrementCount1 = document.querySelector(".retweet-btn");

  const totalCount1 = document.querySelector(".total-count");

  let count1 = 0;

  totalCount1.innerHTML = count1;

  const handleIncrement = () => {
    count1++;
    totalCount1.innerHTML = count1;
  };

  const handleDecrement = () => {
    count1--;
    totalCount1.innerHTML = count1;
  };

  let flag = true;
  incrementCount1.addEventListener("click", function () {
    if (flag) {
      handleIncrement();
    } else {
      handleDecrement();
    }
    flag = !flag;
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const showCommentBtn = document.getElementById("show-comment-btn");

  const promptForComment = () => {
    const commentText = prompt("Please enter your comment:");

    if (commentText !== null && commentText.trim() !== "") {
      const newComment = document.createElement("li");
      newComment.textContent = commentText;

      const commentList = document.getElementById("comment-list");
      commentList.appendChild(newComment);
    }
  };

  showCommentBtn.addEventListener("click", promptForComment);
});
