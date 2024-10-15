const getLocalById = async(id: string) => {

  const local = await fetch(
      `http://localhost:5000/locais/${id}`, 
      { cache: 'no-store'}
      )

  return local.json();

}


export default getLocalById;