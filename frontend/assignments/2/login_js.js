document.addEventListener('DOMContentLoaded', function () {
    
    const loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        fetch("http://localhost:3010/api/user/login",{
            method:'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify({username:username,password:password})
        }).then(response=>{
            if(response.status==200){
                return response.json();
            }
            else{
                throw new Error('Invalid credentials');
            }
        })
        .then(data => {
            sessionStorage.setItem('userData', JSON.stringify(data));
            window.location.href = 'index.html';
        })
        .catch(error => {
            alert(error.message);
        });
    });
});

