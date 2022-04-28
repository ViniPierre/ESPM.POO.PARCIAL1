package src;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Processo {

    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public static void reservarMesa() {

        boolean tipo;
        String aux = "";
        TipoPessoa tipoPessoa = inputTipoCliente();

        String nome = JOptionPane.showInputDialog("Nome: ");

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

        while(!aux.equals("1") && !aux.equals("2")){
            aux = JOptionPane.showInputDialog("Qual será a forma de pagamento? \nDigite 1 se for à vista. \nDigite 2 se for parcelado.").toLowerCase();
            if(!aux.equals("1") && !aux.equals("2")){
                JOptionPane.showMessageDialog(null, " Informe um valor válido: \n1. Pagamento à vista \n 2. Pagamento parcelado");
            }
        }

        if("1".equals(aux)){
            tipo = true;
        } else {
            tipo = false;        
        }

        Reserva reserva = new Reserva(c, tipo);
        reserva.setPagamentoAVista(tipo);
    
        reservas.add(reserva);
            
        if(reservas.size() > 6){
            JOptionPane.showMessageDialog(null, "O número máximo de reservas foi atingido, mas não se abale, você está na lista de espera!");
        } else{
            JOptionPane.showMessageDialog(null, "Sua reserva foi resgistrada e está confirmada!");
        }
    }

    private static TipoPessoa inputTipoCliente(){

        String tp=" ";

        while(!tp.equals("1")&& !tp.equals("2")){  
            tp = JOptionPane.showInputDialog("Qual o tipo de cliente? \nDigite 1 se for pessoa física. \nDigite 2 se for pessoa jurídica.").toLowerCase();
            if(!tp.equals("1")&& !tp.equals("2")){
                JOptionPane.showMessageDialog(null, "Digite uma opção válida: 1(Pessoa Física) ou 2(Pessoa Jurídica)");
            }
        }
        return tp.equals("1") ? TipoPessoa.Fisica : TipoPessoa.Juridica;
    }

    public static void pesquisarReserva(){
        if(reservas.size() > 0){
            String aux = JOptionPane.showInputDialog(null, "Digite o seu CPF ou seu CNPJ");
            int nreserva = buscarReservas(aux);

            if(nreserva >= 0){
                JOptionPane.showMessageDialog(null, "Sua reserva está cadastrada");
            } else {
                JOptionPane.showMessageDialog(null, "Você não possui reserva cadastrada");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ainda não existem reservas");
        }
    }

    public static int buscarReservas(String aux){
        
        if(reservas.size() > 0){
            for(int i = 0; i < reservas.size(); i++){
                if(reservas.get(i).getCliente() instanceof PessoaFisica){
                    Cliente c = reservas.get(i).getCliente();
                    PessoaFisica pf = (PessoaFisica) (c);
                    if(pf.getCpf().equals(aux)){
                        return i;
                    }
                }
                if(reservas.get(i).getCliente() instanceof PessoaJuridica){
                    Cliente c = reservas.get(i).getCliente();
                    PessoaJuridica pj = (PessoaJuridica) (c);
                    if(pj.getCnpj().equals(aux)){
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static void imprimirListaDeEspera() {
        if(reservas.size() > 7) {
            for(int i = 0; i < reservas.size(); i++) {
                if(i >= 7){
                    JOptionPane.showMessageDialog(null, "Tem apenas " + (i-1) + " mesas antes de você" + "\n" + reservas.get(i));
                }    
            }

        } else {
            JOptionPane.showMessageDialog(null, "Não possui lista de espera, ainda é possível realizar reservas");
            
        }
    }
    
    public static void cancelarReserva(){
        if(reservas.size() > 0) {
            String aux = JOptionPane.showInputDialog(null, "Digite o seu CPF ou o seu CNPJ");
            int nreservas = buscarReservas(aux);

            if(nreservas >= 0){
                reservas.remove(nreservas);
                JOptionPane.showMessageDialog(null, "Sua reserva foi cancelada");
            } else {
                JOptionPane.showMessageDialog(null, "Seu CPF/CNPJ ainda não foi cadastrado");
            }

        } else {
            JOptionPane.showMessageDialog(null, "O sistema ainda não possui nenhuma reserva");
        }
    }

    public static void imprimirReserva() {
        if(reservas.size() > 0) {
            for(int i = 0; i < reservas.size(); i++) {
                if(i < 6){
                    JOptionPane.showMessageDialog(null, reservas.get(i));
                } else {
                    return;
                }   
            }

        }else {
            JOptionPane.showMessageDialog(null, "O sistema ainda não possui nenhuma reserva");
        }
    }
    

}