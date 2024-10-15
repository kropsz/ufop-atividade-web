import { FormEvent, useEffect, useState } from "react";
import { useRouter } from "next/router";
import Input from "@/app/components/forms/Input";
import getAllEstados from "@/app/repository/estados/GetAllEstados";
import EstadoInterface from "@/app/types/estado";

export default function CreatePessoa() {
    const [nome, setNome] = useState('');
    const [rua, setRua] = useState('');
    const [numero, setNumero] = useState('');
    const [complemento, setComplemento] = useState('');
    const [rg, setRg] = useState('');
    const [cidadeId, setCidadeId] = useState('');
    const [tipoId, setTipoId] = useState('');
    const [estados, setEstados] = useState<EstadoInterface[]>([]);
    const { push } = useRouter();

    useEffect(() => {
        getAllEstados()
            .then(data => setEstados(data))
            .catch(error => console.error(error));
    }, []);

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();
        const data = {
            nome,
            rua,
            numero,
            complemento,
            rg,
            cidade_id: cidadeId,
            tipo_id: tipoId,
        };
        const requestInit: RequestInit = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };
        try {
            const response = await fetch('http://localhost:5000/pessoas', requestInit);
            if (response.ok) {
                const pessoa = await response.json();
                const { id } = pessoa;
                window.alert(`Pessoa inserida com sucesso! Id: ${id}`);
                push('/pessoas');
            }
        } catch (error) {
            console.error('Error creating pessoa:', error);
        }
    }

    return (
        <main className="container m-auto">
            <h1>Cadastro de pessoas</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <Input name="nome" label="Nome" setValue={(event) => { setNome(event.target.value) }} />
                </div>
                <div>
                    <Input name="rua" label="Rua" setValue={(event) => { setRua(event.target.value) }} />
                </div>
                <div>
                    <Input name="numero" label="Número" setValue={(event) => { setNumero(event.target.value) }} />
                </div>
                <div>
                    <Input name="complemento" label="Complemento" setValue={(event) => { setComplemento(event.target.value) }} />
                </div>
                <div>
                    <Input name="rg" label="RG" setValue={(event) => { setRg(event.target.value) }} />
                </div>
                <div>
                    <label htmlFor="cidadeId">Cidade</label>
                    <select
                        name="cidadeId"
                        id="cidadeId"
                        value={cidadeId}
                        onChange={(event) => {
                            setCidadeId(event.target.value);
                        }}>
                        <option value="" disabled>Selecione:</option>
                        {
                            estados.map((estado) => {
                                return (
                                    <option
                                        value={estado.id}
                                        key={estado.id}
                                    >{estado.nome}</option>
                                );
                            })
                        }
                    </select>
                </div>
                <div>
                    <Input name="tipoId" label="Tipo" setValue={(event) => { setTipoId(event.target.value) }} />
                </div>
                <div>
                    <button type="submit">Cadastrar</button>
                    <button type="reset">Limpar</button>
                </div>
            </form>
        </main>
    );
}
