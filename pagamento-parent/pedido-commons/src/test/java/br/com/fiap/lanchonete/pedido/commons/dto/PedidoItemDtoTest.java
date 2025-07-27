package br.com.fiap.lanchonete.pedido.commons.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoItemDtoTest {

    @Test
    void constructor_ShouldCreatePedidoItemDtoWithAllFields() {
        Long id = 1L;
        String nome = "Produto";
        Long produtoId = 123L;
        int quantidade = 2;
        Double valorUnitario = 10.0;
        String observacoes = "Observação";

        PedidoItemDto dto = new PedidoItemDto(id, nome, produtoId, quantidade, valorUnitario, observacoes);

        assertEquals(id, dto.getId());
        assertEquals(nome, dto.getNome());
        assertEquals(produtoId, dto.getProdutoId());
        assertEquals(quantidade, dto.getQuantidade());
        assertEquals(valorUnitario, dto.getValorUnitario());
        assertEquals(observacoes, dto.getObservacoes());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyPedidoItemDto() {
        PedidoItemDto dto = new PedidoItemDto();

        assertNull(dto.getId());
        assertNull(dto.getNome());
        assertNull(dto.getProdutoId());
        assertEquals(0, dto.getQuantidade());
        assertNull(dto.getValorUnitario());
        assertNull(dto.getObservacoes());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        PedidoItemDto dto = new PedidoItemDto();
        Long id = 1L;
        String nome = "Produto";
        Long produtoId = 123L;
        int quantidade = 3;
        Double valorUnitario = 15.0;
        String observacoes = "Nova observação";

        dto.setId(id);
        dto.setNome(nome);
        dto.setProdutoId(produtoId);
        dto.setQuantidade(quantidade);
        dto.setValorUnitario(valorUnitario);
        dto.setObservacoes(observacoes);

        assertEquals(id, dto.getId());
        assertEquals(nome, dto.getNome());
        assertEquals(produtoId, dto.getProdutoId());
        assertEquals(quantidade, dto.getQuantidade());
        assertEquals(valorUnitario, dto.getValorUnitario());
        assertEquals(observacoes, dto.getObservacoes());
    }
}