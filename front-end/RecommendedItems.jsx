import React, { useEffect, useState } from 'react';
import { getRecommendedItems } from './api';

const RecommendedItems = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const userId = 3;  // current user Id, might need change
        const recommendedItems = await getRecommendedItems(userId);
        setItems(recommendedItems);
      } catch (error) {
        setError('Error finding the recommended items');
      } finally {
        setLoading(false);
      }
    };

    fetchItems();
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div>
      <h2>This items might be for you:</h2>
      <ul>
        {items.map((item) => (
          <li key={item.itemId}>
            <h3>{item.name}</h3>
            <p>{item.itemTitle}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RecommendedItems;
