package lms;

import java.io.*;
        import java.util.ArrayList;
import java.util.List;

class LMS {
    private List<Book> books = new ArrayList<>();
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public boolean borrowBook(Book book, Student student) {
        if (books.contains(book)) {
            borrowRecords.add(new BorrowRecord(book, student));
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getBook().equals(book)) {
                borrowRecords.remove(record);
                return true;
            }
        }
        return false;
    }

    public void saveState(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
            oos.writeObject(borrowRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadState(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            books = (List<Book>) ois.readObject();
            borrowRecords = (List<BorrowRecord>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}