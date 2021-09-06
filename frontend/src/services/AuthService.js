import axios from 'axios';

export function getUser() {
    return localStorage.getItem("user");
}


export function signIn(signInUser) {
    let promise = axios.post('/api/signin', signInUser, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });

    promise.then(function (response) {
        console.log(response.data);
        localStorage.setItem("user", response.data);
    });

    return promise;
}
