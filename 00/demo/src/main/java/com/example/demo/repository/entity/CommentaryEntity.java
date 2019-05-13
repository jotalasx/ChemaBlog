package com.example.demo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "comments")
@Entity
@Accessors(chain = true)
public class CommentaryEntity {

    @Id
    @GeneratedValue
    private Long commentID;
    private String userName;
    private String commentBody;
    private String publishDate;

}
