# Smartadmin

[live](https://sang2-shockwave.rhcloud.com) demo

## AOT Note
Starting from version 1.8.7.2 we are supporting AOT builds.  
 
use `npm run build:aot` to run AOT build
 
if you'll get `FATAL ERROR: CALL_AND_RETRY_LAST Allocation failed - JavaScript heap out of memory` use `npm run build:aot2` or increase node memory heep size even more (check `package.json` `scripts` section for details) 


## Prerequisites

Make sure latest [angular-cli] installed globally. Follow [update-how-to](https://github.com/angular/angular-cli#updating-angular-cli)  

This project has dependencies that require **Node 4.x.x and NPM 3.x.x**.

For Windows users [git-bash](https://git-scm.com/downloads) is perfect **terminal**-window to manage nodejs projects. [1]
 
 
## Installation

1. Download and unpack
2. Run `npm i`. This may take a while. It may even *freeze* a bit on final steps - be patient. 
  * Also Remember, that npm on installing project dependencies may try to rebuild some modules (i.e *gyp*) and show scary red errors on that rebuild fails. Don't panic. If your `npm install` ends with long tree of project dependencies - then you are going right way. Nevermind deprecation WARNs. 

3. To start whole app template (this means a lot of initial compilation) in local dev server run
  * `npm run server`
  * or checkout *cookbook* section for some starting tips
4. Point your browser to [http://localhost:4200](http://localhost:4200)

#### tip 
quick way to speed up builds when testing 
* exclude whole template parts from compilation by commenting them in `src/app/app.routing.ts` 


 
## NPM scripts
Checkout `scripts` section in `package.json` for tools launching shortcuts

 
## App structure

```
|-- e2e         # end-to-end tests directory 
...
|
|-- src
|   |-- app
|   |   |-- +feature-module-1        # by convension async modules are prefixed with + sign            
|   |       |-- feature-module-1.component.html
|   |       |-- feature-module-1.component.ts
|   |       |-- feature-module-1.module.ts
|   |       |-- feature-module-1-routing.module.ts
|   |   |-- +feature-module-2
|   |   ...
|   |   |-- core                      # core module for app wide injectable service instancess 
|   |       |-- api                   
|   |       |-- guards                # prevent core module of being loaded twice
|   |       |-- core.module.ts        
|   |
|   |   |-- shared                    # dir with common directives, components, services, pipes 
|   |       |-- chat
|   |       |-- forms
|   |       |-- graphs
|   |       ...
|   |       |-- smartadmin.config.ts  # smartadmin setup file
|   |       |-- smartadmin.module.ts  # shared module useful for reexport common functionality
|   |   ...    
|   |   |-- app.module.ts       # app root module
|   |   |-- app.resolver.ts     # here you can async fetch data for app before init  
|   |   |-- app.routing.ts      # top routes definition
|   |   |-- app.service.ts      # global app state service
|   |       
|   |-- assets           # static resources folder
|   |   |-- css   
|   |   |-- fonts   
|   |   ...   
|   |-- environment      # env specific variables
|   |   |-- environment.prod.ts   
|   |   |-- environment.ts        
|   |-- custom-typings.d.ts      # typescript definitions tweaks 
|   |-- index.html              
|   |-- main.ts          # app entry point 
|   |-- polyfills.ts     # polyfills for browsers
|   |-- style.css        # project specific styles
|   |-- test.ts          # unit tests entry point
|   |-- tsconfig.json    # typescript config
|   |-- vendor.ts        # common vendor chunk
|   
...
|-- angular-cli.json           # cli project config
|-- karma.conf.js              # unit tests config
|-- package.json
|-- protractor.conf.js         # e2e tests config
...

```

## Build
* Run `npm run build` to build the project. 
* The build artifacts will be stored in the `dist/` directory. 
* checkout how excluding routes from *.routing.ts file reduces *.chunks.js count.
* dev build off app with dozen routes/pages takes comparatively acceptable time (~ 1 min)
* inspect built chunks, just to get idea how 
* Use the `npm run build:prod` for a production build.
* On huge app prod build may take more than 10 mins. On the final step it may look frozen completely. Be patient. Alocate at least 1Gb of free RAM. 
   * production code goes through optimizations, minifying, uglyfying, tree-shaking algorithm  -  but that's just price for nice chunked minimalist builds. 
   * there are some code rules, how to help tree-shacking compilers to do their [job](http://www.2ality.com/2015/12/webpack-tree-shaking.html)
    
#### tip 
write shell scripts for build/deploy/whatever routines automation


## Smartadmin Angular 2 templates

Smartadmin will be supplied with 3 templates: 
* *full* - full demo application
* *blank* - app with simple Home page. All demo pages are also there  but they are excluded from build by default
* *lite* - good starting point for creating smartadmin app with no extra dependencies (like jquery) 


## Some useful code stuff

* Switching app root-layouts according to route (e.g. different layout for login/register pages) 
* Lazy loading everywhere - routing and plugins 
* Animating routes with Decorators
* hot module replacement
* Google Map - async map initializer 
* Image editor - Angular 2 application with Redux data flow 
* CK Editor - loading scripts from cdn
* and much more 


## Webpack

Under the hood project is maintained with webpack. 
Checkout `config/` directory for environment specific webpack.config files 


## Testing
Testing is important for big projects. So we have good old js combo!

App is configured for running end-to-end (via [Protractor](http://www.protractortest.org/)) and unit (via [Karma](https://karma-runner.github.io)) tests

`npm run e2e` shortcut for executing end-to-end tests. 
`npm run test` for unit tests.


## cookbook
 
*  download, unpack, rename template into your new project dir
*  init git repo in your project  
*  exclude all unwanted routes in `app/app.routing.ts`
    * try to remove maximum stuff on this point. This will speed up builds a lot. 
    * Template has lot of splitting points allowing you to exclude plugin dependencies not just from build but even from webpack compilation.     
*  pick some route that is matching your idea. Or use `app/+misc/blank`, or generate new module with **angular-cli**
* now decide and chose what features are desired in your new project 
* perhaps maps, modals, some graphs, maybe voice-control and knobs...?
* include chosen modules from `app/shared/` subs into  your app's first 
* adjust navigation `app/shared/layout/navigation/navigation.component.html` 
   * (soon navigation will be configurable via json or `smartadmin.config.js`)
* run `npm run server:dev:hmr` to start dev server
* now it's time to get some data, to use in  your project. Or even Big Data? Collect from web API's, social networks, libraries; 
* explore, transform, display, analyze, edit. Angular 2 is really good for joining pieces together.
* until you have no backend with powerful db, use `localstorage` to gather results, persist data and even sync browser tabs 
* cleanup by deleting demo pages that are not matching your idea.  

### some tips 
* use angular-cli for code generating 
* choose and know your IDE, or try [vscode](https://code.visualstudio.com/) - it has good typescript support and acceptable resources usage. 
* use logs: if you cannot build, compile, deploy something, first place to locate error causers are log outputs
* create shortcuts for frequent git operations and commit your progress more often
* always automate DB backups.



## next releases plans
* reduce dependencies
* html templates smart minifying 
* smart-cli tool for creating and refactoring (or renaming) complex **angular** structures .


## resources
- [live project demo](https://sang2-shockwave.rhcloud.com)
- [webpack 2 docs](https://webpack.js.org)
- [angular-cli docs](https://github.com/angular/angular-cli)


## remarks
[1] If you, on some edging cases, can't build or run angular 2 projects, consider to try any virtual environment (i.e custom vagrant with some lightweight linux and nodejs@6.*.*)
