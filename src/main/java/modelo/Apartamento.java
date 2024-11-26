package modelo;

import javafx.beans.property.*;

public class Apartamento {

    // Propriedades do modelo Apartamento
    private final StringProperty numero; // Número do apartamento
    private final StringProperty bloco; // Bloco onde o apartamento está localizado
    private final DoubleProperty area; // Área do apartamento em metros quadrados
    private final StringProperty proprietario; // Nome do proprietário
    private final StringProperty descricao; // Descrição do apartamento
    private final StringProperty mesCompetencia; // Mês de competência relacionado ao apartamento
    private final DoubleProperty aluguel; // Valor do aluguel do apartamento
    private final DoubleProperty garagem; // Valor referente à garagem
    private final DoubleProperty valorCondominio; // Valor do condomínio
    private final DoubleProperty consumoAgua; // Consumo de água em m³
    private final DoubleProperty valorAgua; // Valor referente ao consumo de água
    private final DoubleProperty consumoGas; // Consumo de gás em m³
    private final DoubleProperty valorGas; // Valor referente ao consumo de gás
    private final DoubleProperty valorLuz; // Valor referente ao consumo de energia elétrica
    private final DoubleProperty total; // Valor total relacionado ao apartamento

    // Construtor que inicializa todas as propriedades
    public Apartamento(String numero, String bloco, double area, String proprietario, String descricao,
                       String mesCompetencia, double aluguel, double garagem, double valorCondominio,
                       double consumoAgua, double valorAgua, double consumoGas, double valorGas,
                       double valorLuz, double total) {
        this.numero = new SimpleStringProperty(numero);
        this.bloco = new SimpleStringProperty(bloco);
        this.area = new SimpleDoubleProperty(area);
        this.proprietario = new SimpleStringProperty(proprietario);
        this.descricao = new SimpleStringProperty(descricao);
        this.mesCompetencia = new SimpleStringProperty(mesCompetencia);
        this.aluguel = new SimpleDoubleProperty(aluguel);
        this.garagem = new SimpleDoubleProperty(garagem);
        this.valorCondominio = new SimpleDoubleProperty(valorCondominio);
        this.consumoAgua = new SimpleDoubleProperty(consumoAgua);
        this.valorAgua = new SimpleDoubleProperty(valorAgua);
        this.consumoGas = new SimpleDoubleProperty(consumoGas);
        this.valorGas = new SimpleDoubleProperty(valorGas);
        this.valorLuz = new SimpleDoubleProperty(valorLuz);
        this.total = new SimpleDoubleProperty(total);
    }

    // Getters para acessar as propriedades
    public StringProperty numeroProperty() { return numero; } // Retorna a propriedade do número
    public StringProperty blocoProperty() { return bloco; } // Retorna a propriedade do bloco
    public DoubleProperty areaProperty() { return area; } // Retorna a propriedade da área
    public StringProperty proprietarioProperty() { return proprietario; } // Retorna a propriedade do proprietário
    public StringProperty descricaoProperty() { return descricao; } // Retorna a propriedade da descrição
    public StringProperty mesCompetenciaProperty() { return mesCompetencia; } // Retorna a propriedade do mês de competência
    public DoubleProperty aluguelProperty() { return aluguel; } // Retorna a propriedade do aluguel
    public DoubleProperty garagemProperty() { return garagem; } // Retorna a propriedade da garagem
    public DoubleProperty valorCondominioProperty() { return valorCondominio; } // Retorna a propriedade do valor do condomínio
    public DoubleProperty consumoAguaProperty() { return consumoAgua; } // Retorna a propriedade do consumo de água
    public DoubleProperty valorAguaProperty() { return valorAgua; } // Retorna a propriedade do valor da água
    public DoubleProperty consumoGasProperty() { return consumoGas; } // Retorna a propriedade do consumo de gás
    public DoubleProperty valorGasProperty() { return valorGas; } // Retorna a propriedade do valor do gás
    public DoubleProperty valorLuzProperty() { return valorLuz; } // Retorna a propriedade do valor da luz
    public DoubleProperty totalProperty() { return total; } // Retorna a propriedade do valor total

    // Getters tradicionais para obter valores diretamente
    public String getNumero() { return numero.get(); } // Obtém o número do apartamento
    public String getBloco() { return bloco.get(); } // Obtém o bloco
    public double getArea() { return area.get(); } // Obtém a área
    public String getProprietario() { return proprietario.get(); } // Obtém o nome do proprietário
    public String getDescricao() { return descricao.get(); } // Obtém a descrição
    public String getMesCompetencia() { return mesCompetencia.get(); } // Obtém o mês de competência
    public double getAluguel() { return aluguel.get(); } // Obtém o valor do aluguel
    public double getGaragem() { return garagem.get(); } // Obtém o valor da garagem
    public double getValorCondominio() { return valorCondominio.get(); } // Obtém o valor do condomínio
    public double getConsumoAgua() { return consumoAgua.get(); } // Obtém o consumo de água
    public double getValorAgua() { return valorAgua.get(); } // Obtém o valor da água
    public double getConsumoGas() { return consumoGas.get(); } // Obtém o consumo de gás
    public double getValorGas() { return valorGas.get(); } // Obtém o valor do gás
    public double getValorLuz() { return valorLuz.get(); } // Obtém o valor da luz
    public double getTotal() { return total.get(); } // Obtém o valor total

    // Setters tradicionais para modificar os valores
    public void setNumero(String numero) { this.numero.set(numero); } // Define o número
    public void setBloco(String bloco) { this.bloco.set(bloco); } // Define o bloco
    public void setArea(double area) { this.area.set(area); } // Define a área
    public void setProprietario(String proprietario) { this.proprietario.set(proprietario); } // Define o proprietário
    public void setDescricao(String descricao) { this.descricao.set(descricao); } // Define a descrição
    public void setMesCompetencia(String mesCompetencia) { this.mesCompetencia.set(mesCompetencia); } // Define o mês de competência
    public void setAluguel(double aluguel) { this.aluguel.set(aluguel); } // Define o aluguel
    public void setGaragem(double garagem) { this.garagem.set(garagem); } // Define o valor da garagem
    public void setValorCondominio(double valorCondominio) { this.valorCondominio.set(valorCondominio); } // Define o valor do condomínio
    public void setConsumoAgua(double consumoAgua) { this.consumoAgua.set(consumoAgua); } // Define o consumo de água
    public void setValorAgua(double valorAgua) { this.valorAgua.set(valorAgua); } // Define o valor da água
    public void setConsumoGas(double consumoGas) { this.consumoGas.set(consumoGas); } // Define o consumo de gás
    public void setValorGas(double valorGas) { this.valorGas.set(valorGas); } // Define o valor do gás
    public void setValorLuz(double valorLuz) { this.valorLuz.set(valorLuz); } // Define o valor da luz
    public void setTotal(double total) { this.total.set(total); } // Define o valor total
}
