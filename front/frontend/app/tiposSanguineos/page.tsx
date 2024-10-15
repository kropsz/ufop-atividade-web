// pages/tipos-sanguineos.js

import Link from "next/link";
import Line from "../components/Line";
import getAllTiposSanguineos from "../repository/tiposSanguineos/getAllTiposSanguineos";
import { useEffect, useState } from "react";

export default function TiposSanguineos() {
    const [tiposSanguineos, setTiposSanguineos] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getAllTiposSanguineos();
                setTiposSanguineos(data);
            } catch (error) {
                console.error('Error fetching tipos sanguíneos:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <main>
            <h1>Lista de Tipos Sanguíneos</h1>
            <Link href="/tipos-sanguineos/create">Cadastrar</Link>

            <ul>
                {tiposSanguineos.map(tipo => (
                    <Line key={tipo.id} id={tipo.id} description={`Tipo sanguíneo: ${tipo.tipo}`} />
                ))}
            </ul>
        </main>
    );
}
