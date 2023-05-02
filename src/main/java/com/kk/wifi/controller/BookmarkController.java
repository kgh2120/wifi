package com.kk.wifi.controller;

import static com.kk.wifi.utils.BodyUtils.*;

import com.kk.wifi.dto.BookmarkCreateRequest;
import com.kk.wifi.dto.BookmarkRetrieveResponse;
import com.kk.wifi.service.BookmarkService;
import com.kk.wifi.utils.BodyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "bookmarkController", value = "/bookmark")
public class BookmarkController extends HttpServlet {

    private final BookmarkService bookmarkService = BookmarkService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            sendBody(resp,bookmarkService.retrieveBookmarks());

        } else {
            BookmarkRetrieveResponse response = bookmarkService.retrieveSingleBookmark(
                    Integer.parseInt(id));
            sendBody(resp,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BookmarkCreateRequest bookmarkCreateRequest = readBody(req,
                BookmarkCreateRequest.class);
        bookmarkService.createBookmark(bookmarkCreateRequest);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null){

            return;
        }
        bookmarkService.deleteBookmark(Integer.parseInt(id));
    }
}
