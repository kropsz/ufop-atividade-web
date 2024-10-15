import CidadeInterface from "./cidade";

interface PessoaInterface {
  id: number;
  nome: string;
  rua: string;
  numero: string;
  complemento: string;
  rg: string;
  cidade: CidadeInterface; 
  tipo: string; 
  created_at: string;
  updated_at: string;
}

export default PessoaInterface;
