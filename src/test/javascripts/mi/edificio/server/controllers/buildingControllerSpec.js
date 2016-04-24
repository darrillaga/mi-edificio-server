describe("mi.edificio.server module", function() {
    var scope;

    beforeEach(angular.mock.module("mi.edificio.server", function() {
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
