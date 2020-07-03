package com.raphael.cursoudemy.domain;

import com.raphael.cursoudemy.domain.enums.EstadoPagamento;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagamento_boleto")
public class PagamentoComBoleto extends Pagamento{
    private static final long serialVersionUID = 1L;

    @Column(name = "data_vcto")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @Column(name = "data_pgto")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


}
