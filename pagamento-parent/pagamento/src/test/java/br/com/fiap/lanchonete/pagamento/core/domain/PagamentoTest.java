package br.com.fiap.lanchonete.pagamento.core.domain;

import br.com.fiap.lanchonete.pagamento.commons.domain.FormaPagamento;
import br.com.fiap.lanchonete.pagamento.commons.domain.PagamentoStatus;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    void constructor_ShouldCreatePagamentoWithAllFields() {
        String id = "1";
        Long pedidoId = 123L;
        PagamentoStatus status = PagamentoStatus.PENDENTE;
        FormaPagamento forma = FormaPagamento.PIC_PAY;
        Double valor = 100.0;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        Pagamento pagamento = new Pagamento(id, pedidoId, status, forma, valor, createdAt, updatedAt);

        assertEquals(id, pagamento.getId());
        assertEquals(pedidoId, pagamento.getPedidoId());
        assertEquals(status, pagamento.getStatus());
        assertEquals(forma, pagamento.getForma());
        assertEquals(valor, pagamento.getValor());
        assertEquals(createdAt, pagamento.getCreatedAt());
        assertEquals(updatedAt, pagamento.getUpdatedAt());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyPagamento() {
        Pagamento pagamento = new Pagamento();

        assertNull(pagamento.getId());
        assertNull(pagamento.getPedidoId());
        assertNull(pagamento.getStatus());
        assertNull(pagamento.getForma());
        assertNull(pagamento.getValor());
        assertNull(pagamento.getCreatedAt());
        assertNull(pagamento.getUpdatedAt());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        Pagamento pagamento = new Pagamento();
        String id = "1";
        Long pedidoId = 123L;
        PagamentoStatus status = PagamentoStatus.CONFIRMADO;
        FormaPagamento forma = FormaPagamento.PIC_PAY;
        Double valor = 150.0;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        pagamento.setId(id);
        pagamento.setPedidoId(pedidoId);
        pagamento.setStatus(status);
        pagamento.setForma(forma);
        pagamento.setValor(valor);
        pagamento.setCreatedAt(createdAt);
        pagamento.setUpdatedAt(updatedAt);

        assertEquals(id, pagamento.getId());
        assertEquals(pedidoId, pagamento.getPedidoId());
        assertEquals(status, pagamento.getStatus());
        assertEquals(forma, pagamento.getForma());
        assertEquals(valor, pagamento.getValor());
        assertEquals(createdAt, pagamento.getCreatedAt());
        assertEquals(updatedAt, pagamento.getUpdatedAt());
    }

    @Test
    void toString_ShouldReturnStringRepresentation() {
        Pagamento pagamento = new Pagamento("1", 123L, PagamentoStatus.PENDENTE, FormaPagamento.PIC_PAY, 100.0, new Date(), new Date());

        String result = pagamento.toString();

        assertNotNull(result);
        assertTrue(result.contains("Pagamento"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("pedidoId=123"));
    }
}