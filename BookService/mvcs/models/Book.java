package mvcs.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String code;
    private String title;
    private LocalDate dop;

    public Book() {
    }
     
    public Book(String code, String title, LocalDate dop) {
        this.code = code;
        this.title = title;
        this.dop = dop;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDop() {
        return dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return title;
    }            
}
