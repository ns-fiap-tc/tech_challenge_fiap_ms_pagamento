package br.com.fiap.lanchonete.pagamento.adapter.gateway;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoGatewayTest {

    @Mock
    private PagamentoRepository repository;

    private PagamentoGateway gateway;
    private Pagamento pagamento;
    private PagamentoDto pagamentoDto;

    @BeforeEach
    void setUp() {
        gateway = new PagamentoGateway(repository);
        
        pagamento = new Pagamento();
        pagamento.setId("1");
        pagamento.setPedidoId(123L);
        pagamento.setStatus(PagamentoStatus.PENDENTE);
        pagamento.setForma(FormaPagamento.PIC_PAY);
        pagamento.setValor(100.0);
        pagamento.setCreatedAt(new Date());
        pagamento.setUpdatedAt(new Date());

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

        Pagamento result = gateway.save(pagamento);

        assertNotNull(result);
        assertEquals(pagamento.getId(), result.getId());
        verify(repository).save(any(PagamentoDto.class));
    }

    @Test
    void findById_ShouldReturnPagamento() {
        when(repository.findById("1")).thenReturn(pagamentoDto);

        Pagamento result = gateway.findById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(repository).findById("1");
    }

    @Test
    void findAll_ShouldReturnAllPagamentos() {
        List<PagamentoDto> pagamentosDto = Arrays.asList(pagamentoDto);
        when(repository.findAll()).thenReturn(pagamentosDto);

        List<Pagamento> result = gateway.findAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        gateway.deleteById("1");

        verify(repository).deleteById("1");
    }

    @Test
    void updateStatus_ShouldCallRepositoryUpdateStatus() {
        gateway.updateStatus(123L, PagamentoStatus.CONFIRMADO);

        verify(repository).updateStatus(123L, PagamentoStatus.CONFIRMADO);
    }

    @Test
    void findByPedidoId_ShouldReturnPagamento() {
        when(repository.findByPedidoId(123L)).thenReturn(pagamentoDto);

        Pagamento result = gateway.findByPedidoId(123L);

        assertNotNull(result);
        assertEquals(123L, result.getPedidoId());
        verify(repository).findByPedidoId(123L);
    }
}