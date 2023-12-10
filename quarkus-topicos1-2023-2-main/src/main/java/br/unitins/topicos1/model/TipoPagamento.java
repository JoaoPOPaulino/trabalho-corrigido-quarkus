package br.unitins.topicos1.model;

public enum TipoPagamento {
    PIX(1, "Pix"),
    CARTAO_DEBITO(2, "Cartão Débito"),
    CARTAO_CREDITO(3, "Cartão Crédito"),
    BOLETO(4, "Boleto");

    private final Integer id;
    private final String label;

    private TipoPagamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoPagamento valueOf(Integer id) throws IllegalArgumentException {
        if ((id == null))
            return null;
        for (TipoPagamento tipoPagamento : TipoPagamento.values()) {
            if (tipoPagamento.getId().equals(id)) {
                return tipoPagamento;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}