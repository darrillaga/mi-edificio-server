package mi.edificio.server

class Post {

    static constraints = {
    }

    static hasMany = [comments: Comment]
    static belongsTo = [building: Building, buildingUser: BuildingUser]

    Long id
    String text
    Integer commentsCount
    Date lastCommentDate
}
