import { FormEvent, useEffect, useState } from "react";
import Link from "next/link";
import Input from "@/app/components/forms/Input";
import PessoaInterface from "../types/pessoa";

const updatePessoa = async (id: string, data: Partial<PessoaInterface>) => {
    const response = await fetch(`http://localhost:5000/pessoas/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    if (!response.ok) {
        throw new Error('Failed to update pessoa');
    }
    return response.json();
};

export default function UpdatePessoa({ id }: { id: string }) {
    const [pessoa, setPessoa] = useState<PessoaInterface | null>(null);
    const [nome, setNome] = useState('');
    const [rua, setRua] = useState('');
    const [numero, setNumero] = useState('');
    const [complemento, setComplemento] = useState('');
    const [cidadeId, setCidadeId] = useState('');
    const [tipoId, setTipoId] = useState('');

    useEffect(() => {
        const fetchPessoa = async () => {
            try {
                const response = await fetch(`http://localhost:5000/pessoas/${id}`);
                if (response.ok) {
                    const data = await response.json();
                    setPessoa(data);
                    setNome(data.nome);
                    setRua(data.rua);
                    setNumero(data.numero);
                    setComplemento(data.complemento);
                    setCidadeId(data.cidade_id.toString());
                    setTipoId(data.tipo_id.toString());
                } else {
                    console.error(`Failed to fetch pessoa: ${response.status}`);
                }
            } catch (error) {
                console.error("Error fetching pessoa:", error);
            }
        };

        fetchPessoa();
    }, [id]);

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        if (!pessoa) return;
        try {
            await updatePessoa(id, {
                nome,
                rua,
                numero,
                complemento,
                cidade_id: parseInt(cidadeId),
                tipo_id: parseInt(tipoId),
            });
        } catch (error) {
            console.error("Error updating pessoa:", error);
        }
    };

    if (!pessoa) {
        return <div>Carregando...</div>;
    }

    return (
        <main>
            <h1>Atualização de Pessoa: {pessoa.nome}</h1>
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
                    <Input name="tipoId" label="Tipo ID" value={tipoId} onChange={e => setTipoId(e.target.value)} />
                </div>
                <div>
                    <button type="submit">Atualizar</button>
                </div>
            </form>
            <Link href="/pessoas">Voltar para Lista de Pessoas</Link>
        </main>
    );
}
