package Model;

public class Books {
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private int bookPageCount;
    private int bookVolume;

    public Books() {
    }

    public Books(String bookTitle, String bookAuthor, int bookPageCount, int bookVolume) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPageCount = bookPageCount;
        this.bookVolume = bookVolume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPageCount() {
        return bookPageCount;
    }

    public void setBookPageCount(int bookPageCount) {
        this.bookPageCount = bookPageCount;
    }

    public int getBookVolume() {
        return bookVolume;
    }

    public void setBookVolume(int bookVolume) {
        this.bookVolume = bookVolume;
    }
    @Override
    public String toString() {
        return "\nBook Details: " +
                "ID=" + id +
                ", Title='" + bookTitle + '\'' +
                ", Author='" + bookAuthor + '\'' +
                ", Page Count=" + bookPageCount +
                ", Volume=" + bookVolume;
    }

}
