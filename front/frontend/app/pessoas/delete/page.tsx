import { FormEvent, useEffect, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";
import PessoaInterface from "./types/pessoa";

interface DeletePessoaParamsInterface {
    params: {
        id: string
    }
}

export default function DeletePessoa({ params }: DeletePessoaParamsInterface) {
    const [pessoa, setPessoa] = useState<PessoaInterface | null>(null);
    const { push } = useRouter();

    useEffect(() => {
        const fetchPessoa = async () => {
            try {
                const response = await fetch(`http://localhost:5000/pessoas/${params.id}`);
                if (response.ok) {
                    const data = await response.json();
                    setPessoa(data);
                } else {
                    console.error(`Failed to fetch pessoa: ${response.status}`);
                }
            } catch (error) {
                console.error("Error fetching pessoa:", error);
            }
        };

        fetchPessoa();
    }, [params]);

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();
        if (!pessoa) return;

        const requestInit: RequestInit = {
            method: "DELETE"
        };

        try {
            const response = await fetch(`http://localhost:5000/pessoas/${pessoa.id}`, requestInit);
            if (response.ok) {
                window.alert(`Pessoa excluída com sucesso! Id: ${pessoa.id}`);
                push('/pessoas');
            } else {
                console.error(`Failed to delete pessoa: ${response.status}`);
                window.alert("Erro na exclusão da Pessoa!");
            }
        } catch (error) {
            console.error("Error deleting pessoa:", error);
            window.alert("Erro na exclusão da Pessoa!");
        }
    }

    if (!pessoa) {
        return <div>Carregando...</div>;
    }

    return (
        <main className="container m-auto">
            <h1>Exclusão de Pessoa: {pessoa.nome}</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Input name="nome" label="Nome" value={pessoa.nome} readOnly />
                </div>
                <div>
                    <Input name="rua" label="Rua" value={pessoa.rua} readOnly />
                </div>
                <div>
                    <Input name="numero" label="Número" value={pessoa.numero} readOnly />
                </div>
                <div>
                    <Input name="complemento" label="Complemento" value={pessoa.complemento} readOnly />
                </div>
                <div>
                    <Input name="rg" label="RG" value={pessoa.rg} readOnly />
                </div>
                <div>
                    <Input name="cidade" label="Cidade" value={pessoa.cidade.nome} readOnly />
                </div>
                <div>
                    <Input name="tipo" label="Tipo" value={pessoa.tipo} readOnly />
                </div>
                <div>
                    <button type="submit">Excluir</button>
                    <button type="reset">Limpar</button>
                </div>
            </form>
        </main>
    );
}
