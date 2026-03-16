package com.example.hb01;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDao {


    public void saveBook(Book book)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateBook(Book book)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            session.merge(book);
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteBook(int id)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null)
            {
                session.remove(book);
            }
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public Book getBookById(int id)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            return session.get(Book.class, id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> getAllBooks()
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public List<Book> searchBooks(String title, String author, Integer year, String genre, Integer pageCount, String descriptionKeyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            StringBuilder hql = new StringBuilder("FROM Book WHERE 1=1");
            Map<String, Object> parameters = new HashMap<>();

            if (title != null && !title.trim().isEmpty()) {
                hql.append(" AND lower(title) LIKE lower(:title)");
                parameters.put("title", "%" + title + "%");
            }
            if (author != null && !author.trim().isEmpty())
            {
                hql.append(" AND lower(authorFullname) LIKE lower(:author)");
                parameters.put("author", "%" + author + "%");
            }
            if (year != null && year > 0)
            {
                hql.append(" AND publicationYear = :year");
                parameters.put("year", year);
            }
            if (genre != null && !genre.trim().isEmpty())
            {
                hql.append(" AND lower(genre) LIKE lower(:genre)");
                parameters.put("genre", "%" + genre + "%");
            }
            if (pageCount != null && pageCount > 0)
            {
                hql.append(" AND pageCount = :pageCount");
                parameters.put("pageCount", pageCount);
            }
            if (descriptionKeyword != null && !descriptionKeyword.trim().isEmpty())
            {
                hql.append(" AND lower(shortDescription) LIKE lower(:descriptionKeyword)");
                parameters.put("descriptionKeyword", "%" + descriptionKeyword + "%");
            }

            Query<Book> query = session.createQuery(hql.toString(), Book.class);

            for (Map.Entry<String, Object> entry : parameters.entrySet())
            {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            return query.list();
        }
    }
}
