package com.raphael.cursoudemy.domain;

import com.raphael.cursoudemy.domain.enums.EstadoPagamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pagamento_cartao")
public class PagamentoComCartao extends Pagamento{
    private static final long serialVersionUID = 1L;

    @Column(name = "numero_parcelas")
    private Integer numeroParcelas;

    public PagamentoComCartao() {

    }

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
