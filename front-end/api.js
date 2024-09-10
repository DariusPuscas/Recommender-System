import axios from 'axios';

// Set baze URL to the backend
const BASE_URL = 'http://localhost:8081'; 

//obtain the recommended items
export const getRecommendedItems = async (userId) => {
  try {
    const response = await axios.get(`${BASE_URL}/recommendations?userId=${userId}`);
    return response.data;
  } catch (error) {
    console.error("Couldn't get the items", error);
    throw error;
  }
};


