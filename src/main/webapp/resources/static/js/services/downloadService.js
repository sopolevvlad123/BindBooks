/**
 * Created by pc8 on 27.11.15.
 */

app.service('downloadService', function($http){

    this.download = function(){

        $http.get('http://10.251.0.21:8080/download')
            .success(function (data) {
            })
            .error(function (err) {
                return err;
            });
    }


});