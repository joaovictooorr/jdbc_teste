package testes;

import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Produto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {
    private IProdutoDAO produtoDAO;

    @Test
    public void cadastrarTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setNomeProduto("Notebook");
        produto.setPreco(1500.00);
        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue("Falha ao cadastrar o produto", countCad == 1);

        Produto produtoDB = produtoDAO.buscar(produto.getNomeProduto());
        assertNotNull("Produto não encontrado no banco de dados", produtoDB);
        assertEquals("Nome do produto não corresponde", produto.getNomeProduto(), produtoDB.getNomeProduto());
        assertEquals("Preço do produto não corresponde", produto.getPreco(), produtoDB.getPreco(), 0.01);

        Integer countDel = produtoDAO.excluir(produtoDB);
        assertTrue("Falha ao excluir o produto", countDel == 1);
    }
    @Test
    public void buscarTest() throws Exception {
        produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNomeProduto("Notebook");
        produto.setPreco(1500.00);
        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue("Falha ao cadastrar o produto", countCad == 1);

        Produto produtoDB = produtoDAO.buscar("Notebook");
        assertNotNull("Produto não encontrado no banco de dados", produtoDB);
        assertEquals("Nome do produto não corresponde", produto.getNomeProduto(), produtoDB.getNomeProduto());

        Integer countDel = produtoDAO.excluir(produtoDB);
        assertTrue("Falha ao excluir o produto", countDel == 1);
    }

    @Test
    public void excluirTest() throws Exception {
        produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNomeProduto("Smartphone");
        produto.setPreco(2000.00);

        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue("Falha ao cadastrar o produto", countCad == 1);

        Produto produtoDB = produtoDAO.buscar("Smartphone");
        assertNotNull("Produto não encontrado no banco de dados", produtoDB);

        Integer countDel = produtoDAO.excluir(produtoDB);
        assertTrue("Falha ao excluir o produto", countDel == 1);

        Produto produtoExcluido = produtoDAO.buscar("Smartphone");
        assertNull("Produto não foi corretamente excluído", produtoExcluido);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        produtoDAO = new ProdutoDAO();
        List<Produto> produtos = new ArrayList<>();

        Produto produto1 = new Produto();
        produto1.setNomeProduto("Teclado");
        produto1.setPreco(100.00);
        produtoDAO.cadastrar(produto1);

        Produto produto2 = new Produto();
        produto2.setNomeProduto("Mouse");
        produto2.setPreco(50.00);
        produtoDAO.cadastrar(produto2);

        produtos = produtoDAO.buscarTodos();

        assertFalse("Nenhum produto encontrado", produtos.isEmpty());

        assertTrue("Número incorreto de produtos recuperados", produtos.size() >= 2);
    }

    @Test
    public void atualizarTest() throws Exception {
        produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNomeProduto("Fone de Ouvido");
        produto.setPreco(300.00);

        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue("Falha ao cadastrar o produto", countCad == 1);

        Produto produtoDB = produtoDAO.buscar("Fone de Ouvido");
        assertNotNull("Produto não encontrado no banco de dados", produtoDB);

        produtoDB.setPreco(250.00);
        Integer countUpd = produtoDAO.atualizar(produtoDB);
        assertTrue("Falha ao atualizar o produto", countUpd == 1);

        Produto produtoAtualizado = produtoDAO.buscar("Fone de Ouvido");
        assertEquals("Preço do produto não foi atualizado corretamente", 250.00, produtoAtualizado.getPreco(), 0.001);

        Integer countDel = produtoDAO.excluir(produtoAtualizado);
        assertTrue("Falha ao excluir o produto após atualização", countDel == 1);
    }


}
