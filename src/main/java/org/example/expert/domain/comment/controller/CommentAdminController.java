package org.example.expert.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.annotation.AdminLog;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @AdminLog
    @DeleteMapping("/admin/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") long commentId) {
        commentAdminService.deleteComment(commentId);
    }
}
