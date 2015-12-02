/**
 * Created by pc8 on 27.11.15.
 */
/**
 * Created by pc8 on 27.11.15.
 */
app.controller('MainController', ['$scope', '$http', function ($scope, $http) {


    $scope.index = {
        value: 0
    };
    $scope.getDesc = function () {
        $http.get('http://10.251.0.10:8080/book', {params: {bookIndex: $scope.index.value}})
            .success(function (data) {
                alert("bookDesc " + $scope.index.value);
                $scope.book = data;

            })
            .error(function (err) {
                return err;
            });
    }

    $scope.func = function () {
        $scope.increment();
        $scope.getDesc();

    }

    $scope.increment = function () {
        $scope.index.value++;


    }

    $scope.decrement = function () {
        $scope.index.value--;
        $scope.getDesc();
    }

    $scope.init = function () {
        $scope.getDesc();

    }


}]);

app.controller('CarouselCtrl', ['$scope', '$http', function ($scope, $http) {

    // initial image index
    $scope._Index = 0;

    // Set of Photos
    $scope.photos = [];


    $scope.initImage = function () {
        $scope.addImage();
    }

    $scope.addImage = function () {

        for (var i = 0; i < 10; i++) {
            $scope.photos.push({src: '/home/pc8/Документы/380/' + ($scope._Index + 1 + i) + '.jpg'});

        }

    }


    // if a current image is the same as requested image
    $scope.isActive = function (index) {
        return $scope._Index === index;
    };

    // show prev image
    $scope.showPrev = function () {
        $scope._Index = ($scope._Index > 0) ? --$scope._Index : $scope.photos.length - 1;
    };

    // show next image
    $scope.showNext = function () {
        if ($scope._Index % 10 === 5) {
            $scope.addImage();
            $scope.download();
        }
        alert("next");
        $scope._Index = ($scope._Index < $scope.photos.length - 1) ? ++$scope._Index : 0;
    };

    // show a certain image
    $scope.showPhoto = function (index) {
        $scope._Index = index;
    };

    $scope.download = function () {
        $http.get('http://10.251.0.10:8080/download')
            .success(function (data) {
                alert("download");

            })
            .error(function (err) {
                return err;
            });
    }


}]);

app.controller('SearchCtrl', ['$scope', '$http', '$sce', function ($scope, $http, $sce) {

    $scope.proccesing = false;

    $scope.arrIrbis;
    $scope.tmp;
    $scope.indexIrbis = 0;

    $scope.search = function () {
        alert($scope.keyword);
        $scope.getIrbisDesc();
    };

    $scope.getIrbisDesc = function () {
        $scope.proccesing = true;
        $http.get('http://10.251.0.10:8080/search', {params: {searchExpr: $scope.keyword}})

            .success(function (data) {
                alert('search');
                $scope.arrIrbis = data;
                for (var i = 0; i < $scope.arrIrbis.length; i++) {
                    $scope.arrIrbis[i].html = $sce.trustAsHtml($scope.arrIrbis[i].html);
                }
                $scope.proccesing = false;

            })
            .error(function (err) {
                return err;
            });
    };
    $scope.nextIrbis = function () {
        $scope.indexIrbis = ($scope.indexIrbis > 0) ? --$scope.indexIrbis : $scope.arrIrbis.length - 1;
    }
    $scope.prevIrbis = function () {
        $scope.indexIrbis = ($scope.indexIrbis < $scope.arrIrbis.length - 1) ? ++$scope.indexIrbis : 0;
    }
}]);


app.controller('BindCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.bind = function () {


        $http.get('http://10.251.0.10:8080/bindBook', {
            params: {
                bookId: $scope.book.bookId,
                mfn: $scope.arrIrbis[$scope.indexIrbis].mfn
            }
        }).success(function (data) {
            console.log("bind, bookId =  " + $scope.book.bookId + "and mfn = " + $scope.arrIrbis[$scope.indexIrbis].mfn);

        })
            .error(function (err) {
                return err;
            });
    }

}]);


app.filter('trust', [
    '$sce',
    function ($sce) {
        return function (value, type) {
            // Defaults to treating trusted text as `html`
            return $sce.trustAs(type || 'html', text);
        }
    }
]);