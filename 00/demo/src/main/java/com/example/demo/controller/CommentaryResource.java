package com.example.demo.controller;

import com.example.demo.controller.dto.CommentaryDTO;
import com.example.demo.service.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
public class CommentaryResource {

    @Autowired
    CommentaryService commentaryService;

    private boolean validateComment(CommentaryDTO commentaryDTO){
        return (commentaryDTO.getUserName().isEmpty() || commentaryDTO.getCommentBody().isEmpty()) ? false : true;
    }

    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public ResponseEntity<CommentaryDTO> newComments(@RequestBody CommentaryDTO commentaryDTO){
        ResponseEntity<CommentaryDTO> response = null;

        if(validateComment(commentaryDTO)){
            CommentaryDTO comment = commentaryService.newComment(commentaryDTO);
            response = ResponseEntity.ok(commentaryDTO);
        } else {
            response = ResponseEntity.badRequest().body(commentaryDTO);
        }

        return response;

    }

    @RequestMapping(path = "/comments/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommentaryDTO> getCommentByID(@PathVariable(name = "id") Long id){
        ResponseEntity<CommentaryDTO> response = null;

        CommentaryDTO commentaryDTO = commentaryService.searchCommentByID(id);
        if (commentaryDTO != null){
            response = ResponseEntity.ok(commentaryDTO);
        } else {
            response = ResponseEntity.notFound().build();
        }


        return response;

    }

    @RequestMapping(path = "/comments/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<CommentaryDTO> updateCommentByID(@PathVariable(name = "id") Long id, @RequestBody CommentaryDTO commentaryDTO){
        ResponseEntity<CommentaryDTO> responseDTO = null;

        CommentaryDTO updatedComment = commentaryService.updateCommentByID(commentaryDTO, id);
        responseDTO = ResponseEntity.ok(updatedComment);


        return responseDTO;
    }

    @RequestMapping(path = "/comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCommentByID(@PathVariable(name = "id") Long id){
        commentaryService.deleteComment(id);
        return ResponseEntity.accepted().build();
    }
}
