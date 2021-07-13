package br.com.zupacademy.diego.ecommerce.models;

public enum PagamentoGateway {

    PAYPAL, PAGSEGURO;

    private String url;

    static {
        PAYPAL.url = "paypal.com?buyerId=";
        PAGSEGURO.url = "pagseguro.com?returnId=";
    }

    public String getUrl(Long id) {
        return this.url + id.toString() + "&redirectUrl=retornoPosPagamento";
    }
}
