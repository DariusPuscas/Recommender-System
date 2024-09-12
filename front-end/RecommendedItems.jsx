import React, { useState, useEffect } from 'react';
import { Typography, Card, CardContent, Grid, CircularProgress, Alert, TextField, Button } from '@mui/material';
import { CSSTransition, TransitionGroup } from 'react-transition-group';
import './RecommendedItems.css';  // add CSS file for animations

const RecommendedItems = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");  // search state
  const [currentPage, setCurrentPage] = useState(1); // current page state
  const itemsPerPage = 5;  // Number of items/page

  // gets the items
  useEffect(() => {
    const fetchItems = async () => {
      try {
        const userId = 3;  // to do
        const response = await fetch(`/api/recommendations/${userId}`);  // Endpoint
        // headers: { Authorization: `Bearer ${token}` }
        if (!response.ok) {
          throw new Error('Failed to fetch');
        }
        const recommendedItems = await response.json();
        setItems(recommendedItems);
      } catch (error) {
        setError('Error finding the recommended items');
      } finally {
        setLoading(false);
      }
    };

    fetchItems();
  }, []);

  // Filter
  const filteredItems = items.filter((item) =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // search for first page items
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = filteredItems.slice(indexOfFirstItem, indexOfLastItem);

  
  if (loading) {
    return <CircularProgress />;
  }

  
  if (error) {
    return <Alert severity="error">{error}</Alert>;
  }

  // methods for changing the page
  const handleNextPage = () => {
    if (currentPage < Math.ceil(filteredItems.length / itemsPerPage)) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        These items might be for you:
      </Typography>

      {/* Seach field*/}
      <TextField
        label="Search items"
        variant="outlined"
        fullWidth
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}  // update searchterm
        margin="normal"
      />

      {/* print first page items */}
      <Grid container spacing={2}>
        <TransitionGroup component={null}>
          {currentItems.map((item) => (
            <CSSTransition key={item.itemId} timeout={300} classNames="fade">
              <Grid item xs={12} sm={6} md={4}>
                <Card>
                  <CardContent>
                    <Typography variant="h5" component="div">
                      {item.name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {item.itemTitle}
                    </Typography>
                  </CardContent>
                </Card>
              </Grid>
            </CSSTransition>
          ))}
        </TransitionGroup>
      </Grid>

      {/* Buttons */}
      <div style={{ marginTop: '20px', display: 'flex', justifyContent: 'space-between' }}>
        <Button variant="contained" onClick={handlePreviousPage} disabled={currentPage === 1}>
          Previous
        </Button>
        <Typography variant="subtitle1">
          Page {currentPage} of {Math.ceil(filteredItems.length / itemsPerPage)}
        </Typography>
        <Button variant="contained" onClick={handleNextPage} disabled={currentPage === Math.ceil(filteredItems.length / itemsPerPage)}>
          Next
        </Button>
      </div>
    </div>
  );
};

export default RecommendedItems;
