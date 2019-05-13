package com.example.demo.service.impl;

import com.example.demo.controller.dto.CommentaryDTO;
import com.example.demo.repository.CommentaryRepo;
import com.example.demo.repository.entity.CommentaryEntity;
import com.example.demo.service.CommentaryService;
import com.example.demo.service.converter.DtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentaryService {

    @Autowired
    CommentaryRepo commentaryRepo;

    DtoEntityConverter mapper = new DtoEntityConverter();

    @Override
    public CommentaryDTO newComment(CommentaryDTO comment) {
        CommentaryDTO commentDTO = null;

        CommentaryEntity commentaryEntity = commentaryRepo.save(mapper.mapDTOtoEntity(comment));
        commentDTO = mapper.mapEntityToDto(commentaryEntity);

        return commentDTO;
    }

    @Override
    public CommentaryDTO searchCommentByID(Long id) {
        CommentaryDTO commentDTO = null;

        Optional<CommentaryEntity> optionalCommentaryEntity = commentaryRepo.findById(id);
        if(optionalCommentaryEntity.isPresent()){
            commentDTO = mapper.mapEntityToDto(optionalCommentaryEntity.get());
        }

        return commentDTO;
    }

    @Override
    public CommentaryDTO updateCommentByID(CommentaryDTO comment, Long id) {
        CommentaryDTO commentDTO = null;

        Optional<CommentaryEntity> optionalCommentaryEntity = commentaryRepo.findById(id);
        if(commentDTO.getUserName() != null){

            optionalCommentaryEntity.get().setUserName(commentDTO.getUserName());

        }
        if(commentDTO.getCommentBody() != null){

            optionalCommentaryEntity.get().setCommentBody(commentDTO.getCommentBody());

        }
        if(commentDTO.getPublishDate() != null){

            optionalCommentaryEntity.get().setPublishDate(commentDTO.getPublishDate());

        }

        CommentaryEntity commentSaved = commentaryRepo.save(optionalCommentaryEntity.get());
        commentDTO = mapper.mapEntityToDto(commentSaved);
        return commentDTO;
    }


    @Override
    public void deleteComment(Long id) {
        commentaryRepo.deleteById(id);

    }

    private boolean validateMandatoryFields(CommentaryDTO commentaryDTO){
        return !StringUtils.isEmpty(commentaryDTO.getUserName()) &&
                !StringUtils.isEmpty(commentaryDTO.getCommentBody()) &&
                !StringUtils.isEmpty(commentaryDTO.getPublishDate());
    }
}
