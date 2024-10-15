import { FormEvent, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";

const createDoacao = async (data: { pessoa_id: string; local_id: string; data: string }) => {
    const response = await fetch(`http://localhost:5000/doacoes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        throw new Error('Failed to create doacao');
    }
    return response.json();
};

export default function CreateDoacao() {
    const router = useRouter();
    const [pessoaId, setPessoaId] = useState('');
    const [localId, setLocalId] = useState('');
    const [data, setData] = useState('');

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        try {
            await createDoacao({ pessoa_id: pessoaId, local_id: localId, data });
            router.push('/doacoes');
        } catch (error) {
            console.error("Error creating doacao:", error);
        }
    };

    return (
        <main>
            <h1>Criação de Doação</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Input name="pessoaId" label="ID da Pessoa" value={pessoaId} onChange={e => setPessoaId(e.target.value)} />
                </div>
                <div>
                    <Input name="localId" label="ID do Local" value={localId} onChange={e => setLocalId(e.target.value)} />
                </div>
                <div>
                    <Input name="data" label="Data" type="date" value={data} onChange={e => setData(e.target.value)} />
                </div>
                <div>
                    <button type="submit">Criar</button>
                </div>
            </form>
        </main>
    );
}
