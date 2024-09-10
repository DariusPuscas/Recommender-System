import React from 'react';
import './App.css';
import RecommendedItems from './RecommendedItems';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome!</h1>
        <p>Importing data...</p>
      </header>

      {/* Recommended Items */}
      <RecommendedItems />
    </div>
  );
}

export default App;