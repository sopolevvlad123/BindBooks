/**
 * Created by pc8 on 27.11.15.
 */

app.service('bookDesc', function($http){

     this.getBookDesc = function(bookIndex,  index, photos, callback){
        return $http.get('http://10.251.0.21:8080/book', {params: {bookIndex: bookIndex}})
            .success(callback)
            .error(function (err) {
                return err;
            });
    }

});