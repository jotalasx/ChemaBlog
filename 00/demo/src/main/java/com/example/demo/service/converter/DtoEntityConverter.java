package com.example.demo.service.converter;

import com.example.demo.controller.dto.CommentaryDTO;
import com.example.demo.repository.entity.CommentaryEntity;

public class DtoEntityConverter {

    public CommentaryEntity mapDTOtoEntity(CommentaryDTO commentaryDTO){
        CommentaryEntity commentaryEntity = null;

        commentaryEntity = new CommentaryEntity()
                .setUserName(commentaryDTO.getUserName())
                .setCommentBody(commentaryDTO.getCommentBody())
                .setPublishDate(commentaryDTO.getPublishDate());

        return commentaryEntity;
    }

    public CommentaryDTO mapEntityToDto(CommentaryEntity commentaryEntity){
        CommentaryDTO commentaryDTO = null;

        commentaryDTO = new CommentaryDTO()
                .setUserName(commentaryEntity.getUserName())
                .setCommentBody(commentaryEntity.getCommentBody())
                .setPublishDate(commentaryEntity.getPublishDate());

        return commentaryDTO;
    }
}
