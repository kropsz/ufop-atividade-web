// repository/doacoes/getAllDoacoes.js

const getAllDoacoes = async () => {
  try {
      const response = await fetch('http://localhost:5000/doacoes');
  
      if (!response.ok) {
          throw new Error('Failed to fetch doacoes');
      }
      const data = await response.json();
      return data;
  } catch (error) {
      console.error('Error fetching doacoes:', error);
      throw error;
  }
};

export default getAllDoacoes;
