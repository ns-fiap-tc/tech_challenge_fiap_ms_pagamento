package br.com.fiap.lanchonete.pedido.commons.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoStatusTest {

    @Test
    void valueOf_ShouldReturnCorrectStatus() {
        for (PedidoStatus status : PedidoStatus.values()) {
            assertEquals(status, PedidoStatus.valueOf(status.name()));
        }
    }

    @Test
    void values_ShouldReturnAllStatuses() {
        PedidoStatus[] values = PedidoStatus.values();
        assertTrue(values.length > 0);
    }
}