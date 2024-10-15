import { FormEvent, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";

const createTipoSanguineo = async (data: { tipo: string; fator: string }) => {
    const response = await fetch(`http://localhost:5000/tipos`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        throw new Error('Failed to create tipo sanguineo');
    }
    return response.json();
};

export default function CreateTipoSanguineo() {
    const router = useRouter();
    const [tipo, setTipo] = useState('');
    const [fator, setFator] = useState('');

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        try {
            await createTipoSanguineo({ tipo, fator });
            router.push('/tipos-sanguineos');
        } catch (error) {
            console.error("Error creating tipo sanguineo:", error);
        }
    };

    return (
        <main>
            <h1>Criação de Tipo Sanguíneo</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Input name="tipo" label="Tipo" value={tipo} onChange={e => setTipo(e.target.value)} />
                </div>
                <div>
                    <Input name="fator" label="Fator" value={fator} onChange={e => setFator(e.target.value)} />
                </div>
                <div>
                    <button type="submit">Criar</button>
                </div>
            </form>
        </main>
    );
}
