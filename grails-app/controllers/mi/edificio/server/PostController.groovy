package mi.edificio.server

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PostController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def buildingId = params.buildingId

        respond Post.where {
            building.id == buildingId
        }.list(params), model:[postCount: Post.count()]
    }

    def show(Post post) {
        respond post
    }

    @Transactional
    def save(Post post) {
        if (post == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        def building = Building.get(params.buildingId)

        if (building == null) {
            render status: NOT_FOUND
            return
        }

        building.addToPosts(post)

        if (building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond building.errors, view:'create'
            return
        }

        building.save flush:true

        respond post, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Post post) {
        if (post == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (post.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond post.errors, view:'edit'
            return
        }

        post.save flush:true

        respond post, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Post post) {

        if (post == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        post.delete flush:true

        render status: NO_CONTENT
    }
}
