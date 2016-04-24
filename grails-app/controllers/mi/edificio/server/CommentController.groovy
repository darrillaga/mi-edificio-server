package mi.edificio.server

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommentController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def postId = params.postId

        respond Comment.where{
            post.id == postId
        }.list(params), model:[commentCount: Comment.count()]
    }

    def show(Comment comment) {
        respond comment
    }

    @Transactional
    def save(Comment comment) {
        if (comment == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        def post = Post.get(params.postId)

        if (post == null) {
            render status: NOT_FOUND
            return
        }

        comment.building = post.building
        post.addToComments(comment)

        if (post.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond post.errors, view:'create'
            return
        }

        post.save flush:true

        respond comment, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Comment comment) {
        if (comment == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (comment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comment.errors, view:'edit'
            return
        }

        comment.save flush:true

        respond comment, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Comment comment) {

        if (comment == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        comment.delete flush:true

        render status: NO_CONTENT
    }
}
