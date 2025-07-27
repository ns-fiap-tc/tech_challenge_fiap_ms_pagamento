package br.com.fiap.lanchonete.pagamento.adapter.controller;

import br.com.fiap.lanchonete.pagamento.commons.domain.FormaPagamento;
import br.com.fiap.lanchonete.pagamento.commons.domain.PagamentoStatus;
import br.com.fiap.lanchonete.pagamento.commons.dto.PagamentoDto;
import br.com.fiap.lanchonete.pagamento.commons.persistence.PagamentoRepository;
import br.com.fiap.lanchonete.pagamento.core.domain.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoControllerTest {

    @Mock
    private PagamentoMockServiceClient mockServiceClient;

    @Mock
    private PagamentoRepository repository;

    private PagamentoController controller;
    private PagamentoDto pagamentoDto;

    @BeforeEach
    void setUp() {
        controller = new PagamentoController(mockServiceClient, repository);
        pagamentoDto = new PagamentoDto();
        pagamentoDto.setId("1");
        pagamentoDto.setPedidoId(123L);
        pagamentoDto.setStatus(PagamentoStatus.PENDENTE);
        pagamentoDto.setForma(FormaPagamento.PIC_PAY);
        pagamentoDto.setValor(BigDecimal.valueOf(100.0));
        pagamentoDto.setCreatedAt(new Date());
        pagamentoDto.setUpdatedAt(new Date());
    }

    @Test
    void save_ShouldReturnSavedPagamento() {
        when(repository.save(any(PagamentoDto.class))).thenReturn(pagamentoDto);

        PagamentoDto result = controller.save(pagamentoDto);

        assertNotNull(result);
        assertEquals(pagamentoDto.getId(), result.getId());
        verify(repository).save(any(PagamentoDto.class));
    }

    @Test
    void findById_ShouldReturnPagamento() {
        when(repository.findById("1")).thenReturn(pagamentoDto);

        PagamentoDto result = controller.findById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(repository).findById("1");
    }

    @Test
    void findAll_ShouldReturnAllPagamentos() {
        List<PagamentoDto> pagamentos = Arrays.asList(pagamentoDto);
        when(repository.findAll()).thenReturn(pagamentos);

        List<PagamentoDto> result = controller.findAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        controller.deleteById("1");

        verify(repository).deleteById("1");
    }

    @Test
    void findByPedidoId_ShouldReturnPagamento() {
        when(repository.findByPedidoId(123L)).thenReturn(pagamentoDto);

        PagamentoDto result = controller.findByPedidoId(123L);

        assertNotNull(result);
        assertEquals(123L, result.getPedidoId());
        verify(repository).findByPedidoId(123L);
    }

    @Test
    void realizarPagamento_WhenPagamentoNotExists_ShouldCreateAndProcessPayment() {
        when(repository.findByPedidoId(123L)).thenReturn(null);
        when(repository.save(any(PagamentoDto.class))).thenReturn(pagamentoDto);

        PagamentoDto result = controller.realizarPagamento(pagamentoDto);

        assertNotNull(result);
        verify(mockServiceClient).realizarPagamento(123L, BigDecimal.valueOf(100.0));
        verify(repository).save(any(PagamentoDto.class));
    }

    @Test
    void realizarPagamento_WhenPagamentoExistsAndNotConfirmado_ShouldProcessPayment() {
        PagamentoDto existingPagamento = new PagamentoDto();
        existingPagamento.setPedidoId(123L);
        existingPagamento.setStatus(PagamentoStatus.PENDENTE);
        
        when(repository.findByPedidoId(123L)).thenReturn(existingPagamento);
        when(repository.save(any(PagamentoDto.class))).thenReturn(pagamentoDto);

        PagamentoDto result = controller.realizarPagamento(pagamentoDto);

        assertNotNull(result);
        verify(mockServiceClient).realizarPagamento(123L, BigDecimal.valueOf(100.0));
        verify(repository).save(any(PagamentoDto.class));
    }

    @Test
    void realizarPagamento_WhenPagamentoConfirmado_ShouldNotProcessPayment() {
        PagamentoDto existingPagamento = new PagamentoDto();
        existingPagamento.setPedidoId(123L);
        existingPagamento.setStatus(PagamentoStatus.CONFIRMADO);
        
        when(repository.findByPedidoId(123L)).thenReturn(existingPagamento);
        when(repository.save(any(PagamentoDto.class))).thenReturn(pagamentoDto);

        PagamentoDto result = controller.realizarPagamento(pagamentoDto);

        assertNotNull(result);
        verify(mockServiceClient, never()).realizarPagamento(anyLong(), any(BigDecimal.class));
        verify(repository).save(any(PagamentoDto.class));
    }
}