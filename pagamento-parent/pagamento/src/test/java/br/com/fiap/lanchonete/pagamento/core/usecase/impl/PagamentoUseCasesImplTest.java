package br.com.fiap.lanchonete.pagamento.core.usecase.impl;

import br.com.fiap.lanchonete.pagamento.adapter.gateway.PagamentoGateway;
import br.com.fiap.lanchonete.pagamento.commons.domain.FormaPagamento;
import br.com.fiap.lanchonete.pagamento.commons.domain.PagamentoStatus;
import br.com.fiap.lanchonete.pagamento.core.domain.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoUseCasesImplTest {

    @Mock
    private PagamentoGateway gateway;

    private PagamentoUseCasesImpl useCase;
    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        useCase = new PagamentoUseCasesImpl(gateway);
        pagamento = new Pagamento("1", 123L, PagamentoStatus.PENDENTE, FormaPagamento.PIC_PAY, 100.0, new Date(), new Date());
    }

    @Test
    void save_ShouldReturnSavedPagamento() {
        when(gateway.save(pagamento)).thenReturn(pagamento);

        Pagamento result = useCase.save(pagamento);

        assertEquals(pagamento, result);
        verify(gateway).save(pagamento);
    }

    @Test
    void findById_ShouldReturnPagamento() {
        when(gateway.findById("1")).thenReturn(pagamento);

        Pagamento result = useCase.findById("1");

        assertEquals(pagamento, result);
        verify(gateway).findById("1");
    }

    @Test
    void findAll_ShouldReturnAllPagamentos() {
        List<Pagamento> pagamentos = Arrays.asList(pagamento);
        when(gateway.findAll()).thenReturn(pagamentos);

        List<Pagamento> result = useCase.findAll();

        assertEquals(pagamentos, result);
        verify(gateway).findAll();
    }

    @Test
    void deleteById_ShouldCallGatewayDelete() {
        useCase.deleteById("1");

        verify(gateway).deleteById("1");
    }

    @Test
    void updateStatus_WhenStatusNotConfirmado_ShouldUpdateStatus() {
        when(gateway.findByPedidoId(123L)).thenReturn(pagamento);

        useCase.updateStatus(123L, PagamentoStatus.CONFIRMADO);

        verify(gateway).findByPedidoId(123L);
        verify(gateway).updateStatus(123L, PagamentoStatus.CONFIRMADO);
    }

    @Test
    void updateStatus_WhenStatusConfirmado_ShouldNotUpdateStatus() {
        pagamento.setStatus(PagamentoStatus.CONFIRMADO);
        when(gateway.findByPedidoId(123L)).thenReturn(pagamento);

        useCase.updateStatus(123L, PagamentoStatus.RECUSADO);

        verify(gateway).findByPedidoId(123L);
        verify(gateway, never()).updateStatus(123L, PagamentoStatus.RECUSADO);
    }

    @Test
    void findByPedidoId_ShouldReturnPagamento() {
        when(gateway.findByPedidoId(123L)).thenReturn(pagamento);

        Pagamento result = useCase.findByPedidoId(123L);

        assertEquals(pagamento, result);
        verify(gateway).findByPedidoId(123L);
    }
}