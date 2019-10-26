import {Directive, ElementRef, OnInit} from '@angular/core';


@Directive({
  selector: '[smartTags]'
})
export class SmartTagsDirective implements OnInit{

  constructor(private el : ElementRef) { }

  ngOnInit(){
    import('bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js').then(()=>{
      this.render()
    })
  }


  render(){
    $(this.el.nativeElement).tagsinput();
  }


}
