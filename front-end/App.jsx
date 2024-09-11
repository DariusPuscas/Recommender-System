import React from 'react';
import './App.css';
import RecommendedItems from './RecommendedItems';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { CssBaseline } from '@mui/material';

const theme = createTheme({
  palette: {
    mode: 'dark',
  },
});
function App() {
  return (

    <div className="App">
      <header className="App-header">
        <h1>Welcome!</h1>
        <p></p>
      </header>

      {/* Recommended Items */}
      <RecommendedItems />
    </div>

  );
}

export default App;