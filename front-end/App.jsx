import React, { useState } from 'react';
import './App.css';
import RecommendedItems from './RecommendedItems';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { CssBaseline, Button, TextField } from '@mui/material';

const theme = createTheme({
  palette: {
    mode: 'dark',
  },
});

function App() {
  const [email, setEmail] = useState(''); //  email
  const [token, setToken] = useState(''); //  JWT
  const [recommendedItems, setRecommendedItems] = useState([]); // items
  const [isAuthenticated, setIsAuthenticated] = useState(false); // see if the user connected

  // submit email
  const handleEmailSubmit = async (e) => {
    e.preventDefault(); //prevent unwanted refresh

    // sends response to backend
    const response = await fetch('/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email }), //send mail as JSON object
    });

    if (response.ok) {
      const data = await response.json(); 
      setToken(data.token); //  JWT
      setRecommendedItems(data.recommendedItems); //  set 
      setIsAuthenticated(true); 
    } else {
     //throws errors
      alert('Invalid email or authentication error.');
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <div className="App">
        <header className="App-header">
          <h1>The best is yet to come!</h1>
        </header>

        {/* if user not logged display log in page, else show recommended items */}
        {!isAuthenticated ? (
          <div>
            <h2>Enter your email to see recommended items:</h2>
            <form onSubmit={handleEmailSubmit}>
              <TextField
                label="Email"
                variant="outlined"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
              <br />
              <Button type="submit" variant="contained" color="primary" style={{ marginTop: '10px' }}>
                Submit
              </Button>
            </form>
          </div>
        ) : (
          // print items
          <div>
            <h2>These items might be for you:</h2>
            <RecommendedItems items={recommendedItems} />
          </div>
        )}
      </div>
    </ThemeProvider>
  );
}

export default App;
