'use strict';

const gulp = require('gulp');
const rigger = require('gulp-rigger');
const babel = require('gulp-babel');
const gulpIf = require('gulp-if');
const uglify = require('gulp-uglify');

const isProduction = process.env.NODE_ENV === 'prod';

module.exports = function(options) {

  return function() {
    return gulp.src(options.src, {base: options.base})
        .pipe(rigger())
        .pipe(babel())
        .pipe(gulpIf(isProduction, uglify()))
        .pipe(gulp.dest(options.dist));
  }

};
