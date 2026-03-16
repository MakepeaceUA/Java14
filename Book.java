package com.example.hb01;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author_fullname", nullable = false)
    private String authorFullname;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "genre")
    private String genre;

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "short_description")
    private String shortDescription;

    public Book() {}

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthorFullname()
    {
        return authorFullname;
    }

    public void setAuthorFullname(String authorFullname)
    {
        this.authorFullname = authorFullname;
    }

    public int getPublicationYear()
    {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear)
    {
        this.publicationYear = publicationYear;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public String getShortDescription()
    {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }
}