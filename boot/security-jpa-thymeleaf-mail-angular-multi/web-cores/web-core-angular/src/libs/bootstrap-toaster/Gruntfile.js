module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        copy: {
            docs: {
                files: [
                    {
                        expand: true,
                        cwd: 'dist/js/',
                        src: [
                            'bootstrap-toaster.js',
                            'bootstrap-toaster.js.map'
                        ],
                        dest: 'docs/js/'
                    }
                ]
            }
        },

        run: {
            js_compile: {
                cmd: 'npm', args: ['run','js-compile']
            },
            js_minify: {
                cmd: 'npm', args: ['run','js-minify']
            }
        },

        watch: {
            js: {
                files: [
                    'src/js/*.js'
                ],
                tasks: [
                    'run:js_compile',
                    'copy:docs'
                ]
            }
        }
    })

    grunt.loadNpmTasks('grunt-run')
    grunt.loadNpmTasks('grunt-contrib-copy')
    grunt.loadNpmTasks('grunt-contrib-watch')
}