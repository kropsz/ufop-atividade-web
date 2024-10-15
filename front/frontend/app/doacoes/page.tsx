import Link from "next/link";
import Line from "../components/Line";
import getAllDoacoes from "../repository/doacoes/getAllDoacoes";
import { useEffect, useState } from "react";

export default function Doacoes() {
    const [doacoes, setDoacoes] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getAllDoacoes();
                setDoacoes(data);
            } catch (error) {
                console.error('Error fetching doacoes:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <main>
            <h1>Lista de Doações</h1>
            <Link href="/doacoes/create">Cadastrar</Link>

            <ul>
                {doacoes.map(doacao => (
                    <Line key={doacao.id} id={doacao.id} description={`Doação para ${doacao.destinatario}`} />
                ))}
            </ul>
        </main>
    );
}
