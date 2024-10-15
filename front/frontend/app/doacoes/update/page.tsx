import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";
import getDoacaoById from "@/app/repository/doacoes/GetDoacaoById";

interface DoacaoDetailsParamsInterface {
    params: {
        id: string;
    };
}

export default function DoacaoDetails({ params }: DoacaoDetailsParamsInterface) {
    const [doacao, setDoacao] = useState<any>(null);
    const router = useRouter();

    useEffect(() => {
        const fetchDoacaoDetails = async () => {
            try {
                const doacao = await getDoacaoById(params.id);
                setDoacao(doacao);
            } catch (error) {
                console.error("Error fetching doacao:", error);
            }
        };

        fetchDoacaoDetails();
    }, [params]);

    if (!doacao) {
        return <div>Loading...</div>;
    }

    return (
        <main className="container m-auto">
            <h1>Detalhes da Doação</h1>
            <div>
                <Input
                    name="pessoaId"
                    label="ID da Pessoa"
                    value={doacao.pessoa_id.toString()}
                    readOnly
                />
            </div>
            <div>
                <Input
                    name="localId"
                    label="ID do Local"
                    value={doacao.local_id.toString()}
                    readOnly
                />
            </div>
            <div>
                <Input name="data" label="Data" type="date" value={doacao.data} readOnly />
            </div>
        </main>
    );
}
