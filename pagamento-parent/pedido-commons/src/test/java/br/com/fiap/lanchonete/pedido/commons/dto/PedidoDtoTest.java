package br.com.fiap.lanchonete.pedido.commons.dto;

import br.com.fiap.lanchonete.pagamento.commons.dto.PagamentoDto;
import br.com.fiap.lanchonete.pedido.commons.domain.PedidoStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDtoTest {

    @Test
    void constructor_ShouldCreatePedidoDtoWithAllFields() {
        Long id = 1L;
        PedidoStatus status = PedidoStatus.valueOf("RECEBIDO");
        Long clienteId = 123L;
        List<PedidoItemDto> itens = Arrays.asList(new PedidoItemDto());
        PagamentoDto pagamentoDto = new PagamentoDto();
        String pagamentoId = "pag123";
        Long tempoEspera = 30L;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        PedidoDto dto = new PedidoDto(id, status, clienteId, itens, pagamentoDto, pagamentoId, tempoEspera, createdAt, updatedAt);

        assertEquals(id, dto.getId());
        assertEquals(status, dto.getStatus());
        assertEquals(clienteId, dto.getClienteId());
        assertEquals(itens, dto.getItens());
        assertEquals(pagamentoDto, dto.getPagamentoDto());
        assertEquals(pagamentoId, dto.getPagamentoId());
        assertEquals(tempoEspera, dto.getTempoEspera());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyPedidoDto() {
        PedidoDto dto = new PedidoDto();

        assertNull(dto.getId());
        assertNull(dto.getStatus());
        assertNull(dto.getClienteId());
        assertNull(dto.getItens());
        assertNull(dto.getPagamentoDto());
        assertNull(dto.getPagamentoId());
        assertNull(dto.getTempoEspera());
        assertNull(dto.getCreatedAt());
        assertNull(dto.getUpdatedAt());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        PedidoDto dto = new PedidoDto();
        Long id = 1L;
        PedidoStatus status = PedidoStatus.valueOf("RECEBIDO");
        Long clienteId = 456L;
        List<PedidoItemDto> itens = Arrays.asList(new PedidoItemDto());
        PagamentoDto pagamentoDto = new PagamentoDto();
        String pagamentoId = "pag456";
        Long tempoEspera = 45L;
        Date createdAt = new Date();
        Date updatedAt = new Date();

        dto.setId(id);
        dto.setStatus(status);
        dto.setClienteId(clienteId);
        dto.setItens(itens);
        dto.setPagamentoDto(pagamentoDto);
        dto.setPagamentoId(pagamentoId);
        dto.setTempoEspera(tempoEspera);
        dto.setCreatedAt(createdAt);
        dto.setUpdatedAt(updatedAt);

        assertEquals(id, dto.getId());
        assertEquals(status, dto.getStatus());
        assertEquals(clienteId, dto.getClienteId());
        assertEquals(itens, dto.getItens());
        assertEquals(pagamentoDto, dto.getPagamentoDto());
        assertEquals(pagamentoId, dto.getPagamentoId());
        assertEquals(tempoEspera, dto.getTempoEspera());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
    }
}