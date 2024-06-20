package testes;

import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {
    private IClienteDAO clienteDAO;

    @Test
    public void cadastrarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("102030");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue("Falha ao cadastrar o cliente", countCad == 1);

        Cliente clienteDB = clienteDAO.buscar("102030");
        assertNotNull("Cliente não encontrado no banco de dados", clienteDB);
        assertEquals("CPF não corresponde", cliente.getCpf(), clienteDB.getCpf());
        assertEquals("Nome não corresponde", cliente.getNome(), clienteDB.getNome());

        Integer countDel = clienteDAO.excluir(clienteDB);
        assertTrue("Falha ao excluir o cliente", countDel == 1);
    }

    @Test
    public void buscarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("102030");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue("Falha ao cadastrar o cliente", countCad == 1);

        Cliente clienteDB = clienteDAO.buscar("102030");
        assertNotNull(clienteDB);
        assertEquals(cliente.getCpf(), clienteDB.getCpf());
        assertEquals(cliente.getNome(), clienteDB.getNome());

        Integer countDel = clienteDAO.excluir(clienteDB);
        assertTrue(countDel == 1);
    }

    @Test
    public void excluirTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("102030");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue("Falha ao cadastrar o cliente", countCad == 1);

        Cliente clienteDB = clienteDAO.buscar("102030");
        assertNotNull("Cliente não encontrado no banco de dados", clienteDB);
        assertEquals("CPF não corresponde", cliente.getCpf(), clienteDB.getCpf());
        assertEquals("Nome não corresponde", cliente.getNome(), clienteDB.getNome());

        Integer countDel = clienteDAO.excluir(clienteDB);
        assertTrue("Falha ao excluir o cliente", countDel == 1);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("102030");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clientes = new Cliente();
        clientes.setCpf("20");
        clientes.setNome("Teste");
        Integer countCad2 = clienteDAO.cadastrar(clientes);
        assertTrue(countCad2 == 1);

        List<Cliente> list = clienteDAO.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Cliente cli : list) {
            clienteDAO.excluir(cli);
            countDel++;
        }
        assertEquals(list.size(), countDel);

        list = clienteDAO.buscarTodos();
        assertEquals(list.size(), 0);

    }

    @Test
    public void atualizarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCpf("10");
        cliente.setNome("caio");
        Integer countCad = clienteDAO.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCpf(), clienteBD.getCpf());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCpf("20");
        clienteBD.setNome("Outro nome");
        Integer countUpdate = clienteDAO.atualizar(clienteBD);
        assertTrue(countUpdate == 1);

        Cliente clienteBD1 = clienteDAO.buscar("10");
        assertNull(clienteBD1);

        Cliente clienteBD2 = clienteDAO.buscar("20");
        assertNotNull(clienteBD2);
        assertEquals(clienteBD.getId(), clienteBD2.getId());
        assertEquals(clienteBD.getCpf(), clienteBD2.getCpf());
        assertEquals(clienteBD.getNome(), clienteBD2.getNome());

        List<Cliente> list = clienteDAO.buscarTodos();
        for (Cliente cli : list) {
            clienteDAO.excluir(cli);
        }
    }






}
