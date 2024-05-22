package lms;

import java.io.Serializable;

class BorrowRecord implements Serializable {
    private Book book;
    private Student student;

    public BorrowRecord(Book book, Student student) {
        this.book = book;
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }
}