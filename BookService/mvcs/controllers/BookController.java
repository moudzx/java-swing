package mvcs.controllers;

import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingWorker;
import mvcs.services.BookService;
import mvcs.models.Book;

public class BookController {

    private BookService service;

    public BookController() {
        this.service = new BookService();
    }

    public void create(Book book, Callback callback) {
        new CreateWorker(book, callback).execute();
    }

    public void findAll(Callback callback) {
        new FindAllWorker(callback).execute();
    }

    public void findByCode(String code, Callback callback) {
        new FindByCodeWorker(code, callback).execute();
    }
    
    public void update(Book book, Callback callback) {
        new UpdateWorker(book, callback).execute();
    }

    private class CreateWorker extends SwingWorker<Void, Void> {

        private Book book;
        private Callback callback;
        private Exception exception;

        public CreateWorker(Book book, Callback callback) {
            this.book = book;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground() throws Exception {
            try {
                service.create(book);
            } catch (SQLException ex) {
                exception = ex;
            }
            return null;
        }

        @Override
        protected void done() {
            if (exception != null) {
                callback.onError("Error saving book: " + exception.getMessage());
            } else {
                callback.onSuccess("Book saved successfully!");
            }
        }

    }

    private class FindByCodeWorker extends SwingWorker<Book, Void> {

        private String code;
        private Callback callback;
        private Exception exception;

        public FindByCodeWorker(String code, Callback callback) {
            this.code = code;
            this.callback = callback;
        }

        @Override
        protected Book doInBackground() throws Exception {
            try {
                return service.findByCode(code);
            } catch (SQLException ex) {
                exception = ex;
                return null;
            }
        }

        @Override
        protected void done() {
            try {
                Book book = get();
                if (book != null) {
                    callback.onFindByCode(book);
                } else if (exception != null) {
                    callback.onError("Error Loading Book: " + exception.getMessage());
                } else {
                    callback.onError("Book not found");
                }
            } catch (Exception ex) {
                callback.onError("Error Loading Book: " + ex.getMessage());
            }
        }

    }

    private class FindAllWorker extends SwingWorker<List<Book>, Void> {

        private Callback callback;
        private Exception exception;

        public FindAllWorker(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Book> doInBackground() throws Exception {
            try {
                return service.findAll();
            } catch (SQLException e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void done() {
            try {
                List<Book> list = get();
                if (list != null) {
                    callback.onFindAll(list);
                    callback.onSuccess("Loading Successful");
                } else {
                    callback.onError("Error loading books: " + exception.getMessage());
                }
            } catch (Exception ex) {
                callback.onError("Error loading books: " + ex.getMessage());
            }
        }

    }

    private class UpdateWorker extends SwingWorker<Void, Void> {

        private Book book;
        private Callback callback;
        private Exception exception;

        public UpdateWorker(Book book, Callback callback) {
            this.book = book;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground() throws Exception {
            try {
                service.update(book);
            } catch (SQLException ex) {
                exception = ex;
            }
            return null;
        }

        @Override
        protected void done() {
            if (exception != null) {
                callback.onError("Error saving book: " + exception.getMessage());
            } else {
                callback.onSuccess("Book saved successfully!");
            }
        }

    }

    public interface Callback {

        void onSuccess(String message);

        void onError(String message);

        void onFindAll(List<Book> list);

        void onFindByCode(Book book);

    }
}
