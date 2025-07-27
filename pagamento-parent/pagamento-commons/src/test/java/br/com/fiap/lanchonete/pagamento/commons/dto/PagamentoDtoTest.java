package br.com.fiap.lanchonete.pagamento.commons.dto;

import br.com.fiap.lanchonete.pagamento.commons.domain.FormaPagamento;
import br.com.fiap.lanchonete.pagamento.commons.domain.PagamentoStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoDtoTest {

    @Test
    void constructor_ShouldCreatePagamentoDtoWithAllFields() {
        String id = "1";
        Long pedidoId = 123L;
        PagamentoStatus status = PagamentoStatus.PENDENTE;
        FormaPagamento forma = FormaPagamento.PIC_PAY;
        BigDecimal valor = BigDecimal.valueOf(100.0);
        Date createdAt = new Date();
        Date updatedAt = new Date();

        PagamentoDto dto = new PagamentoDto(id, pedidoId, status, forma, valor, createdAt, updatedAt);

        assertEquals(id, dto.getId());
        assertEquals(pedidoId, dto.getPedidoId());
        assertEquals(status, dto.getStatus());
        assertEquals(forma, dto.getForma());
        assertEquals(valor, dto.getValor());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyPagamentoDto() {
        PagamentoDto dto = new PagamentoDto();

        assertNull(dto.getId());
        assertNull(dto.getPedidoId());
        assertNull(dto.getStatus());
        assertNull(dto.getForma());
        assertNull(dto.getValor());
        assertNull(dto.getCreatedAt());
        assertNull(dto.getUpdatedAt());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        PagamentoDto dto = new PagamentoDto();
        String id = "1";
        Long pedidoId = 123L;
        PagamentoStatus status = PagamentoStatus.CONFIRMADO;
        FormaPagamento forma = FormaPagamento.PIC_PAY;
        BigDecimal valor = BigDecimal.valueOf(150.0);
        Date createdAt = new Date();
        Date updatedAt = new Date();

        dto.setId(id);
        dto.setPedidoId(pedidoId);
        dto.setStatus(status);
        dto.setForma(forma);
        dto.setValor(valor);
        dto.setCreatedAt(createdAt);
        dto.setUpdatedAt(updatedAt);

        assertEquals(id, dto.getId());
        assertEquals(pedidoId, dto.getPedidoId());
        assertEquals(status, dto.getStatus());
        assertEquals(forma, dto.getForma());
        assertEquals(valor, dto.getValor());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
    }

    @Test
    void toString_ShouldReturnStringRepresentation() {
        PagamentoDto dto = new PagamentoDto("1", 123L, PagamentoStatus.PENDENTE, FormaPagamento.PIC_PAY, BigDecimal.valueOf(100.0), new Date(), new Date());

        String result = dto.toString();

        assertNotNull(result);
        assertTrue(result.contains("PagamentoDto"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("pedidoId=123"));
    }
}