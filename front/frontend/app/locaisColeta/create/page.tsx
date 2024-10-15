import { FormEvent, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";

const createLocalColeta = async (data: { nome: string; rua: string; numero: string; complemento: string; cidade_id: string }) => {
    const response = await fetch(`http://localhost:5000/locais`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        throw new Error('Failed to create local coleta');
    }
    return response.json();
};

export default function CreateLocalColeta() {
    const router = useRouter();
    const [nome, setNome] = useState('');
    const [rua, setRua] = useState('');
    const [numero, setNumero] = useState('');
    const [complemento, setComplemento] = useState('');
    const [cidadeId, setCidadeId] = useState('');

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        try {
            await createLocalColeta({ nome, rua, numero, complemento, cidade_id: cidadeId });
            router.push('/locais-coleta');
        } catch (error) {
            console.error("Error creating local coleta:", error);
        }
    };

    return (
        <main>
            <h1>Criação de Local de Coleta</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Input name="nome" label="Nome" value={nome} onChange={e => setNome(e.target.value)} />
                </div>
                <div>
                    <Input name="rua" label="Rua" value={rua} onChange={e => setRua(e.target.value)} />
                </div>
                <div>
                    <Input name="numero" label="Número" value={numero} onChange={e => setNumero(e.target.value)} />
                </div>
                <div>
                    <Input name="complemento" label="Complemento" value={complemento} onChange={e => setComplemento(e.target.value)} />
                </div>
                <div>
                    <Input name="cidadeId" label="Cidade ID" value={cidadeId} onChange={e => setCidadeId(e.target.value)} />
                </div>
                <div>
                    <button type="submit">Criar</button>
                </div>
            </form>
        </main>
    );
}
