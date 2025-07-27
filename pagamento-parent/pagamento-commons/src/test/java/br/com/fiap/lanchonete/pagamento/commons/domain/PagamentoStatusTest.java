package br.com.fiap.lanchonete.pagamento.commons.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoStatusTest {

    @Test
    void values_ShouldContainAllStatuses() {
        PagamentoStatus[] values = PagamentoStatus.values();
        
        assertEquals(4, values.length);
        assertEquals(PagamentoStatus.PENDENTE, values[0]);
        assertEquals(PagamentoStatus.PROCESSAMENTO, values[1]);
        assertEquals(PagamentoStatus.CONFIRMADO, values[2]);
        assertEquals(PagamentoStatus.RECUSADO, values[3]);
    }

    @Test
    void valueOf_ShouldReturnCorrectStatus() {
        assertEquals(PagamentoStatus.PENDENTE, PagamentoStatus.valueOf("PENDENTE"));
        assertEquals(PagamentoStatus.PROCESSAMENTO, PagamentoStatus.valueOf("PROCESSAMENTO"));
        assertEquals(PagamentoStatus.CONFIRMADO, PagamentoStatus.valueOf("CONFIRMADO"));
        assertEquals(PagamentoStatus.RECUSADO, PagamentoStatus.valueOf("RECUSADO"));
    }
}