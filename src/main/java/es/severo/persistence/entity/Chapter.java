package es.severo.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name =  "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tittle;

    private int pages;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Chapter() {
    }

    public Chapter(String tittle, int pages) {
        this.tittle = tittle;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", pages=" + pages +
                ", book=" + book +
                '}';
    }
}
