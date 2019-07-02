'use strict';

const gulp = require('gulp');
const htmlmin = require('gulp-htmlmin');

module.exports = function(options) {

  return function() {
    return gulp.src(options.src, {base: options.base})
        .pipe(htmlmin({
          collapseWhitespace: true,
          collapseInlineTagWhitespace: true,
          removeComments: true,
          minifyCSS: true,
          minifyJS: true
        }))
        .pipe(gulp.dest(options.dist));
  }

};
