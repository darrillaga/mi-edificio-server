package mi.edificio.server

class Comment {

    static constraints = {
    }

    static belongsTo = [building: Building, buildingUser: BuildingUser, post: Post]

    Long id
    String text
}
