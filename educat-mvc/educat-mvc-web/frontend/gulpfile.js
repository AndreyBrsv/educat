'use strict';

const gulp = require('gulp');


// =============================================================================
// функция "ленивого" подключения модуля
// =============================================================================

function lazyRequireTasks(taskName, path, options) {
  options = options || {};
  options.taskName = taskName;
  gulp.task(taskName, function(callback) {
    let task = require(path).call(this, options);
    return task(callback);
  })
}


// =============================================================================
// html
// =============================================================================

lazyRequireTasks('build:html', './gulp-tasks/build/html', {
  src: 'src/**/*.html',
  base: 'src/',
  dist: 'dist/'
});

lazyRequireTasks('clean:html', './gulp-tasks/clean', {
  dist: 'dist/**/*.html'
});


// =============================================================================
// css
// =============================================================================

lazyRequireTasks('build:css', './gulp-tasks/build/css', {
  src: 'src/pages-decl/**/*.css',
  base: 'src/',
  dist: 'dist/'
});

lazyRequireTasks('clean:css', './gulp-tasks/clean', {
  dist: 'dist/pages-decl/**/*.css'
});


// =============================================================================
// js
// =============================================================================

lazyRequireTasks('build:js', './gulp-tasks/build/js', {
  src: 'src/pages-decl/**/*.js',
  base: 'src/',
  dist: 'dist/'
});

lazyRequireTasks('clean:js', './gulp-tasks/clean', {
  dist: 'dist/pages-decl/**/*.js'
});


// =============================================================================
// очистка сборки
// =============================================================================

lazyRequireTasks('clean', './gulp-tasks/clean', {
  dist: 'dist/'
});


// =============================================================================
// наблюдение за исходниками
// =============================================================================

gulp.task('watch', function() {

  gulp.watch('src/**/*.html', gulp.series('clean:html', 'build:html'))
      .on('all', function(event, path) {
        console.log('Task initiator: "' + path + '"' + ' Event: ' + event);
      });

  gulp.watch([
    'src/blocks/**/*.css',
    'src/pages-decl/**/*.css'
  ], gulp.series('clean:css', 'build:css'))
      .on('all', function(event, path) {
        console.log('Task initiator: "' + path + '"' + ' Event: ' + event);
      });

});


// =============================================================================
// варианты сборки
// =============================================================================

gulp.task('default', gulp.series('clean', 'build:html', 'build:css', 'watch'));
