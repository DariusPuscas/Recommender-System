import React, { useState } from 'react';

const LoginPage = ({ setToken, setItems }) => {
    const [email, setEmail] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                email: email
            }),
        });

        if (response.ok) {
            const data = await response.json();
            setToken(data.token); 
            setItems(data.recommendedItems); 
        } else {
            alert('Email invalid');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />
            <button type="submit">Submit</button>
        </form>
    );
};

export default LoginPage;