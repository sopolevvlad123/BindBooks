/**
 * Created by pc8 on 27.11.15.
 */
/**
 * Created by pc8 on 27.11.15.
 */
app.controller('MainController', ['$scope', '$http', function ($scope, $http) {
    $scope._Index = 0;
    $scope._maxIndex = 0;
    // Set of Photos
    $scope.photos = [];


    $scope.index = {
        value: 0
    };
    $scope.getDesc = function () {
        $http.get('http://localhost:8080/book', {params: {bookIndex: $scope.index.value}})
            //10.251.0.21
            .success(function (data) {
                $scope.book = data;
                $scope.photos = [];
                $scope.addImage();
            })
            .error(function (err) {
                return err;
            });
    }

    $scope.next = function () {
        $scope.increment();
        $scope.getDesc();
        $scope._Index = 0;
        $scope._maxIndex = 0;
        $scope.photos = [];
    }

    $scope.increment = function () {
        $scope.index.value++;


    }

    $scope.decrement = function () {
        $scope.index.value--;
        $scope.getDesc();
        $scope._Index = 0;
        $scope._maxIndex = 0;
        $scope.photos = [];
    }

    $scope.init = function () {
          $scope.getDesc();

    }
    //$scope.initImage = function () {
    //    //$scope.addImage();
    //}
    $scope.addImage = function () {
        for (var i = 0; i < 10; i++) {
            $scope.photos.push({
                src: 'http://localhost:8080/static/'+ $scope.book.bookId  +'/'
                    //10.251.0.21
                + ($scope._Index + 1 + i) + '.jpg'
            });
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

        $scope._Index = ($scope._Index < $scope.photos.length - 1) ? ++$scope._Index : 0;
        if ($scope._maxIndex < $scope._Index) {
            ++$scope._maxIndex;
        }


        console.log($scope._Index + ' & ' + $scope._maxIndex );

     if ( $scope._maxIndex ===   $scope._Index) {

         if ($scope._Index % 10 === 4) {
             console.log($scope._Index + ' + download call');
             $scope.download();

         }
         if ($scope._Index % 10 === 6) {
             console.log($scope._Index + ' + add call');
             $scope.addImage();

         }
     }


    };
    // show a certain image
    $scope.showPhoto = function (index) {
        $scope._Index = index;
    };
    $scope.download = function () {
        $http.get('http://localhost:8080/download')
        //10.251.0.21
            .success(function (data) {
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
        $http.get('http://localhost:8080/search', {params: {searchExpr: $scope.keyword}})
        //'http://10.251.0.21:8080/search'

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
        if (typeof $scope.arrIrbis !== 'undefined') {

            console.log("bind, bookId =  " + $scope.book.bookId + "and mfn = " + $scope.arrIrbis[$scope.indexIrbis].mfn);
            $http.get('http://localhost:8080/bindBook', {
            //$http.get('http://#', {
            // 10.251.0.21
                params: {
                    bookId: $scope.book.bookId,
                    mfn: $scope.arrIrbis[$scope.indexIrbis].mfn
                }
            }).success(function (data) {

            }).error(function (err) {
                return err;
            });
        } else {
            alert("Найдите книгу которую нужно связать");
        }

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