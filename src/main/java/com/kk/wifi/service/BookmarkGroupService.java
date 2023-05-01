package com.kk.wifi.service;

import com.kk.wifi.dao.BookmarkGroupDao;
import com.kk.wifi.dto.BookmarkGroupCreateRequest;
import com.kk.wifi.dto.BookmarkGroupListResponse;
import com.kk.wifi.dto.BookmarkGroupUpdateRequest;
import java.util.List;

public class BookmarkGroupService {

    private final BookmarkGroupDao bookmarkGroupDao = BookmarkGroupDao.getInstance();
    private static final BookmarkGroupService instance = new BookmarkGroupService();

    public static BookmarkGroupService getInstance(){
        return instance;
    }

    public List<BookmarkGroupListResponse> retrieveBookmarkGroupList(){
        return bookmarkGroupDao.retrieveBookmarkGroupList();
    }

    public void createBookmarkGroup(BookmarkGroupCreateRequest request){
        bookmarkGroupDao.createBookmarkGroup(request.getName(), request.getOrders());
    }

    public void updateBookmarkGroup(BookmarkGroupUpdateRequest request){
        bookmarkGroupDao.updateBookmarkGroup(request.getId(), request.getName(), request.getOrders());

    }

    public void deleteBookmarkGroup(int id){
        bookmarkGroupDao.deleteBookmarkGroup(id);
    }


}
