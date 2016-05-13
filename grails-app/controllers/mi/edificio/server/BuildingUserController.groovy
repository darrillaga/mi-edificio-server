package mi.edificio.server

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingUserController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def buildingId = params.buildingId;

        respond BuildingUser.where {
            building.id == buildingId
        }.list(params), model:[buildingUserCount: BuildingUser.count()]
    }

    def show(BuildingUser buildingUser) {
        respond buildingUser
    }

    @Transactional
    def save(BuildingUser buildingUser) {
        if (buildingUser == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        def building = Building.get(params.buildingId)

        if (building == null) {
            render status: NOT_FOUND
            return
        }

        buildingUser.buildingCreator = building.buildingUsers.count == 0

        building.addToBuildingUsers(buildingUser)

        if (building.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond building.errors, view:'create'
            return
        }

        building.save flush:true

        respond buildingUser, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(BuildingUser buildingUser) {
        if (buildingUser == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (buildingUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingUser.errors, view:'edit'
            return
        }

        buildingUser.save flush:true

        respond buildingUser, [status: OK, view:"show"]
    }

    @Transactional
    def delete(BuildingUser buildingUser) {

        if (buildingUser == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        buildingUser.delete flush:true

        render status: NO_CONTENT
    }
}
