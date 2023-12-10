package br.unitins.topicos1.model;

public enum TipoQuarto {
    CASUAL(1, "Casual"),
    LUXO(2, "Luxo"),
    PRSIDENCIAL(3, "Presidencial"),
    VIP(4, "Vip");

    private final Integer id;
    private final String label;

    private TipoQuarto(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoQuarto valueOf(Integer id) throws IllegalArgumentException {
        if ((id == null))
            return null;
        for (TipoQuarto tipoQuarto : TipoQuarto.values()) {
            if (tipoQuarto.getId().equals(id)) {
                return tipoQuarto;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
