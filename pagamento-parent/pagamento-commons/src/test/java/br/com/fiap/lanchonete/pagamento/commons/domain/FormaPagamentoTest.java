package br.com.fiap.lanchonete.pagamento.commons.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormaPagamentoTest {

    @Test
    void values_ShouldContainPicPay() {
        FormaPagamento[] values = FormaPagamento.values();
        
        assertEquals(1, values.length);
        assertEquals(FormaPagamento.PIC_PAY, values[0]);
    }

    @Test
    void valueOf_ShouldReturnPicPay() {
        assertEquals(FormaPagamento.PIC_PAY, FormaPagamento.valueOf("PIC_PAY"));
    }
}