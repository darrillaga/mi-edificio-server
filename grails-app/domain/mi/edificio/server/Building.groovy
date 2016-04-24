package mi.edificio.server

class Building {

    static constraints = {
    }

    static hasMany = [buildingUsers: BuildingUser, posts: Post]

    Long id
    String code
    String name
    String address
    String contactEmail
}
