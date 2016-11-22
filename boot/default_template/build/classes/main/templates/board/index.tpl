package templates.board

layout 'layout.tpl', title: 'Messages : Create',
  content: contents {
    div (class:'container') {
      form (id:'messageForm', action:'/', method:'post') {
      	if (formErrors) {
          div(class:'alert alert-error') {
            formErrors.each { error ->
              p error.defaultMessage
            }
          }
        }
        div (class:'pull-right') {
          a (href:'/', 'Messages')
        }

        board.each { atBoard ->
            li {
                a(href: "/person/$atBoard.name", "$atBoard.name $atBoard.addr")
            }
        }

      }
    }
  }