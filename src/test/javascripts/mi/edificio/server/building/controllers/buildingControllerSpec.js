describe("mi.edificio.server.building module", function() {
    var scope;

    beforeEach(angular.mock.module("mi.edificio.server.building", function() {
    }));

    beforeEach(angular.mock.inject(function($rootScope) {
        scope = $rootScope.$new();
    }));

    describe("BuildingController", function() {

        var ctrl;

        beforeEach(angular.mock.inject(function($controller) {
            ctrl = $controller("BuildingController", {});
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });

    });

});
