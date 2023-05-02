package com.kk.wifi.service;

import com.kk.wifi.dao.BookmarkDao;
import com.kk.wifi.dto.BookmarkCreateRequest;
import com.kk.wifi.dto.BookmarkRetrieveResponse;
import java.util.List;

public class BookmarkService {

    private final BookmarkDao bookmarkDao = BookmarkDao.getInstance();
    private static final BookmarkService instance = new BookmarkService();

    public static BookmarkService getInstance() {
        return instance;
    }

    public BookmarkRetrieveResponse retrieveSingleBookmark(int id) {
        return bookmarkDao.retrieveSingleBookmark(id);
    }

    public List<BookmarkRetrieveResponse> retrieveBookmarks() {
        return bookmarkDao.retrieveBookmarks();
    }

    public void createBookmark(BookmarkCreateRequest bookmarkCreateRequest) {
        bookmarkDao.createBookmark(bookmarkCreateRequest);
    }

    public void deleteBookmark(int id) {
        bookmarkDao.deleteBookmark(id);
    }


}
