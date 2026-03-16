package com.example.hb01;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/books", "/addBook", "/editBook", "/updateBook", "/deleteBook"})
public class BookServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/editBook":
                showEditForm(request, response);
                break;
            case "/books":
            default:
                listAndSearchBooks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String action = request.getServletPath();

        switch (action)
        {
            case "/addBook":
                addBook(request, response);
                break;
            case "/updateBook":
                updateBook(request, response);
                break;
            case "/deleteBook":
                deleteBook(request, response);
                break;
        }
    }

    private void listAndSearchBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String yearParam = request.getParameter("year");
        String genre = request.getParameter("genre");
        String pageCountParam = request.getParameter("pageCount");
        String descriptionKeyword = request.getParameter("descriptionKeyword");

        Integer year = (yearParam != null && !yearParam.isEmpty()) ? Integer.parseInt(yearParam) : null;
        Integer pageCount = (pageCountParam != null && !pageCountParam.isEmpty()) ? Integer.parseInt(pageCountParam) : null;

        List<Book> bookList = bookDao.searchBooks(title, author, year, genre, pageCount, descriptionKeyword);

        request.setAttribute("bookList", bookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDao.getBookById(id);
        request.setAttribute("book", existingBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-book.jsp");
        dispatcher.forward(request, response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Book newBook = new Book();
        newBook.setTitle(request.getParameter("title"));
        newBook.setAuthorFullname(request.getParameter("author"));
        newBook.setPublicationYear(Integer.parseInt(request.getParameter("year")));
        newBook.setGenre(request.getParameter("genre"));

        String pageCountStr = request.getParameter("pageCount");
        if (pageCountStr != null && !pageCountStr.isEmpty())
        {
            newBook.setPageCount(Integer.parseInt(pageCountStr));
        }
        newBook.setShortDescription(request.getParameter("shortDescription"));

        bookDao.saveBook(newBook);
        response.sendRedirect("books");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDao.getBookById(id);

        book.setTitle(request.getParameter("title"));
        book.setAuthorFullname(request.getParameter("author"));
        book.setPublicationYear(Integer.parseInt(request.getParameter("year")));
        book.setGenre(request.getParameter("genre"));
        String pageCountStr = request.getParameter("pageCount");
        if (pageCountStr != null && !pageCountStr.isEmpty())
        {
            book.setPageCount(Integer.parseInt(pageCountStr));
        }
        book.setShortDescription(request.getParameter("shortDescription"));

        bookDao.updateBook(book);
        response.sendRedirect("books");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDao.deleteBook(id);
        response.sendRedirect("books");
    }
}