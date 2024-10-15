import { FormEvent, useEffect, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";
import deleteDoacao from "@/app/repository/doacoes/DeleteDoacao";

interface DeleteDoacaoParamsInterface {
    params: {
        id: string;
    };
}

export default function DeleteDoacao({ params }: DeleteDoacaoParamsInterface) {
    const [loading, setLoading] = useState(false);
    const [pessoaId, setPessoaId] = useState('');
    const [localId, setLocalId] = useState('');
    const [data, setData] = useState('');
    const router = useRouter();

    useEffect(() => {
        const fetchDoacaoDetails = async () => {
            try {
                const response = await fetch(`http://localhost:5000/doacoes/${params.id}`);
                if (response.ok) {
                    const doacao = await response.json();
                    setPessoaId(doacao.pessoa_id.toString());
                    setLocalId(doacao.local_id.toString());
                    setData(doacao.data);
                } else {
                    console.error(`Failed to fetch doacao: ${response.status}`);
                }
            } catch (error) {
                console.error("Error fetching doacao:", error);
            }
        };

        fetchDoacaoDetails();
    }, [params]);

    const handleDelete = async (event: FormEvent) => {
        event.preventDefault();
        setLoading(true);
        try {
            await deleteDoacao(params.id);
            console.log("Doacao deletada!");
            router.push('/doacoes');
        } catch (error) {
            console.error("Error doacao:", error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <main className="container m-auto">
            <h1>Exclusão de Doação</h1>
            <form onSubmit={handleDelete}>
                <div>
                    <Input name="pessoaId" label="ID da Pessoa" value={pessoaId} readOnly />
                </div>
                <div>
                    <Input name="localId" label="ID do Local" value={localId} readOnly />
                </div>
                <div>
                    <Input name="data" label="Data" type="date" value={data} readOnly />
                </div>
                <div>
                    <button type="submit" disabled={loading}>
                        {loading ? 'Excluindo...' : 'Excluir'}
                    </button>
                </div>
            </form>
        </main>
    );
}
