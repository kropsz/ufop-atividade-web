import Link from "next/link";
import Line from "../components/Line";
import getAllLocaisColeta from "../repository/locaisColeta/getAllLocaisColeta";
import { useEffect, useState } from "react";

export default function LocaisColeta() {
    const [locaisColeta, setLocaisColeta] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getAllLocaisColeta();
                setLocaisColeta(data);
            } catch (error) {
                console.error('Error fetching locais de coleta:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <main>
            <h1>Lista de Locais de Coleta</h1>
            <Link href="/locais-coleta/create">Cadastrar</Link>

            <ul>
                {locaisColeta.map(local => (
                    <Line key={local.id} id={local.id} description={`Local de coleta: ${local.nome}`} />
                ))}
            </ul>
        </main>
    );
}
