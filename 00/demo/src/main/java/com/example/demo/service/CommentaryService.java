package com.example.demo.service;

import com.example.demo.controller.dto.CommentaryDTO;

public interface CommentaryService {

    CommentaryDTO newComment(CommentaryDTO comment);

    CommentaryDTO searchCommentByID(Long id);

    CommentaryDTO updateCommentByID(CommentaryDTO comment, Long id);

    void deleteComment(Long id);





}
