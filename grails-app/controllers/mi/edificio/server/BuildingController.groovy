package mi.edificio.server

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Building.list(params), model:[buildingCount: Building.count()]
    }

    def show(Building building) {
        respond building
    }

    @Transactional
    def save(Building building) {
        if (building == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond building.errors, view:'create'
            return
        }

        building.save flush:true

        respond building, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Building building) {
        if (building == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond building.errors, view:'edit'
            return
        }

        building.save flush:true

        respond building, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Building building) {

        if (building == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        building.delete flush:true

        render status: NO_CONTENT
    }
}
