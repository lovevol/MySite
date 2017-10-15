package model;

/**
 * Created by lh
 * on 2017/9/9.
 * @author lh
 */
public class Ebook {
    private int idEbook;
    private String bookName;
    private String description;
    private String path;

    public int getIdEbook() {
        return idEbook;
    }

    public void setIdEbook(int idEbook) {
        this.idEbook = idEbook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Ebook{" +
                "idEbook=" + idEbook +
                ", bookName='" + bookName + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ebook ebook = (Ebook) o;

        if (idEbook != ebook.idEbook) {
            return false;
        }
        if (bookName != null ? !bookName.equals(ebook.bookName) : ebook.bookName != null) {
            return false;
        }
        if (description != null ? !description.equals(ebook.description) : ebook.description != null) {
            return false;
        }
        return path != null ? path.equals(ebook.path) : ebook.path == null;
    }

    @Override
    public int hashCode() {
        int result = idEbook;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
