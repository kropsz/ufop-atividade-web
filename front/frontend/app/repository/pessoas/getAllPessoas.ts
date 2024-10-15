
const getAllPessoas = async () => {
  try {
      const response = await fetch('http://localhost:5000/pessoas');
      
      if (!response.ok) {
          throw new Error('Failed to fetch pessoas');
      }
      const data = await response.json();
      return data;
  } catch (error) {
      console.error('Error fetching pessoas:', error);
      throw error; 
  }
};

export default getAllPessoas;
