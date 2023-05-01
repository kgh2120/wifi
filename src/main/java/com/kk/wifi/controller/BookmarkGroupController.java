package com.kk.wifi.controller;

import static com.kk.wifi.utils.BodyUtils.*;

import com.kk.wifi.dto.BookmarkGroupCreateRequest;
import com.kk.wifi.dto.BookmarkGroupUpdateRequest;
import com.kk.wifi.service.BookmarkGroupService;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "bookmarkGroupController", value = "/bookmark-group")
public class BookmarkGroupController extends HttpServlet {

    private final BookmarkGroupService bookmarkGroupService = BookmarkGroupService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        sendBody(resp,bookmarkGroupService.retrieveBookmarkGroupList());;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BookmarkGroupCreateRequest requestDto = (BookmarkGroupCreateRequest) readBody(
                req, BookmarkGroupCreateRequest.class);
        bookmarkGroupService.createBookmarkGroup(requestDto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BookmarkGroupUpdateRequest requestDto = (BookmarkGroupUpdateRequest) readBody(
                req, BookmarkGroupUpdateRequest.class);
        bookmarkGroupService.updateBookmarkGroup(requestDto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookmarkGroupService.deleteBookmarkGroup(id);

    }
}
