package mi.edificio.server

class UrlMappings {

    static mappings = {
        "/buildings/by-code/$code"(controller: 'building', action: 'buildingByCode', method: "GET")

        "/buildings"(resources: 'building') {
            "/buildingUsers"(resources: 'buildingUser')
            "/posts"(resources: 'post') {
                "/comments"(resources: 'comment')
            }
        }

//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

        "/"(view: '/index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
