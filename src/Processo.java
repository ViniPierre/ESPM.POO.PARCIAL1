package src;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Processo {

    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public static void reservarMesa() {

        boolean tipo;
        String aux = "";
        TipoPessoa tipoPessoa = inputTipoCliente();

        String nome = JOptionPane.showInputDiaLog("Nome: ");

        Cliente c = null;

        switch(tipoPessoa){
            case Fisica:
                String cpf = JOptionPane.showInputDialog("CPF: ");
                PessoaFisica f = new PessoaFisica(nome, cpf);
                c = f;
                break;
            case Juridica:
                String cnpj = JOptionPane.showInputDialog("CNPJ: ");
                PessoaJuridica j = new PessoaJuridica(nome, cnpj);
                c = j;
                break;
        }

        while(!aux.equals("s") && !aux.equals("n")){
            aux = JOptionPane.showInputDialog("O pagamento será à vista? [S/N]: ").toLowerCase();
            if(!aux.equals("s") && !aux.equals("n")){
                JOptionPane.showMessageDialog(null, "ERRO!! Informe um valor válido\nS: Pagamento à vista | N: Pagamento parcelado");
            }
        }

        if("s".equals(aux)){
            tipo = true;
        } else {
            tipo = false;        
        }

        Reserva reserva = new Reserva(c, tipo);
        reserva.setPagamentoAVista(tipo);
    
        reservas.add(reserva);
            
        if(reservas.size() > 6){
            JOptionPane.showMessageDialog(null, "Reservas esgotadas :( \nVocê está na lista de espera");
        } else{
            JOptionPane.showMessageDialog(null, "Reserva efetuada com sucesso");
        }
    }
}