const getAllLocaisColeta = async () => {
  try {
      const response = await fetch('http://localhost:5000/locais');
      if (!response.ok) {
          throw new Error('Failed to fetch locais de coleta');
      }
      const data = await response.json();
      return data;
  } catch (error) {
      console.error('Error fetching locais de coleta:', error);
      throw error; 
  }
};

export default getAllLocaisColeta;
