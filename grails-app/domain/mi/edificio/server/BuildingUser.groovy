package mi.edificio.server

class BuildingUser {

    static constraints = {
    }

    static hasMany = [posts: Post, comments: Comment]
    static belongsTo = [building: Building]

    Long id
    String name
    String apartment
    String roleDescription
    Boolean buildingCreator
}
