const getAllTiposSanguineos = async () => {
  const tipos = await fetch("http://localhost:5000/tiposSanguineos", {
    cache: "no-store",
  });

  return tipos.json();
};

export default getAllTiposSanguineos