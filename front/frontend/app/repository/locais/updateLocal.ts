const updateLocal = async (localData) => {
  const { id, nome, rua, numero, complemento, cidade_id } = localData;
  const requestInit = {
      method: "PUT",
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({
          id,
          nome,
          rua,
          numero,
          complemento,
          cidade_id
      })
  };

  try {
      const response = await fetch(`http://localhost:5000/locais/${id}`, requestInit);
      if (response.ok) {
          const updatedLocal = await response.json();
          return updatedLocal;
      } else {
          throw new Error('Failed to update local');
      }
  } catch (error) {
      throw new Error('Error updating local: ' + error.message);
  }
};

export default updateLocal;
