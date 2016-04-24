describe("mi.edificio.server.building module", function() {
    var $httpBackend;

    beforeEach(angular.mock.module("mi.edificio.server.building", function() {
    }));

    beforeEach(angular.mock.inject(function(_$httpBackend_) {
        $httpBackend = _$httpBackend_;
    }));

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    describe("Building domain", function() {

        var Building;

        beforeEach(angular.mock.inject(function(_Building_) {
            Building = _Building_;
        }));

        it("should be tested", function() {
            expect(true).toEqual(false);
        });

    });

});
