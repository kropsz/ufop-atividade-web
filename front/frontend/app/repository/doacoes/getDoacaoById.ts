const getDoacaoById = async (id: string) => {
  try {
      const response = await fetch(`http://localhost:5000/doacoes/${id}`);
      if (response.ok) {
          return await response.json();
      } else {
          throw new Error(`Failed to fetch doacao: ${response.status}`);
      }
  } catch (error) {
      console.error("Error fetching doacao:", error);
      throw error; 
  }
};

export default getDoacaoById;
